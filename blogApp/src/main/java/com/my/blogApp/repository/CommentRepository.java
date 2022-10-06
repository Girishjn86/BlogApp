package com.my.blogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.blogApp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{
	List<Comment> findByPostId(Long postId);
}
