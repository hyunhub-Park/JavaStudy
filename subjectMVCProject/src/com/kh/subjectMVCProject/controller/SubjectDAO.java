package com.kh.subjectMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.LandPriceVO;


public class SubjectDAO {
		
	public static final String SUBJECT_SELECT = "SELECT * FROM SUBJECT";
    public static final String SUBJECT_INSERT = "insert into subject(no, num, name) values(subject_seq.nextval, ?, ?)";
    public static final String SUBJECT_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()}";
    public static final String SUBJECT_UPDATE = "UPDATE SUBJECT SET NAME = ? WHERE NUM = ?";
    public static final String SUBJECT_DELETE = "DELETE FROM SUBJECT WHERE NUM = ?";
    public static final String SUBJECT_SORT = "SELECT * FROM SUBJECT ORDER BY NUM";
	
	public ArrayList<LandPriceVO> subjectSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> subjectList = new ArrayList<LandPriceVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SUBJECT_SELECT);

		if(rs.next()) {
			do{
				int no = rs.getInt("NO");
				String num = rs.getString("NUM");
				String name = rs.getString("NAME");
				LandPriceVO svo = new LandPriceVO(no, num, name); 
				subjectList.add(svo);
			}while (rs.next());
		}else {
			subjectList = null; 
		}
		DBUtility.dbClose(con, stmt, rs);
		return subjectList;
	}
	
	public boolean subjectInsert(LandPriceVO svo) throws SQLException  {
		//Conection
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(SUBJECT_INSERT);
		pstmt.setString(1, svo.getNum());
		pstmt.setString(2, svo.getName());
		
		int result = pstmt.executeUpdate();
		
		DBUtility.dbClose(con, pstmt);
		successFlag = (result != 0 ) ? true : false;
		return successFlag; 
	}

	public static boolean subjectUpdate(LandPriceVO svo) throws SQLException {
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SUBJECT_UPDATE);
		pstmt.setString(1, svo.getName());
		pstmt.setString(2, svo.getNum());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0 ) ? true : false;
		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}

	public static boolean subjectDelete(LandPriceVO svo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SUBJECT_DELETE);
		pstmt.setString(1, svo.getNum());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;
		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}

	public static ArrayList<LandPriceVO> subjectSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> subjectList = new ArrayList<LandPriceVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SUBJECT_SORT);

		if(rs.next()) {
			do {
				int no = rs.getInt("NO");
				String num = rs.getString("NUM");
				String name = rs.getString("NAME");
				
				LandPriceVO svo = new LandPriceVO(no, num, name); 
				subjectList.add(svo);
			} while (rs.next());
		}else {
			subjectList = null; 
		}

		DBUtility.dbClose(con, stmt, rs);
		return subjectList; 
	}


}








//package com.kh.subjectMVCProject.controller;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import com.kh.subjectMVCProject.model.SubjectVO;
//
//public class SubjectDAO
//{
//	public static final String SUBJECT_SELECT = "SELECT * FROM SUBJECT";
//	public static final String SUBJECT_INSERT = "INSERT INTO SUBJECT(NO, NUM, NAME) VALUES(SUBJECT_SEQ.nextval, ?, ?);";
//	public static final String SUBJECT_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()})";
//	public static final String SUBJECT_UPDATE = "UPDATE SUBJECT SET NAME = ? WHERE NO = ?";
//	public static final String SUBJECT_DELETE = "DELETE FROM SUBJECT WHERE NUM = ?";
//	public static final String SUBJECT_SORT = "SELECT * FROM SUBJECT order by NUM";
//	
//	public static ArrayList <SubjectVO> subjectSelect() throws SQLException
//	{
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList <SubjectVO> subjectList = new ArrayList <SubjectVO>();
//		
//		con = DBUtility.dbCon();
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(SUBJECT_SELECT);
//		
//		if(rs.next()) 
//		{
//			do{
//				int no = rs.getInt("NO");
//				String num = rs.getString("NUM");
//				String name = rs.getString("NAME");
//				
//				SubjectVO svo = new SubjectVO(no, num, name); 
//				subjectList.add(svo);
//			} while (rs.next());
//		}else 
//		{
//			subjectList = null; 
//		}
//		// stuListPrint(studentList);
//		DBUtility.dbClose(con, stmt, rs);
//		
//		return subjectList;
//	}
//	
//	public static boolean subjectInsert(SubjectVO svo) throws SQLException
//	{
//		// Connection
//		boolean successFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		// 1 Load, 2. connect
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(SUBJECT_INSERT);
//		pstmt.setString(1, svo.getNum());
//		pstmt.setString(2, svo.getName());
//
//		int result = pstmt.executeUpdate();
//
//		DBUtility.dbClose(con, pstmt);
//		
//		// 이게 진짜 출력화면임.
//		successFlag = ((result != 0) ? true : false);
//		
//		return successFlag;
//	}
//
//	public static boolean subjectUpdate(SubjectVO svo) throws SQLException
//	{
//		boolean successFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(SUBJECT_UPDATE);
//		pstmt.setString(1, svo.getName());
//		pstmt.setString(2, svo.getNum());
//
//		int result = pstmt.executeUpdate();
//		
//		successFlag = ((result != 0) ? true : false);
//
//		DBUtility.dbClose(con, pstmt);
//		return successFlag;
//	}
//
//	public static boolean subjectDelete(SubjectVO svo) throws SQLException
//	{
//		boolean successFlag = false;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(SUBJECT_DELETE);
//		pstmt.setString(1, svo.getNum());
//		int result = pstmt.executeUpdate();
//
//		successFlag = ((result != 0) ? true : false);
//
//		DBUtility.dbClose(con, pstmt);
//		return successFlag;
//	}
//	
//	public static ArrayList <SubjectVO> subjectSort() throws SQLException
//	{
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList <SubjectVO> subjectList = new ArrayList <SubjectVO>();
//
//		con = DBUtility.dbCon();
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(SUBJECT_SORT);
//
//		if (rs.next())
//		{
//			do
//			{
//				// order by를 통해 이미 정렬된 값이 나옴.
//				int no = rs.getInt("NO");
//				String num = rs.getString("NUM");
//				String name = rs.getString("NAME");
//				
//				SubjectVO svo = new SubjectVO(no, num, name);
//				subjectList.add(svo);
//				
//			} while (rs.next());
//		} else
//		{
//			subjectList = null;
//		}
//		
//		DBUtility.dbClose(con, stmt, rs);
//		return subjectList;
//	}
//}