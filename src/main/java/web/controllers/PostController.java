package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Post;
import web.services.PostService;

import java.util.Date;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  @Autowired
  private PostService postService;

  @GetMapping
  public Iterable<Post> posts() {
    return postService.getAllPosts();
  }

  @PostMapping
  public Post publishPost(@RequestBody Post post) {
    if (post.getDateCreated() == null)
      post.setDateCreated(new Date());
    return postService.insertPost(post);
  }

  @PutMapping("/{id}")
  public void updatePost(
          @PathVariable Long id,
          @RequestBody Post post) {
    postService.insertPost(post);

  }

  @DeleteMapping("/{id}")
  public void deletePost(
          @PathVariable Long id,
          @RequestBody Post post) {
    postService.deletePost(post);
  }

}
