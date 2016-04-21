package com.panichmaxim.yandextest.controller.network;

import com.panichmaxim.yandextest.controller.network.response.GetArtistsListResponse;

import retrofit.http.GET;
import rx.Observable;

public interface ServerApi {
    @GET("/artists.json")
    Observable<GetArtistsListResponse> getArtists();
}
