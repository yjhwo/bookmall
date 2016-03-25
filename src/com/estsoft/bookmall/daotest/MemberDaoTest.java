package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmall.dao.MemberDao;
import com.estsoft.bookmall.vo.MemberVO;

public class MemberDaoTest {

	public static void main(String[] args) {
		
		insertTest();

		getListTest();
	}

	private static void insertTest() {
		MemberVO memberVo = new MemberVO(null, "홍길동", "010-1111-2222", "aaa@naver.com", "abcd");
		MemberDao memberDao = new MemberDao();
		memberDao.insert(memberVo);
		
		memberVo = new MemberVO(null, "둘리", "010-1111-3333", "bbb@naver.com", "1234");
		memberDao = new MemberDao();
		memberDao.insert(memberVo);
	}

	private static void getListTest() {
		List<MemberVO> list = new MemberDao().getList();

		for (MemberVO vo : list) {
			System.out.println(vo);
		}
	}

}
