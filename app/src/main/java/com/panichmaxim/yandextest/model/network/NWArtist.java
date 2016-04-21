package com.panichmaxim.yandextest.model.network;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NWArtist {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("genres")
    private List<String> mGenres;
    @SerializedName("tracks")
    private int mTracks;
    @SerializedName("albums")
    private int mAlbums;
    @SerializedName("link")
    private String mLink;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("cover")
    private NWCover mCover;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<String> getGenres() {
        return mGenres;
    }

    public int getTracks() {
        return mTracks;
    }

    public int getAlbums() {
        return mAlbums;
    }

    public String getLink() {
        return mLink;
    }

    public String getDescription() {
        return mDescription;
    }

    public NWCover getCover() {
        return mCover;
    }
}
