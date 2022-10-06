package com.my.blogApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.blogApp.payload.PostDto;
import com.my.blogApp.payload.PostPageResponse;
import com.my.blogApp.service.PostService;
import com.my.blogApp.util.AppConstants;

@RestController
@RequestMapping("/api/posts")
//@ResponseStatus(value=HttpStatus.OK)
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	
	}
	@GetMapping
	public PostPageResponse getAllPosts(
			@RequestParam(value="pageNo",defaultValue=AppConstants.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.DEFAULT_SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_SORT_DIRECTION,required=false) String sortDir
	){
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long id ) {
		return ResponseEntity.ok(postService.getPostById(id));	
	}
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long id){
		return new ResponseEntity<>(postService.updatePost(postDto, id),HttpStatus.OK);
	}
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id){
		postService.deletePost(id);
		
		return new ResponseEntity<>("Post " + id + " deleted successfully",HttpStatus.OK);
	}
	@DeleteMapping
	public ResponseEntity<String> deleteAllPost(){
		postService.deleteAllPosts();
		return new ResponseEntity<>("All Posts deleted",HttpStatus.OK);
	}
}
