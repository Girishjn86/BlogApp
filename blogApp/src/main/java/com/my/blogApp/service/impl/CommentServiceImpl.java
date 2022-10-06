package com.my.blogApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.my.blogApp.entity.Comment;
import com.my.blogApp.entity.Post;
import com.my.blogApp.exception.BlogApiException;
import com.my.blogApp.exception.ResourceNotFoundException;
import com.my.blogApp.payload.CommentDto;
import com.my.blogApp.repository.CommentRepository;
import com.my.blogApp.repository.PostRepository;
import com.my.blogApp.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	
	public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		return comment;
	}
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
		Comment comment = mapToEntity(commentDto);
		comment.setPost(post);
		return mapToDto(commentRepository.save(comment));
	}

	@Override
	public List<CommentDto> getAllCommentsOfAPost(Long postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);
		List<CommentDto> commentDtos = comments.stream().map(p->mapToDto(p)).collect(Collectors.toList());
		return commentDtos;
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) throws BlogApiException {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment id not belong to the post");
		}
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) throws BlogApiException {
		
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment id not belong to the post");
		}
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		Comment commentResponse = commentRepository.save(comment);
		return mapToDto(commentResponse);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) throws BlogApiException {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment id not belong to the post");
		}
		commentRepository.deleteById(commentId);
	}

}
