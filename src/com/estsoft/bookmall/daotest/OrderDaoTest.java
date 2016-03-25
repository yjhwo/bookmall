package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmall.dao.OrderDao;
import com.estsoft.bookmall.vo.OrderVO;

public class OrderDaoTest {

	public static void main(String[] args) {
		
		insertTest();
		
		getListTest();
		
		//getOrderBookListTest();
	}


	private static void insertTest() {
		OrderVO orderVo = new OrderVO(null, 20000L, "서울특별시 송파구", 1L);
		OrderDao orderDao = new OrderDao();
//		orderDao.insert(orderVo);	
		
		orderVo = new OrderVO(null, 15000L, "서울", 2L);
		orderDao.insert(orderVo);
	}
	
	private static void getListTest() {
		List<OrderVO> list = new OrderDao().getOrderList();

		for (OrderVO vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void getOrderBookListTest() {
		OrderDao orderDao = new OrderDao();		
		List<OrderVO> list = orderDao.getOrderBookList();
		
		
		for(int i=0; i<list.size(); i++){
			System.out.println("주문번호:"+list.get(i).getNo()+", 서적번호:"+list.get(i).getBook_no()+", 수량:"+list.get(i).getCount());
		}
		
	}
}
