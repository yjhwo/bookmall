package com.estsoft.bookmall.vo;

public class BookVO {
	private Long no;
	private String title;
	private Long price;
	private Long category_no;
	
	public BookVO()	{	}

	public BookVO(Long no, String title, Long price, Long category_no) {
		this.no = no;
		this.title = title;
		this.price = price;
		this.category_no = category_no;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getCategory_no() {
		return category_no;
	}

	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}

	@Override
	public String toString() {
		return no+". 제목:"+title+", 가격:"+price+", 카테고리 번호:"+category_no;
	}
	
	
}
