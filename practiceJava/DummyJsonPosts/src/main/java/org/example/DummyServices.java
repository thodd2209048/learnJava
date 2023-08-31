package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class DummyServices {
    private final OkHttpClient client = new OkHttpClient();

    public DummyServices() {
    }

    private Response sendRequest(Request request) throws IOException {
        Call call = client.newCall(request);
        return call.execute();
    }

    private <T> T convertJsonToObject(String jsonString, Class<T> targetType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, targetType);
    }

    private <T> RequestBody createRequestBodyFromObject(T post) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);
        return RequestBody.create(MediaType.parse("application/json"), json);
    }

    public List<Post> list(int page, int size) throws IOException {
        Integer skip = (page - 1) * size;
        String url = String.format("https://dummyjson.com/products?limit=%s&skip=%s", size, skip);
        Request request = new Request.Builder().url(url).build();
        Response response = sendRequest(request);
        String responseString = response.body().string();
        PostResponse postResponse = convertJsonToObject(responseString, PostResponse.class);

        return postResponse.getPosts();
    }

    public Post create(Post post) throws IOException {
        RequestBody body = createRequestBodyFromObject(post);

        Request request = new Request.Builder()
                .url("https://dummyjson.com/posts/add")
                .post(body)
                .build();

        Response response = sendRequest(request);
        String responseString = response.body().string();
        System.out.println(responseString);

        Post postResponse = convertJsonToObject(responseString, Post.class);
        return postResponse;
    }

    public Post update(int id, Post post) throws IOException {
        RequestBody body = createRequestBodyFromObject(post);
        Request request = new Request.Builder()
                .url("https://dummyjson.com/posts/" + id)
                .put(body)
                .build();
        Response response = sendRequest(request);

        String responseString = response.body().string();
        System.out.println(responseString);

        return convertJsonToObject(responseString, Post.class);
    }

    public Post delete(int id) throws IOException {
        Request request = new Request.Builder()
                .url("https://dummyjson.com/posts/" + id)
                .delete()
                .build();
        Response response = sendRequest(request);

        String responseString = response.body().string();
        System.out.println(responseString);

        return convertJsonToObject(responseString, Post.class);
    }


}
