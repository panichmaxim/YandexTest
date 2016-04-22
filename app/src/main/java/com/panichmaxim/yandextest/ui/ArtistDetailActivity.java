package com.panichmaxim.yandextest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.panichmaxim.yandextest.R;
import com.panichmaxim.yandextest.model.db.DBArtist;
import com.panichmaxim.yandextest.model.db.StringObject;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

public class ArtistDetailActivity extends AppCompatActivity {

    public static String ITEM_ID = "ITEM_ID";

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.artists_detail_image) ImageView mImage;
    @Bind(R.id.artists_detail_genre) TextView mGenre;
    @Bind(R.id.artists_detail_info) TextView mInfo;
    @Bind(R.id.artists_detail_description) TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DBArtist node = Realm.getInstance(this).where(DBArtist.class).equalTo("mId", getIntent().getExtras().getInt(ITEM_ID)).findFirst();
        ImageLoader.getInstance().displayImage(node.getmCover().getmBig(), mImage);
        setTitle(node.getmName());
        String genres =  Stream.of(node.getmGenres()).map((StringObject obj) -> obj.getString() + "; ").collect(Collectors.joining());
        mGenre.setText(genres);
        mInfo.setText(node.getmAlbums() + " albums * " + node.getmTracks() + " songs");
        mDescription.setText(node.getmDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
