package com.example.alarmforinddev.service;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;

import org.json.JSONObject;

public class ApiService {

    static String token;

    public static String baseUrl(String uri) {
        String BASE_URL = "https://inddev-id.tech/api/";
        return BASE_URL + uri;
    }

    /**
     * Get http request
     *
     * @param endpoint ini adalah full url endpoint api
     * @return String
     */
    public static ANRequest get(String endpoint) {
        return AndroidNetworking.get(endpoint)
                .addHeaders("Accept", "application/json").addHeaders("Authorization", "Bearer " + token).setPriority(Priority.HIGH).build();
    }

    /**
     * Post http request
     *
     * @param endpoint Ini adalah full url endpoint api
     * @return ANRequest
     */
    public static ANRequest post(String endpoint, JSONObject data) {
        return AndroidNetworking.post(endpoint)
                .addJSONObjectBody(data)
                .addHeaders("Accept", "application/json").addHeaders("Authorization", "Bearer " + token).build();
    }

    /**
     * Post http request
     *
     * @return ANRequest
     */
    public static ANRequest post(String endpoint) {
        return AndroidNetworking.post(endpoint)
                .addHeaders("Accept", "application/json").addHeaders("Authorization", "Bearer " + token).build();
    }

    /**
     * Put http request
     *
     * @param endpoint Ini adalah full url endpoint api
     * @return ANRequest
     */
    public static ANRequest put(String endpoint, JSONObject data) {
        try {
            data.put("_method", "PUT");
        } catch (Exception e) {
            Log.d("ADD_METHOD", e.getMessage());
        }
        return AndroidNetworking.post(endpoint)
                .addJSONObjectBody(data)
                .addHeaders("Accept", "application/json").addHeaders("Authorization", "Bearer " + token).build();
    }

    /**
     * Delete http request
     *
     * @param endpoint Ini adalah full url endpoint api
     * @return ANRequest
     */
    public static ANRequest delete(String endpoint) {
        JSONObject data = new JSONObject();
        try {
            data.put("_method", "DELETE");
        } catch (Exception e) {
            Log.d("ADD_METHOD", e.getMessage());
        }
        return AndroidNetworking.post(endpoint)
                .addJSONObjectBody(data)
                .addHeaders("Accept", "application/json").addHeaders("Authorization", "Bearer " + token).build();
    }


    public static ANRequest getJadwal() {
        String endpoint=baseUrl("picket-schedules");
        return get(endpoint);
    }
}
