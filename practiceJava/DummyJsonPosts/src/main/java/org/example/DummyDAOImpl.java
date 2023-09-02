package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.List;

public class DummyDAOImpl implements DummyDAO {
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Post> list(int page, int size) throws IOException {
        Integer skip = (page -1 ) * size;
        String url = String.format("https://dummyjson.com/posts?limit=%s&skip=%s&select=title,reactions,userId", size, skip);
        Request request = new Request.Builder()
                .url(url)
                .build();
        String resString = client.newCall(request).execute().body().string();
        PostResponse postResponse = mapper.readValue(resString, PostResponse.class);

        return postResponse.getPosts();
    }

    @Override
    public Post create(Post post) throws IOException {
        RequestBody requestBody = RequestBody.create(mapper.writeValueAsBytes(post));
        Request request = new Request.Builder()
                .url("https://dummyjson.com/posts/add")
                .post(requestBody)
                .build();
        String resString = client.newCall(request).execute().body().string();
        return mapper.readValue(resString, Post.class);
    }

    @Override
    public Post update(int id, Post post) throws IOException {
        RequestBody requestBody = RequestBody.create(mapper.writeValueAsBytes(post));
        String url = String.format("https://dummyjson.com/posts/%s", id);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        String resString = client.newCall(request).execute().body().string();
        return mapper.readValue(resString, Post.class);
    }

    @Override
    public Post delete(int id) throws IOException {
        String url = String.format("https://dummyjson.com/posts/%s", id);
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        String resString = client.newCall(request).execute().body().string();
        return mapper.readValue(resString, Post.class);
    }

}
