package com.panichmaxim.yandextest.controller.network;

import com.panichmaxim.yandextest.model.network.NWArtist;
import java.util.List;
import retrofit.http.GET;
import rx.Observable;

public interface ServerApi {
    @GET("/artists.json")
    Observable<List<NWArtist>> getArtists();
}
