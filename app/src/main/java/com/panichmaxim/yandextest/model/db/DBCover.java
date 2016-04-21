package com.panichmaxim.yandextest.model.db;

import io.realm.RealmObject;

public class DBCover extends RealmObject {
    private String mBig;
    private String mSmall;

    public DBCover() {
    }

    public DBCover(String mBig, String mSmall) {
        this.mBig = mBig;
        this.mSmall = mSmall;
    }

    public String getmBig() {
        return mBig;
    }

    public void setmBig(String mBig) {
        this.mBig = mBig;
    }

    public String getmSmall() {
        return mSmall;
    }

    public void setmSmall(String mSmall) {
        this.mSmall = mSmall;
    }
}
