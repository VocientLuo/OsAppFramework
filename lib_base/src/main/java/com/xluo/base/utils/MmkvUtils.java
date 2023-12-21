package com.xluo.base.utils;

import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;


public class MmkvUtils {

    public static final MMKV mmkv = MMKV.defaultMMKV();

    public static void save(String key, String data) {
        mmkv.encode(key, data);
    }

    public static void save(String key, int data) {
        mmkv.encode(key, data);
    }

    public static void save(String key, long data) {
        mmkv.encode(key, data);
    }

    public static void save(String key, boolean data) {
        mmkv.encode(key, data);
    }

    public static void save(String key, double data) {
        mmkv.encode(key, data);
    }

    public static void save(String key, float data) {
        mmkv.encode(key, data);
    }

    public static <T extends Parcelable> void save(String key, T data) {
        mmkv.encode(key, data);
    }

    public static <T extends Parcelable> void saveList(String key, List<T> datas) {
        mmkv.encode(key, new Gson().toJson(datas));
    }


    public static String get(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    public static int get(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    public static long get(String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    public static boolean get(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    public static double get(String key, double defaultValue) {
        return mmkv.decodeDouble(key, defaultValue);
    }

    public static float get(String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    public static <T extends Parcelable> T get(String key, Class<T> tClass) {
        return mmkv.decodeParcelable(key, tClass);
    }

    public static <T extends Parcelable> ArrayList<T> getList(String key, Class<T> tClass) {
        try {
            String str = mmkv.decodeString(key, "");
            if (str.isEmpty()){
                return new ArrayList<>();
            }
            return new Gson().fromJson(str, TypeToken.getParameterized(ArrayList.class, tClass).getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
