package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmall.dao.CartDao;
import com.estsoft.bookmall.vo.CartVO;

public class CartDaoTest {

	public static void main(String[] args) {
		insertTest();
		
		//getListTest();
	}

	private static void insertTest() {
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
	
	private static void getListTest() {
		List<CartVO> list = new CartDao().getList();

		for (CartVO vo : list) {
			System.out.println(vo);
		}
	}
}
