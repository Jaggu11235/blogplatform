package com.example.blogplatform.service;

import com.example.blogplatform.entity.Author;
import com.example.blogplatform.entity.Comment;
import com.example.blogplatform.entity.Post;
import com.example.blogplatform.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Author getAuthorDetailsById(Long authorId) {
        Author authorById = authorRepository.findById(authorId).orElse(null);
        if(authorById != null) {
            return authorById;
        } else {
            throw new RuntimeException("Author not found with id: "+authorId);
        }
    }

    public Author addUser(Author author) {
        author.setCreatedAt(new Date());
        return authorRepository.save(author);
    }

    public Author updateAuthorDetails(Long authorId, Author author) {
        Author authorById = authorRepository.findById(authorId).orElse(null);
        if(authorById != null) {
            authorById.setEmail(author.getEmail());
            authorById.setName(author.getName());
        } else {
            throw new RuntimeException("Author not found with id: "+authorId);
        }
        authorRepository.save(authorById);
        return authorById;
    }

    public Set<Post> getAllPostsByAuthorId(Long authorId) {
        Author authorDetailsById = getAuthorDetailsById(authorId);
        return authorDetailsById.getPosts();
    }

}
