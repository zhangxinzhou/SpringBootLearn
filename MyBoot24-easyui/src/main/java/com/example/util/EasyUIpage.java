package com.example.util;

import java.util.List;

import org.springframework.data.domain.Page;


public class EasyUIpage<T> {

	private Long total;
	private List<T> rows;
	private List<?> footer;  //有可能是T也有可能是List<Map<String, Object>>,所以这里使用?
	
	
	
	public EasyUIpage() {
		super();
	}
	
	public EasyUIpage(Long total, List<T> rows, List<?> footer) {
		super();
		this.total = total;
		this.rows = rows;
		this.footer = footer;
	}
	
	public EasyUIpage(Page<T> page,List<?> list) {
		super();
		this.total = page.getTotalElements();
		this.rows = page.getContent();
		this.footer = list;
	}
	public EasyUIpage(List<T> tlist){
		super();
		this.rows=tlist;
	}
	public EasyUIpage(Page<T> page) {
		super();
		this.total = page.getTotalElements();
		this.rows = page.getContent();
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public List<?> getFooter() {
		return footer;
	}
	public void setFooter(List<?> footer) {
		this.footer = footer;
	}
	@Override
	public String toString() {
		return "EasyUIpage [total=" + total + ", rows=" + rows + ", footer=" + footer + "]";
	}
	
	
}
