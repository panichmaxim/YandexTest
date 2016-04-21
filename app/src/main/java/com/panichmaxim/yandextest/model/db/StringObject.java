package com.panichmaxim.yandextest.model.db;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

public class StringObject extends RealmObject {
    private String string;

    public StringObject() {
    }

    public StringObject(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public static List<StringObject> convertStringList(List<String> source) {
        List<StringObject> result = new ArrayList<>();
        for (String item: source) {
            result.add(new StringObject(item));
        }
        return result;
    }
}
