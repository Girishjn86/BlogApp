package com.my.blogApp.service;

import java.util.List;

import com.my.blogApp.exception.BlogApiException;
import com.my.blogApp.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(Long postId,CommentDto commentDto);
	List<CommentDto> getAllCommentsOfAPost(Long postId);
	CommentDto getCommentById(Long postId,Long commentId) throws BlogApiException;
	CommentDto updateComment(Long postId,Long commentId,CommentDto commentDto) throws BlogApiException;
	void deleteComment(Long postId,Long commentId) throws BlogApiException;
}
