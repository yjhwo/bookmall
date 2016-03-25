package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmall.dao.CategoryDao;
import com.estsoft.bookmall.vo.CategoryVO;

public class CategoryDaoTest {

	public static void main(String[] args) {
		//insertTest();
		
		getListTest();
	}
		
	private static void insertTest() {
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
	
	private static void getListTest() {
		List<CategoryVO> list = new CategoryDao().getList();

		for (CategoryVO vo : list) {
			System.out.println(vo);
		}
	}
	
}
