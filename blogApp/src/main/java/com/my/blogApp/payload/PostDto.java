package com.my.blogApp.payload;

import java.util.Set;

public class PostDto {
	private Long id;
	private String title;
	private String description;
	private String content;
	private Set<CommentDto> comments;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<CommentDto> getComments(){
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
}
