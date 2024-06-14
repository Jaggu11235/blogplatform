package com.example.blogplatform.service;

import com.example.blogplatform.entity.Author;
import com.example.blogplatform.entity.Comment;
import com.example.blogplatform.entity.Post;
import com.example.blogplatform.repository.AuthorRepository;
import com.example.blogplatform.repository.CommentRepository;
import com.example.blogplatform.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment Not available"));
    }

    public Comment addComment(Long postId, Comment comment) {
        Post postAvailable = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post Not available"));
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setCreatedAt(new Date());
        newComment.setPostId(postAvailable.getId());
        newComment.setAuthorId(comment.getAuthorId());
        postAvailable.getComments().add(newComment);
        postRepository.save(postAvailable);
        return commentRepository.save(newComment);
    }

    public Comment updateComment(Long commentId, Comment comment) {
        Comment commentAvailable = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment Not available"));
        commentAvailable.setContent(comment.getContent());
        return commentRepository.save(commentAvailable);
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public Set<Comment> getAllCommentsByAuthorId(Long authorId) {
        Set<Comment> commentsByAuthorId = new HashSet<>();
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author not found: " + authorId));
        Set<Post> allPosts = author.getPosts();
        for(Post post : allPosts) {
            commentsByAuthorId.addAll(post.getComments());
        }
        return commentsByAuthorId;
    }

    public Set<Comment> getAllCommentsByPost(Long postId) {
        Post postAvailable = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post Not available"));
        return postAvailable.getComments();
    }
}
