package com.my.blogApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.my.blogApp.entity.Post;
import com.my.blogApp.exception.ResourceNotFoundException;
import com.my.blogApp.payload.PostDto;
import com.my.blogApp.payload.PostPageResponse;
import com.my.blogApp.repository.PostRepository;
import com.my.blogApp.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	private PostRepository postRepository;
	
	
	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}


	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}
	
	private PostDto mapToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		return postDto;
	}
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		
		Post responsePost = postRepository.save(post);
		
		PostDto responsePostDto = mapToDto(responsePost);
		
		return responsePostDto;
	}


	@Override
	public PostPageResponse getAllPosts(int pazeNo,int pazeSize,String sortBy,String sortDir) {
		Pageable pageable = null;
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
			pageable = PageRequest.of(pazeNo, pazeSize,sort);
		Page<Post> pagePosts = postRepository.findAll(pageable);
		List<Post> posts = pagePosts.getContent();
		List<PostDto> content = posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());	
		
		PostPageResponse postPageResponse = new PostPageResponse();
		postPageResponse.setContent(content);
		postPageResponse.setPageNo(pagePosts.getNumber());
		postPageResponse.setPageSize(pagePosts.getNumberOfElements());
		postPageResponse.setTotalElements(pagePosts.getTotalElements());
		postPageResponse.setTotalPages(pagePosts.getTotalPages());
		postPageResponse.setIsLast(pagePosts.isLast());
		return postPageResponse;
	}


	
	@Override
	public PostDto getPostById(Long id) {
		return mapToDto(postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id", id)));
	}


	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id", id));
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		
		return mapToDto(postRepository.save(post));
	}


	@Override
	public void deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
		postRepository.delete(post);		
	}


	@Override
	public void deleteAllPosts() {
		postRepository.deleteAll();	
	}
}
