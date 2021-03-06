package com.panichmaxim.yandextest.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.panichmaxim.yandextest.R;
import com.panichmaxim.yandextest.model.db.DBArtist;
import com.panichmaxim.yandextest.model.db.StringObject;
import com.panichmaxim.yandextest.ui.ArtistDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ViewHolder> {

    private List<DBArtist> mNodes = new ArrayList<>();
    private Context mContext;

    public ArtistsListAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.artists_list_item_title) TextView mTitle;
        @Bind(R.id.artists_list_item_image) ImageView mImage;
        @Bind(R.id.artists_list_item_genre) TextView mGenre;
        @Bind(R.id.artists_list_item_info) TextView mInfo;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public void setData(List<DBArtist> nodes) {
        this.mNodes = nodes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artists_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mImage.setImageDrawable(null);
        ImageLoader.getInstance().displayImage(this.mNodes.get(position).getmCover().getmSmall(), holder.mImage);
        holder.mTitle.setText(this.mNodes.get(position).getmName());
        String genres =  Stream.of(this.mNodes.get(position).getmGenres()).map((StringObject obj) -> obj.getString() + "; ").collect(Collectors.joining());
        holder.mGenre.setText(genres);
        holder.mInfo.setText(this.mNodes.get(position).getmAlbums() + " albums, " + this.mNodes.get(position).getmTracks() + " songs");
        holder.itemView.setOnClickListener((View view) -> {
            Intent intent = new Intent(mContext, ArtistDetailActivity.class);
            intent.putExtra(ArtistDetailActivity.ITEM_ID, mNodes.get(position).getmId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.mNodes.size();
    }
}
