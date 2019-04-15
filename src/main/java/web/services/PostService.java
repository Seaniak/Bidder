package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Post;
import web.repositories.PostRepo;

import java.util.List;

@Service
public class PostService {

  @Autowired
  private PostRepo postRepo;

  public List<Post> getAllPosts(){
    return postRepo.findAll();
  }

  public Post insertPost(Post post){
    return postRepo.save(post);
  }

  public void deletePost(Post post){
    postRepo.delete(post);
  }
}
