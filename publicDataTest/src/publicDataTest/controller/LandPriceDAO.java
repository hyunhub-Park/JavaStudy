package publicDataTest.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import publicDataTest.model.LandPriceVO;

public class LandPriceDAO
{
		
	public final String LANDPRICE_SELECT = "SELECT * FROM LANDPRICE";
	public final String LANDPRICE_CHECK_NODENO_SELECT = "SELECT COUNT(*) AS COUNT FROM LANDPRICE WHERE NODENO = ?";
    public final String LANDPRICE_INSERT = "insert into LANDPRICE values(?, ?, ?, ?, ?)";
    public final String LANDPRICE_UPDATE = "UPDATE LANDPRICE SET GPSLATI = ?, GPSLONG = ?, NODEID = ?, NODENM = ? WHERE NODENO = ?";
    public final String LANDPRICE_DELETE = "DELETE FROM LANDPRICE WHERE NODENO = ?";
    // public final String LANDPRICE_SORT = "SELECT * FROM LANDPRICE ORDER BY NODENM";
	
	public ArrayList <LandPriceVO> landPriceSelect()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList <LandPriceVO> landpriceVOList = new ArrayList <LandPriceVO>();

		con = DBUtility.dbCon();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(LANDPRICE_SELECT);

			if(rs.next())
			{
				do
				{
					int nodeno = rs.getInt("NODENO");
					double gpslati = rs.getDouble("GPSLATI");
					double gpslong = rs.getDouble("GPSLONG");
					String nodeid = rs.getString("NODEID");
					String nodenm = rs.getString("NODENM");
					LandPriceVO lvo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nodenm);
					
					landpriceVOList.add(lvo);
				} while (rs.next());
			} else
			{
				landpriceVOList = null; 
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBUtility.dbClose(con, stmt, rs);
		}
		
		
		return landpriceVOList;
	}
	
	public boolean landPriceInsert(LandPriceVO lvo) throws SQLException
	{
		//Conection
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(LANDPRICE_INSERT);
		pstmt.setInt(1, lvo.getNodeno());
		pstmt.setDouble(2, lvo.getGpslati());
		pstmt.setDouble(3, lvo.getGpslong());
		pstmt.setString(4, lvo.getNodeId());
		pstmt.setString(5, lvo.getNodenm());
		
		int result = pstmt.executeUpdate();
		
		DBUtility.dbClose(con, pstmt);
		successFlag = (result != 0 ) ? true : false;
		return successFlag; 
	}

	public boolean landPriceUpdate(LandPriceVO lvo) throws SQLException
	{
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(LANDPRICE_UPDATE);
		pstmt.setDouble(1, lvo.getGpslati());
		pstmt.setDouble(2, lvo.getGpslong());
		pstmt.setString(3, lvo.getNodeId());
		pstmt.setString(4, lvo.getNodenm());
		pstmt.setInt(5, lvo.getNodeno());
		
		int result = pstmt.executeUpdate();
		
		DBUtility.dbClose(con, pstmt);
		successFlag = (result != 0 ) ? true : false;
		return successFlag; 
	}

	public boolean landPriceDelete(LandPriceVO lpvo)
	{
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_DELETE);
			pstmt.setInt(1, lpvo.getNodeno());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false ;
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag; 
	}

	public int landPriceCheckNodeNoSelect(LandPriceVO lpvo)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList <LandPriceVO> landpriceVOList = new ArrayList <LandPriceVO>();
		int count = 0;
		
		try
		{
		con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_CHECK_NODENO_SELECT);
			pstmt.setInt(1, lpvo.getNodeno());
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtility.dbClose(con, pstmt, rs);
		}
		
		return count;
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