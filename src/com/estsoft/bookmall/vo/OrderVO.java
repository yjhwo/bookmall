package com.estsoft.bookmall.vo;

public class OrderVO {
	private Long no;			// 주문 번호
	private Long order_price;	// 결제 금액
	private String address;		// 배송지
	private Long customer_no;	// 고객 번호
	
	// ---- 주문 서적
	private Long book_no;		// 서적번호
	private Long count;			// 수량
	// ---- 고객 정보
	private String name;		// 이름
	private String email;		// 이메일
	
	public OrderVO(){	}

	

	public OrderVO(Long no, String name, String email,  Long order_price, String address, Long customer_no) {		//주문번호, 주문자(이름/이메일),결제금액,배송지
		this.no = no;
		this.name = name;
		this.email = email;
		this.order_price = order_price;
		this.address = address;
		this.customer_no = customer_no;
	}



	public OrderVO(Long no, Long book_no, Long count) {		// 주문서적
		this.no = no;
		this.book_no = book_no;
		this.count = count;
	}


	public OrderVO(Long no, Long order_price, String address, Long customer_no) {
		this.no = no;
		this.order_price = order_price;
		this.address = address;
		this.customer_no = customer_no;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(Long customer_no) {
		this.customer_no = customer_no;
	}

	public Long getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Long order_price) {
		this.order_price = order_price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return no+". 주문자(이름:"+name+"/이메일:"+email+"), 결제금액:"+order_price+", 배송지:"+address+", 고객번호:"+customer_no;
	}
	
	
}
