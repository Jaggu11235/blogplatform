package com.example.blogplatform.controller;

import com.example.blogplatform.entity.Comment;
import com.example.blogplatform.entity.Post;
import com.example.blogplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/authors")
public class PostController {
    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping("/posts/add/{authorId}")
    public Post addPost(@PathVariable Long authorId, @RequestBody Post post) {
        return postService.addPost(authorId, post);
    }

    @PutMapping("/posts/update/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
        return postService.updatePost(postId, post);
    }

    @GetMapping("/posts/{postId}/getAllComments")
    public Set<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
        return postService.getCommentsByPostId(postId);
    }
}
