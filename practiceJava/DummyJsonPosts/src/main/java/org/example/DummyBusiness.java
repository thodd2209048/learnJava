package org.example;

import java.io.IOException;
import java.util.List;

public class DummyBusiness {
    private DummyDAO dummyDao = new DummyDAOImpl();

    public DummyBusiness() {
    }

    public List<Post> list(int page, int size) throws IOException {
        return dummyDao.list(page, size);
    }

    public Post create(Post post) throws IOException {
        return dummyDao.create(post);
    }
    public Post update(int id, Post post) throws IOException {
        return dummyDao.update(id, post);
    }
    public Post delete(int id) throws IOException {
        return dummyDao.delete(id);
    }
}
