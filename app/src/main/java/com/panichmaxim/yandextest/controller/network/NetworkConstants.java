package com.panichmaxim.yandextest.controller.network;

import retrofit.RestAdapter;

public class NetworkConstants {

    public static final String SERVER_URL = "http://cache-default04f.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016";
    public static final RestAdapter.LogLevel LOGLEVEL = RestAdapter.LogLevel.FULL;

    public static RestAdapter getRestAdapter() {
        return new RestAdapter.Builder().setLogLevel(LOGLEVEL).setEndpoint(NetworkConstants.SERVER_URL).build();
    }

}
