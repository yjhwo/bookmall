package com.estsoft.bookmall.vo;

public class CartVO {
	private Long customer_no;
	private Long book_no;
	private Long count;
	
	public CartVO(){	}

	public CartVO(Long customer_no, Long book_no, Long count) {
		this.customer_no = customer_no;
		this.book_no = book_no;
		this.count = count;
	}

	public Long getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(Long customer_no) {
		this.customer_no = customer_no;
	}

	public Long getBook_no() {
		return book_no;
	}

	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "고객번호:"+customer_no+", 주문번호:"+book_no+", 수량:"+count;
	}
	
		
}
