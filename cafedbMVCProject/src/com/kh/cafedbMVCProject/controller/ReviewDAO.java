package com.kh.cafedbMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.cafedbMVCProject.model.OrderCheckAllVO;
import com.kh.cafedbMVCProject.model.ReviewVO;

public class ReviewDAO
{
	public static final String REVIEW_SELECT = "SELECT M.NO, M.DRINK, M.SNACK, M.DESSERT, R.REVIEW FROM REVIEW R INNER JOIN MENU M ON R.M_NUM=M.NO WHERE M_NUM=R.M_NUM";
	// public static final String ORDER_CHECK_ALL = "SELECT M_NUM, M.DRINK, M.SNACK, M.DESSERT, R.REVIEW FROM REVIEW R INNER JOIN MENU M ON R.M_NUM=M.NO";
	public static final String ORDER_CHECK_ALL = "SELECT M_NUM, M.DRINK, NVL(M.SNACK, ' ') AS SNACK, NVL(M.DESSERT, ' ') AS DESSERT, R.REVIEW FROM REVIEW R INNER JOIN MENU M ON R.M_NUM=M.NO";
	public static final String REVIEW_INSERT = "INSERT INTO REVIEW(M_NUM, REVIEW) VALUES(?, ?)";
	
	public static ArrayList <ReviewVO> reviewSelect() throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList <ReviewVO> reviewList = new ArrayList <ReviewVO>();
		
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(REVIEW_SELECT);
		
		if(rs.next()) 
		{
			do{
				int no = rs.getInt("M_NUM");
				
				String review = rs.getString("REVIEW");
				
				ReviewVO rvo = new ReviewVO(no, review);

				reviewList.add(rvo);
			} while (rs.next());
		}else 
		{
			reviewList = null;
		}
		DBUtility.dbClose(con, stmt, rs);
		
		return reviewList;
	}
	
	// 해당 학과와 학생정보를 조인해 정보 가져오기.
	public ArrayList <OrderCheckAllVO> orderCheckAllSelect()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList <OrderCheckAllVO> orderCheckAllList = new ArrayList <OrderCheckAllVO>();

		con = DBUtility.dbCon();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(ORDER_CHECK_ALL);		

			if (rs.next())
			{
				do {
					int m_num = rs.getInt("M_NUM");
					String drink = rs.getString("DRINK");
					String snack = rs.getString("SNACK");
					String dessert = rs.getString("DESSERT");
					String review = rs.getString("REVIEW");

					OrderCheckAllVO ocavo = new OrderCheckAllVO(m_num, drink, snack, dessert, review);
					orderCheckAllList.add(ocavo);
					
				} while (rs.next());
			} else {
			orderCheckAllList = null;
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString() + "DAO 오류.");
		} finally
		{
			DBUtility.dbClose(con, stmt, rs);
		}
		return orderCheckAllList;
	}
	
	public static boolean reviewInsert(ReviewVO rvo) throws SQLException
	{
		// Connection
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(REVIEW_INSERT);
		pstmt.setInt(1, rvo.getM_num());
		pstmt.setString(2, rvo.getReview());

		int result = pstmt.executeUpdate();

		DBUtility.dbClose(con, cstmt);
		
		// 이게 진짜 출력화면임.
		successFlag = ((result != 0) ? true : false);
		
		return successFlag;
	}

}
