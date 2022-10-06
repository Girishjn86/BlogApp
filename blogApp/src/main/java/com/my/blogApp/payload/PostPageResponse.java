package com.my.blogApp.payload;

import java.util.List;

public class PostPageResponse {
	private List<PostDto> content;
	private int pageNo;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private boolean isLast;
	
	public PostPageResponse() {
		super();
	}
	public List<PostDto> getContent() {
		return content;
	}
	public void setContent(List<PostDto> content) {
		this.content = content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}
	
}
