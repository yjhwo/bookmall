package com.estsoft.bookmall.dao;

import java.sql.*;
import java.util.*;

import com.estsoft.bookmall.vo.CartVO;
import com.estsoft.bookmall.vo.OrderVO;

public class OrderDao {
	private Long customerNo;
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;

		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connection 얻기
			String url = "jdbc:mysql://localhost/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다." + e);
		} 

		return conn;
	}
	
	public List<OrderVO> getOrderBookList(){		// 주문서적 리스트
		List<OrderVO> list = new ArrayList<OrderVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
	
			String sql = "SELECT order_no, book_no, count FROM orderbook";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long order_no = rs.getLong(1);
				Long book_no = rs.getLong(2);
				Long count = rs.getLong(3);
		
				OrderVO orderVo = new OrderVO(order_no, book_no, count);
				list.add(orderVo);
				
			}
			
		}catch(SQLException ex){
			System.out.println("error:" + ex);
		}finally { 
			try {
				if (rs != null)			rs.close();
				if (stmt != null)		stmt.close();
				if (conn != null)		conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<CartVO> getCartList(){		// 2. 장바구니에서 정보 가져오기
		CartDao c = new CartDao();
		
		return c.getList();
	}
	
	public List<CartVO> getCart(){				// 4. 장바구니에서 정보 1개 리턴 -> 2개 다
		// 가져온 cartVO에서 고객번호 같은 것 비교, 값 들 중 제일 위에 값 리턴하기... -> 값 다 가져와야 함
		
		List<CartVO> list = getCartList();	// 서적번호 및 수량 추출
		List<CartVO> tmp = new ArrayList<CartVO>();	// temp
		
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getCustomer_no() == customerNo){
				tmp.add(list.get(i));
			}
		}
		return tmp;
	}
	
	public void insertOrderBook(){			// 3. 주문서적에  1~2번에서 구한 데이터 결과 insert
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = getConnection();

			String sql = "insert into orderbook values(?,?,?)";	// 주문번호, 서적번호, 수량
			
			pstmt = conn.prepareStatement(sql);

//			CartVO c = getCart();							// 장바구니에서 고객 번호 같은 정보 1개 리턴
			
			List<CartVO> list = getCart();					// 같은 정보 다 리턴
			
			for(int i=0; i<list.size(); i++){
				Long orderNo = getOrderNo(customerNo);			// 주문번호 추출
				
				pstmt.setLong(1, orderNo);
				pstmt.setLong(2, list.get(i).getBook_no());
				pstmt.setLong(3, list.get(i).getCount());
				
				pstmt.executeUpdate();
			}
			

		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally { 
			try {
				if (pstmt != null)			pstmt.close();
				if (conn != null)			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public Long getOrderNo(Long customerNo){	// 1. 고객번호를 가지고 주문번호 가져오기
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long orderNo = (long) 0;
		
		try{
			conn = getConnection();
			
//			"SELECT a.no, a.title, IF(a.state='rent','대여중','재고있음'), b.name FROM book a, author b "+
//			"WHERE a.author_no = b.no AND a.no = ?"
			String sql = "SELECT a.no FROM orders a, cart b "+
						 "WHERE a.customer_no = b.customer_no AND a.customer_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, customerNo);

			rs = pstmt.executeQuery();
			
			if(rs.next()){
				orderNo = rs.getLong(1);
			}
			
		}catch(SQLException ex){
			System.out.println("error:" + ex);
		}finally { 
			try {
				if (rs != null)			rs.close();
				if (stmt != null)		stmt.close();
				if (conn != null)		conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderNo;
		
	}
	
	public List<OrderVO> getOrderList(){		// 주문 리스트
		List<OrderVO> list = new ArrayList<OrderVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
						 
			String sql = "SELECT a.no, b.name, b.email, a.order_price, a.address, a.customer_no "+
						"FROM orders a, member b "+
						"WHERE a.customer_no = b.no"; 	// 주문번호,주문자(이름/이메일),결제금액,배송지 
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				Long order_price = rs.getLong(4);
				String address = rs.getString(5);
				Long customer_no = rs.getLong(6);
				
				OrderVO orderVo = new OrderVO(no, name, email, order_price, address,customer_no);
				list.add(orderVo);
			}
			
		}catch(SQLException ex){
			System.out.println("error:" + ex);
		}finally { 
			try {
				if (rs != null)			rs.close();
				if (stmt != null)		stmt.close();
				if (conn != null)		conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void insert(OrderVO orderVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "insert into orders values(null,?,?,?)";	// 번호, 결제금액, 배송지, 고객번호
			
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setLong(1, orderVo.getOrder_price());
			pstmt.setString(2, orderVo.getAddress());
			pstmt.setLong(3, orderVo.getCustomer_no());
			
			customerNo = orderVo.getCustomer_no();
			
			pstmt.executeUpdate();

			insertOrderBook();										// 주문 서적 테이블에 넣기!!!
			
		} catch (SQLException ex) {
			System.out.println("SQL 오류" + ex);
		} finally { 
			try {
				if (pstmt != null)			pstmt.close();
				if (conn != null)			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
