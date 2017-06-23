package com.codeup.svcs;

import com.codeup.models.Post;
import com.codeup.repositories.PostsRepository;
import com.codeup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("postSvc")
public class PostSvc {

    private PostsRepository postsDao;
    private UsersRepository usersDao;

    @Autowired
    public PostSvc(PostsRepository postsDao, UsersRepository usersDao){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
    }

    public Iterable<Post> findAll(){
        return postsDao.findAll();
    }

    public Post findOne(long id) { return postsDao.findOne(id); }

    public Post save(Post post) {

        postsDao.save(post);
        return post;
    }

    public void delete(long id) {
        postsDao.delete(id);
    }
}
