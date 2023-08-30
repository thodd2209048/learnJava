package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class DummyServices {
    public DummyServices() {
    }

    public List<Post> list(int page, int size) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://dummyjson.com/posts").build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String responseString = response.body().string();

        ObjectMapper mapper = new ObjectMapper();
        PostResponse postResponse = mapper.readValue(responseString, PostResponse.class);
        return postResponse.getPosts().stream()
                .filter(p -> p.getId() > (page - 1) * 10 && p.getId() <= page * 10)
                .toList();
    }

    public Post create(Post post) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url("https://dummyjson.com/posts/add")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        String responseString = response.body().string();
        System.out.println(responseString);

        ObjectMapper mapper = new ObjectMapper();
        Post postResponse = mapper.readValue(responseString, Post.class);
        return postResponse;
    }

    public Post update(int id, Post post) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url("https://dummyjson.com/posts/"+id)
                .put(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        String responseString = response.body().string();
        System.out.println(responseString);

        ObjectMapper mapper = new ObjectMapper();
        Post postResponse = mapper.readValue(responseString, Post.class);
        return postResponse;
    }

    public Post delete(int id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://dummyjson.com/posts/"+id)
                .delete()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        String responseString = response.body().string();
        System.out.println(responseString);

        ObjectMapper mapper = new ObjectMapper();
        Post postResponse = mapper.readValue(responseString, Post.class);
        return postResponse;
    }
}
