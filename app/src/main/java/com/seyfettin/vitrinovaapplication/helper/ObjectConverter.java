package com.seyfettin.vitrinovaapplication.helper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ObjectConverter {
    private static Gson sGson;

    public static Gson getGson() {
        if (sGson == null) {
            synchronized (Gson.class) {
                if (sGson == null)
                    sGson = new Gson();
            }
        }
        return sGson;
    }

    public static <T> T convert(Object data, Class<T> toClass) {
        String json = getGson().toJson(data);
        if (json.equals("")) {
            return null;
        } else {
            T obj = (T) getGson().fromJson(json, (Class) toClass);
            return obj;
        }
    }

    public static <T> List<T> convertList(Object data, Class<T> toClass) {
        String json = getGson().toJson(data);
        List<T> list = new ArrayList<>();
        try {

            JsonArray arry = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(getGson().fromJson(jsonElement, toClass));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
