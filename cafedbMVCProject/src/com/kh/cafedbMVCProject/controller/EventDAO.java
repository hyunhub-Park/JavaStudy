package com.kh.cafedbMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.cafedbMVCProject.model.EventVO;

public class EventDAO
{
	public static final String EVENT_INSERT = "INSERT INTO EVENT(NO, AREA, COUNT) VALUES(?, ?, ?)";
	
	public static ArrayList <EventVO> eventSelect() throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList <EventVO> eventList = new ArrayList <EventVO>();
		
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(EVENT_INSERT);
		
		if(rs.next()) 
		{
			do{
				int no = rs.getInt("NO");
				String area = rs.getString("AREA");
				int count = rs.getInt("COUNT");
				
				EventVO evo = new EventVO(no, area, count);
				eventList.add(evo);
			} while (rs.next());
		}else 
		{
			eventList = null;
		}
		// stuListPrint(studentList);
		DBUtility.dbClose(con, stmt, rs);
		
		return eventList;
	}
	
	public static boolean eventInsert(EventVO evo) throws SQLException
	{
		// Connection
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(EVENT_INSERT);
		pstmt.setInt(1, evo.getNo());
		pstmt.setString(2, evo.getArea());
		pstmt.setInt(3, evo.getCount());

		int result = pstmt.executeUpdate();

		DBUtility.dbClose(con, cstmt);
		
		// 이게 진짜 출력화면임.
		successFlag = ((result != 0) ? true : false);
		
		return successFlag;
	}
}