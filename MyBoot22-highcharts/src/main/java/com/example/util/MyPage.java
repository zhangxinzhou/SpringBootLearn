package com.example.util;

import java.util.List;

import org.springframework.data.domain.Page;

public class MyPage<T>{

	private List<T> rows;
	private Long total;
	
	public MyPage() {
		super();
	}
		
	public MyPage(List<T> rows) {
		super();
		this.rows = rows;
	}

	public MyPage(Page<T> p) {
		super();
		this.rows = p.getContent();
		this.total =p.getTotalElements();
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "MyPage [rows=" + rows + ", total=" + total + "]";
	}

	
	
	
	
	
}
