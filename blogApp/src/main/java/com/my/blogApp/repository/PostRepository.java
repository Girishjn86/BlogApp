package com.my.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.blogApp.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{
	
}
