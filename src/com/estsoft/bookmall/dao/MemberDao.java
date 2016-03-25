package com.estsoft.bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmall.vo.MemberVO;

public class MemberDao {
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
	
	public List<MemberVO> getList(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT no, name, phone, email, password FROM member";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
				
				MemberVO memberVo = new MemberVO(no, name, phone, email, password);	
				list.add(memberVo);
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
	
	public void insert(MemberVO memberVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = getConnection();

			// 3. Statement 준비 - 값이 binding 된다.
			String sql = "insert into member values(null,?,?,?,?)";	// 번호,이름,전화번호,이메일,비밀번호
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setString(1, memberVo.getName());
			pstmt.setString(2, memberVo.getPhone());
			pstmt.setString(3, memberVo.getEmail());
			pstmt.setString(4, memberVo.getPassword());
			
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
