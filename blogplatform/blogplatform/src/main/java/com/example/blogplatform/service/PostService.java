package com.example.blogplatform.service;

import com.example.blogplatform.entity.Author;
import com.example.blogplatform.entity.Comment;
import com.example.blogplatform.entity.Post;
import com.example.blogplatform.repository.AuthorRepository;
import com.example.blogplatform.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
    }

    public Set<Comment> getCommentsByPostId(Long postId) {
        Post post = getPostById(postId);
        return post.getComments();
    }

    public Post addPost(Long authorId, Post post) {
        Post createNewPost = new Post();
        Author authorDetailsById = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author not found with id: "+authorId));
        createNewPost.setTitle(post.getTitle());
        createNewPost.setContent(post.getContent());
        createNewPost.setCreatedAt(new Date());
        createNewPost.setAuthorId(authorDetailsById.getId());
        authorDetailsById.getPosts().add(createNewPost);
        authorRepository.save(authorDetailsById);
        return postRepository.save(createNewPost);
    }

    public Post updatePost(Long postId, Post post) {
        Post postById = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
        postById.setTitle(post.getTitle());
        postById.setContent(post.getContent());
        return postRepository.save(postById);
    }
}
