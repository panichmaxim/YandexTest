package com.panichmaxim.yandextest.model.db;


import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DBArtist extends RealmObject {
    @PrimaryKey
    private int mId;
    private String mName;
    private RealmList<StringObject> mGenres;
    private int mTracks;
    private int mAlbums;
    private String mLink;
    private String mDescription;
    private DBCover mCover;

    public DBArtist() {

    }

    public DBArtist(int mId, String mName, List<StringObject> mGenres, int mTracks, int mAlbums, String mLink, String mDescription, DBCover mCover) {
        this.mId = mId;
        this.mName = mName;
        this.mGenres = new RealmList<>(mGenres.toArray(new StringObject[mGenres.size()]));
        this.mTracks = mTracks;
        this.mAlbums = mAlbums;
        this.mLink = mLink;
        this.mDescription = mDescription;
        this.mCover = mCover;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public RealmList<StringObject> getmGenres() {
        return mGenres;
    }

    public void setmGenres(RealmList<StringObject> mGenres) {
        this.mGenres = mGenres;
    }

    public int getmTracks() {
        return mTracks;
    }

    public void setmTracks(int mTracks) {
        this.mTracks = mTracks;
    }

    public int getmAlbums() {
        return mAlbums;
    }

    public void setmAlbums(int mAlbums) {
        this.mAlbums = mAlbums;
    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public DBCover getmCover() {
        return mCover;
    }

    public void setmCover(DBCover mCover) {
        this.mCover = mCover;
    }
}
