package com.estsoft.bookmall.app;

import java.util.List;

import com.estsoft.bookmall.dao.BookDao;
import com.estsoft.bookmall.dao.CartDao;
import com.estsoft.bookmall.dao.CategoryDao;
import com.estsoft.bookmall.dao.MemberDao;
import com.estsoft.bookmall.dao.OrderDao;
import com.estsoft.bookmall.vo.BookVO;
import com.estsoft.bookmall.vo.CartVO;
import com.estsoft.bookmall.vo.CategoryVO;
import com.estsoft.bookmall.vo.MemberVO;
import com.estsoft.bookmall.vo.OrderVO;

public class BookMall {

	public static void main(String[] args) {

		// 1. MemberDao의  회원 생성 (2명)
		memberInsert();
		
		// 2. MemberDao의  회원 리스트
		memberList();
		
		// 3. CategoryDao의 카테고리 생성
		categoryInsert();	
		// 4. CategoryDao의 카테고리 리스트
		categoryList();

		// 5. BookDao의 서적(상품) 생성
		bookInsert();
		// 6. BookDao의  서적(상품) 리스트
		bookList();

		// 7. CartDao의 장바구니 정보 생성
		cartInsert();
		// 8. CartDao의  장바구니 내용 리스트
		cartList();
		
		// 9. OrderDao의 주문 생성(할 때 주문서적도 함께 insert)
		orderInsert();
		
		//10. OrderDao의 주문 리스트
		orderList();
		//11. OrderDao의 주문 서적 리스트  
		orderBookList();
		
	}

	private static void orderBookList() {
		System.out.println("<<주문 서적 리스트>>");
		OrderDao orderDao = new OrderDao();		
		List<OrderVO> list = orderDao.getOrderBookList();
		
		
		for(int i=0; i<list.size(); i++){
			System.out.println("주문번호:"+list.get(i).getNo()+", 서적번호:"+list.get(i).getBook_no()+", 수량:"+list.get(i).getCount());
		}
	}

	private static void orderList() {
		System.out.println("<<주문 리스트>>");
		List<OrderVO> list = new OrderDao().getOrderList();

		for (OrderVO vo : list) {
			System.out.println(vo);
		}
	}

	private static void orderInsert() {	// 주문과 주문서적 함께 insert
		OrderVO orderVo = new OrderVO(null, 20000L, "서울특별시 송파구", 1L);
		OrderDao orderDao = new OrderDao();
		orderDao.insert(orderVo);	
		
		orderVo = new OrderVO(null, 15000L, "서울특별시 강남구", 2L);
		orderDao.insert(orderVo);
		
	}

	private static void cartList() {
		System.out.println("<<장바구니 리스트>>");
		List<CartVO> list = new CartDao().getList();

		for (CartVO vo : list) {
			System.out.println(vo);
		}
	}

	private static void cartInsert() {
		CartVO cartVo = new CartVO(1L, 2L, 2L);
		CartDao cartDao = new CartDao();
		cartDao.insert(cartVo);
		
		cartVo = new CartVO(1L, 3L, 1L);
		cartDao.insert(cartVo);
		
		cartVo = new CartVO(2L, 4L, 1L);
		cartDao.insert(cartVo);
		
		cartVo = new CartVO(2L, 1L, 3L);
		cartDao.insert(cartVo);
	}

	private static void bookList() {
		System.out.println("<<서적 리스트>>");
		List<BookVO> list = new BookDao().getList();

		for (BookVO vo : list) {
			System.out.println(vo);
		}
	}

	private static void bookInsert() {
		BookVO bookVo = new BookVO(null, "홍길동전", 10000L, 1L);
		BookDao bookDao = new BookDao();
		bookDao.insert(bookVo);
		
		bookVo = new BookVO(null, "위대한 공존", 18000L, 4L);
		bookDao.insert(bookVo);
		
		bookVo = new BookVO(null, "Java의 정석", 30000L, 3L);
		bookDao.insert(bookVo);
		
		bookVo = new BookVO(null, "르네상스의 비밀", 48000L, 6L);
		bookDao.insert(bookVo);
	}

	private static void categoryList() {
		System.out.println("<<카테고리 리스트>>");
		List<CategoryVO> list = new CategoryDao().getList();

		for (CategoryVO vo : list) {
			System.out.println(vo);
		}
	}

	private static void categoryInsert() {
		CategoryVO categoryVo = new CategoryVO(null, "소설");
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.insert(categoryVo);
		
		categoryVo = new CategoryVO(null, "수필");
		categoryDao.insert(categoryVo);
		
		categoryVo = new CategoryVO(null, "컴퓨터/IT");
		categoryDao.insert(categoryVo);
		
		categoryVo = new CategoryVO(null, "인문");
		categoryDao.insert(categoryVo);
		
		categoryVo = new CategoryVO(null, "경제");
		categoryDao.insert(categoryVo);
		
		categoryVo = new CategoryVO(null, "예술");
		categoryDao.insert(categoryVo);
	}

	private static void memberList() {
		System.out.println("<<회원 리스트>>");
		List<MemberVO> list = new MemberDao().getList();

		for (MemberVO vo : list) {
			System.out.println(vo);
		}
	}

	private static void memberInsert() {
		MemberVO memberVo = new MemberVO(null, "홍길동", "010-1111-2222", "aaa@naver.com", "abcd");
		MemberDao memberDao = new MemberDao();
		memberDao.insert(memberVo);
		
		memberVo = new MemberVO(null, "둘리", "010-1111-3333", "bbb@naver.com", "1234");
		memberDao = new MemberDao();
		memberDao.insert(memberVo);
	}

}
