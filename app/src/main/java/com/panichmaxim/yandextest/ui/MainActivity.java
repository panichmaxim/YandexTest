package com.panichmaxim.yandextest.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.panichmaxim.yandextest.App;
import com.panichmaxim.yandextest.R;
import com.panichmaxim.yandextest.controller.adapter.ArtistsListAdapter;
import com.panichmaxim.yandextest.controller.network.NetworkConstants;
import com.panichmaxim.yandextest.controller.network.ServerApi;
import com.panichmaxim.yandextest.model.db.ArtistsConverter;
import com.panichmaxim.yandextest.model.db.DBArtist;
import com.panichmaxim.yandextest.model.network.NWArtist;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit.RetrofitError;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.loadingPb) ProgressBar mLoadingPb;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.refresh) SwipeRefreshLayout mSwipeRefresh;
    @Bind(R.id.music_list_coordinator_layout) CoordinatorLayout mCoordinatorLayout;

    private Snackbar mSnackbar;
    private ArtistsListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new ArtistsListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefresh.setOnRefreshListener(this);
        mSnackbar = Snackbar
                .make(mCoordinatorLayout, getResources().getString(R.string.internet_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.action_settings), (View v) -> {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                });
        showPg();
        loadData();
    }

    @Override
    public void onRefresh() {
        if (mLoadingPb.getVisibility() != ProgressBar.VISIBLE) {
            loadData();
        } else {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    private void loadData() {
        mCompositeSubscription.add(NetworkConstants.getRestAdapter().create(ServerApi.class).getArtists()
                .compose(provider.<List<NWArtist>>bindToLifecycle())
                .map((List<NWArtist> response) -> ArtistsConverter.convertArtistsList(response))
                .doOnNext((List<DBArtist> list) -> {
                            Realm realm = Realm.getInstance(App.getContext());
                            realm.beginTransaction();
                            realm.copyToRealmOrUpdate(list);
                            realm.commitTransaction();
                            realm.close();
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DBArtist>>() {
                    @Override
                    public void onNext(List<DBArtist> list) {
                        mAdapter.setData(list);
                        mAdapter.notifyDataSetChanged();
                        mSnackbar.dismiss();
                        hidePg();
                        mSwipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof RetrofitError) {
                            if (((RetrofitError) e).getKind() == RetrofitError.Kind.NETWORK) {
                                mSnackbar.show();
                            } else {
                                Snackbar.make(mCoordinatorLayout, getResources().getString(R.string.network_error), Snackbar.LENGTH_LONG).show();
                            }
                        }
                        ArrayList<DBArtist> savedData = new ArrayList<>(Realm.getInstance(App.getContext()).where(DBArtist.class).findAll());
                        if (savedData != null) {
                            mAdapter.setData(savedData);
                            mAdapter.notifyDataSetChanged();
                        }
                        hidePg();
                        mSwipeRefresh.setRefreshing(false);
                    }
                }));
    }

    private void showPg() {
        mLoadingPb.setVisibility(ProgressBar.VISIBLE);
    }

    private void hidePg() {
        mLoadingPb.setVisibility(ProgressBar.INVISIBLE);
    }
}
