package com.example.blogplatform.controller;

import com.example.blogplatform.entity.Author;
import com.example.blogplatform.entity.Comment;
import com.example.blogplatform.entity.Post;
import com.example.blogplatform.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorDetailsById(authorId);
    }

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }


    @GetMapping("/authors/{authorId}/allPosts")
    public Set<Post> getAllPostsByAuthor(@PathVariable Long authorId) {
        return authorService.getAllPostsByAuthorId(authorId);
    }

    @PostMapping("/authors/add")
    public Author createUser(@RequestBody Author author) {
        return authorService.addUser(author);
    }

    @PutMapping("/authors/update/{authorId}")
    public Author updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorDetails(authorId, author);
    }
}
