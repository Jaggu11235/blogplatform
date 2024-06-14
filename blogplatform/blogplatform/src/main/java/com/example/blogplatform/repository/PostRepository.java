package com.example.blogplatform.repository;

import com.example.blogplatform.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
