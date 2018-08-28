package org.zerock.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageVO {
	
	/*브라우저에서 전달되는 값은 페이지 번호(page)와 게시물의 수(size)만을 받도록 설계.
	이때에도 일정 이상의 값이 들어올 수 없도록 제약을 둔다.
	이후 정렬 방향이나, 정렬 기준이 되는 속성은 컨트로럴에서 지정한다.*/
	
	private static final int DEFAULT_SIZE = 10;
	private static final int DEFAULT_MAX_SIZE = 50;
	
	private int page;
	private int size;
	
	public PageVO() {
		this.page = 1;
		this.size = DEFAULT_SIZE;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page < 0? 1:page;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
	}
	
	//String...(var) args를 사용하면 배열 생성을 생략 할 수 있다.
	public Pageable makePageable(int direction, String...props) {
		
		Sort.Direction dir = direction == 0? Sort.Direction.DESC : Sort.Direction.ASC;
		
		return PageRequest.of(this.page -1, this.size, dir, props);
	}

}
