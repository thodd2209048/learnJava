package org.example;

import java.io.IOException;
import java.util.List;

public interface DummyDAO {
    public List<Post> list(int page, int size) throws IOException;
    public Post create(Post post) throws IOException;
    public Post update(int id, Post post) throws IOException;
    public Post delete(int id) throws IOException;
}
