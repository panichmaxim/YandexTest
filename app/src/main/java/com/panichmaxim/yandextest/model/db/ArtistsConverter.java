package com.panichmaxim.yandextest.model.db;

import android.support.annotation.NonNull;

import com.panichmaxim.yandextest.model.network.NWArtist;
import com.panichmaxim.yandextest.model.network.NWCover;

import java.util.ArrayList;
import java.util.List;

public class ArtistsConverter {
    @NonNull
    public static List<DBArtist> convertArtistsList(@NonNull List<NWArtist> source) {
        List<DBArtist> result = new ArrayList<>();
        if (source != null) {
            for (NWArtist item: source) {
                result.add(convert(item));
            }
        }
        return result;
    }

    @NonNull
    public static DBArtist convert(@NonNull NWArtist source) {
        if (source == null) return null;
        return new DBArtist(
                source.getId(),
                source.getName(),
                StringObject.convertStringList(source.getGenres()),
                source.getTracks(),
                source.getAlbums(),
                source.getLink(),
                source.getDescription(),
                convert(source.getCover()));
    }

    @NonNull
    public static DBCover convert(@NonNull NWCover source) {
        if (source == null) return null;
        return new DBCover(
                source.getBig(),
                source.getSmall());
    }
}
