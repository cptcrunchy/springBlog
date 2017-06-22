package com.codeup.svcs;

import com.codeup.models.Post;
import com.codeup.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("postSvc")
public class PostSvc {

    private PostsRepository postsDao;

    @Autowired
    public PostSvc(PostsRepository postsDao){
        this.postsDao = postsDao;
    }

    public Iterable<Post> findAll(){
        return postsDao.findAll();
    }

    public Post findOne(long id) { return postsDao.findOne(id); }

    public Post save(Post post) { postsDao.save(post);return post; }

    public void delete(long id) { postsDao.delete(id); }


}
