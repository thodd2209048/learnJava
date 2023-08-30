package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.Post;

import java.io.IOException;

public class PostService {
    private final OkHttpClient client = new OkHttpClient();

    public PostService() {
    }


    private Response sendRequest(Request request) throws IOException {
        Call call = client.newCall(request);
        return call.execute();
    }

    public Post update(int id, Post post) throws IOException {
        String json = new ObjectMapper().writeValueAsString(post);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
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

    private <T> T convertJsonToObject(String jsonString, Class<T> targetType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, targetType);
    }
}
