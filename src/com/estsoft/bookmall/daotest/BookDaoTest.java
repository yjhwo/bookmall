package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmall.dao.BookDao;
import com.estsoft.bookmall.vo.BookVO;

public class BookDaoTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
	}
	
	private static void insertTest() {
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
	
	private static void getListTest() {
		List<BookVO> list = new BookDao().getList();

		for (BookVO vo : list) {
			System.out.println(vo);
		}
	}
}
