package com.jike;


import okhttp3.*;

import java.io.IOException;

/**
 * 使用 okhttp3
 */
public class Week02 {

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:11111/post";
        String json = "{\"id\":\"1\"}";
        String s = new Week02().post(url, json);
        System.out.println(s);
    }

    private String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private String post(String url, String json) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("fail:" + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String str = response.body().string();
//                System.out.println("OK:" + str);
//            }
//        });

    }

}