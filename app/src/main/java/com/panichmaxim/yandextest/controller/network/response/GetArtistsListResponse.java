package com.panichmaxim.yandextest.controller.network.response;

import com.panichmaxim.yandextest.model.network.NWArtist;
import java.util.List;

public class GetArtistsListResponse {

    private List<NWArtist> mArtists;

    public List<NWArtist> getArtists() {
        return mArtists;
    }
}
