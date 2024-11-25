package com.kh.cafedbMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.cafedbMVCProject.model.CafeMenuVO;


public class CafeMenuDAO
{
	// public static final String MENU_SELECT = "SELECT * FROM MENU"; ORDER BY NO;절 추가해보기.
	// public static final String MENU_SELECT = "SELECT * FROM MENU ORDER BY NO";
	public static final String MENU_SELECT = "SELECT NO, DRINK, NVL(SNACK, ' ') AS SNACK, NVL(DESSERT, ' ')AS DESSERT FROM MENU ORDER BY NO";
	public static final String MENU_INSERT = "INSERT INTO MENU VALUES(MENU_NO_SEQ.nextval, ?, ?, ?)";
	
	public static ArrayList <CafeMenuVO> cafeMenuSelect() throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList <CafeMenuVO> cafeMenuList = new ArrayList <CafeMenuVO>();
		
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(MENU_SELECT);
		
		if(rs.next()) 
		{
			do{
				int no = rs.getInt("NO");
				String drink = rs.getString("DRINK");
				String snack = rs.getString("SNACK");
				String dessert = rs.getString("DESSERT");
				
				CafeMenuVO cmvo = new CafeMenuVO(no, drink, snack, dessert); 
				cafeMenuList.add(cmvo);
			} while (rs.next());
		}else 
		{
			cafeMenuList = null;
		}
		// stuListPrint(studentList);
		DBUtility.dbClose(con, stmt, rs);
		
		return cafeMenuList;
	}
	
	public static boolean cafeMenuInsert(CafeMenuVO cmvo) throws SQLException
	{
		// Connection
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(MENU_INSERT);
		pstmt.setString(1, cmvo.getDrink());
		pstmt.setString(2, cmvo.getSnack());
		pstmt.setString(3, cmvo.getDessert());

		int result = pstmt.executeUpdate();

		DBUtility.dbClose(con, cstmt);
		
		// 이게 진짜 출력화면임.
		successFlag = ((result != 0) ? true : false);
		
		return successFlag;
	}
}
