package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.List;

public class BaseHttpRequest {
    private static  final OkHttpClient client = new OkHttpClient();
    private static ObjectMapper mapper = new ObjectMapper();
    @SneakyThrows
    public static <T> T get(String url, Class<T> tClass) {
        Request req = new Request
                .Builder()
                .url(url)
                .build();
        String resString = client.newCall(req).execute().body().string();
        return mapper.readValue(resString, tClass);
    }

    @SneakyThrows
    public static <T> T post(String url, Object body, Class<T> tClass){
        RequestBody requestBody = RequestBody.create(mapper.writeValueAsBytes(body));
        Request req = new Request
                .Builder()
                .url(url)
                .post(requestBody)
                .build();
        String resString = client.newCall(req).execute().body().string();
        return mapper.readValue(resString, tClass);
    }
}
