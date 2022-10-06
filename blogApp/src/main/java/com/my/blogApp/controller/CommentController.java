package com.my.blogApp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.blogApp.exception.BlogApiException;
import com.my.blogApp.payload.CommentDto;
import com.my.blogApp.service.CommentService;

@RestController
@RequestMapping(value="/api/posts/{postId}/comments")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	@PostMapping
	public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") Long postId,
													@RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId, commentDto),HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<CommentDto>> getAllCommentsOfAPost(@PathVariable(value="postId")Long postId){
		return new ResponseEntity<>(commentService.getAllCommentsOfAPost(postId),HttpStatus.OK);
	}
	@GetMapping(value="/{commentId}")
	public ResponseEntity<CommentDto> getCommentsById(
			@PathVariable(value="postId")Long postId,
			@PathVariable(value="commentId") Long commentId
			) throws BlogApiException{
		return new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
	}
	@PutMapping(value="/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") Long postId,@PathVariable(value="commentId") Long commentId,@RequestBody CommentDto commentDto) throws BlogApiException{
		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto),HttpStatus.OK);
	}
	@DeleteMapping(value="/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="postId") Long postId,@PathVariable(value="commentId") Long commentId) throws BlogApiException{
		commentService.deleteComment(postId,commentId);
		return new ResponseEntity<>("comment-"+commentId+" of Post-" + postId +" is deleted Successfully.",HttpStatus.OK);	
	}
	
}
