package com.estsoft.bookmall.vo;

public class CategoryVO {
	private Long no;
	private String name;
	
	public CategoryVO()	{	}

	public CategoryVO(Long no, String name) {
		this.no = no;
		this.name = name;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return no+". "+name;
	}
	
	
}
