package com.panichmaxim.yandextest.model.network;

import com.google.gson.annotations.SerializedName;

public class NWCover {
    @SerializedName("small")
    private String mSmall;
    @SerializedName("big")
    private String mBig;

    public String getSmall() {
        return mSmall;
    }

    public String getBig() {
        return mBig;
    }
}
