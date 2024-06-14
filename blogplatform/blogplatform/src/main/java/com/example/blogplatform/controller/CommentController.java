package com.example.blogplatform.controller;

import com.example.blogplatform.entity.Comment;
import com.example.blogplatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/authors/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/comments")
    public List<Comment> addComment() {
        return commentService.findAllComments();
    }

    @GetMapping("/comments/{commentId}")
    public Comment addComment(@PathVariable Long commentId) {
        return commentService.findCommentById(commentId);
    }

    @GetMapping("/commentsByPosts/{postId}")
    public Set<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentsByPost(postId);
    }

    @GetMapping("/commentsByAuthor/{authorId}")
    public Set<Comment> getAllCommentsByAuthorId(@PathVariable Long authorId) {
        return commentService.getAllCommentsByAuthorId(authorId);
    }

    @PostMapping("/comments/add/{postId}")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return commentService.addComment(postId, comment);
    }

    @PutMapping("comments/update/{commentId}")
    public Comment updateComments(@PathVariable Long commentId, @RequestBody Comment comment) {
        return commentService.updateComment(commentId, comment);
    }

}
