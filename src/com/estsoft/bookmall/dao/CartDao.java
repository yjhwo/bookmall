package com.estsoft.bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmall.vo.CartVO;

public class CartDao {
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
	
	public List<CartVO> getList(){
		List<CartVO> list = new ArrayList<CartVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT customer_no, book_no, count FROM cart";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long customer_no = rs.getLong(1);
				Long book_no = rs.getLong(2);
				Long count = rs.getLong(3);
				
				CartVO cartVo = new CartVO(customer_no, book_no, count);	
				list.add(cartVo);
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
	
	public void insert(CartVO cartVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "insert into cart values(?,?,?)";	// 고객번호, 서적번호, 수량
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setLong(1, cartVo.getCustomer_no());
			pstmt.setLong(2, cartVo.getBook_no());
			pstmt.setLong(3, cartVo.getCount());
			
			pstmt.executeUpdate();

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
