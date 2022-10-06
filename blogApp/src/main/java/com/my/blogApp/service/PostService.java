package com.my.blogApp.service;

import com.my.blogApp.payload.PostDto;
import com.my.blogApp.payload.PostPageResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);
	PostPageResponse getAllPosts(int pageNo,int pageSize, String sortBy,String sortDir);
	PostDto getPostById(Long id);
	PostDto updatePost(PostDto postDto,Long id);
	void deletePost(Long id);
	void deleteAllPosts();
}
