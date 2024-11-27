package com.kh.subjectMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeDAO
{
	public static final String TRAINEE_SELECT = "SELECT * FROM TRAINEE";
	public static final String TRAINEE_ALL_SELECT = "SELECT T.NO, T.SECTION, T.REGISTDATE, S.NUM, S.NAME AS SNAME, L.ABBRE, L.NAME AS LNAME FROM TRAINEE T INNER JOIN STUDENT S ON T.S_NUM=S.NUM\r\n"
			+ "INNER JOIN LESSON L ON T.ABBRE=L.ABBRE ORDER BY T.NO";
	public static final String TRAINEE_SELECT_SORT = "SELECT * FROM TRAINEE ORDER BY S_NUM";
	public static final String TRAINEE_DELETE = "DELETE FROM TRAINEE WHERE NO=?";
	public static final String TRAINEE_UPDATE = "UPDATE TRAINEE SET S_NUM=?, ABBRE=?, SECTION=? WHERE NO=?";
    public static final String TRAINEE_INSERT = "INSERT INTO TRAINEE VALUES(TRAINEE_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
    // public static final String TRAINEE_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()}";
	
	
	public static boolean traineeInsert(TraineeVO tvo)	// TRAINEE 테이블에서 insert레코드 삽입.
	{
		// Conection
		Connection con = null;	// 오라클 접속 관문.
		PreparedStatement pstmt = null;	// 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false; 

		// 1 Load, 2. connect
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_INSERT);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre());
			pstmt.setString(3, tvo.getSection());
			
			int count = pstmt.executeUpdate();
			successFlag = count!=0 ? true : false;
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt);
		}
		
		return successFlag; 
	}

	public boolean traineeUpdate (TraineeVO tvo)	// 레코드를 수정. Update.
	{
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		
		boolean successFlag = false;
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_UPDATE);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre());
			pstmt.setString(3, tvo.getSection());
			pstmt.setInt(4, tvo.getNo());

			int count = pstmt.executeUpdate();	// 1개 실행됐으면 1, 아무것도 안했으면 0 나올 것.
			successFlag = count != 0 ? true : false;
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	}

	public ArrayList <TraineeVO> traineeSelectSort(TraineeVO tvo)
	{		
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		ResultSet rs = null;	// 결과값 가져와야하니. (커서, result set, 레코드 셋. 반복문 써서 가져오는 것.) 오라클에서 결과물을 받는 객체.
		
		ArrayList <TraineeVO> traineeList = new ArrayList <TraineeVO>();	// 담아야 함.
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_SELECT_SORT);
			rs = pstmt.executeQuery();	// db에 가서 실행하면 결과값을 줌.
			
			while(rs.next())
			{
				int no = rs.getInt("NO");
				String s_num = rs.getString("S_NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date registDate = rs.getDate("REGISTDATE");
				TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, registDate);
				traineeList.add(traineeVO);
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt, rs);
		}

		return traineeList;
	}
	
	
	public ArrayList <TraineeVO> traineeSelect(TraineeVO tvo)	// select시 출력문을 돌려주는 것. 출력 레코드를 리턴. Read.
	{	// 조건을 걸 시, 매개변수 필요.
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		ResultSet rs = null;	// 결과값 가져와야하니. (커서, result set, 레코드 셋. 반복문 써서 가져오는 것.) 오라클에서 결과물을 받는 객체.
		
		ArrayList <TraineeVO> traineeList = new ArrayList <TraineeVO>();	// 담아야 함.
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_SELECT);
			rs = pstmt.executeQuery();	// db에 가서 실행하면 결과값을 줌.
			
			while(rs.next())
			{
				int no = rs.getInt("NO");
				String s_num = rs.getString("S_NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date registDate = rs.getDate("REGISTDATE");
				TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, registDate);
				traineeList.add(traineeVO);
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt, rs);
		}

		return traineeList;
	}
	
	public ArrayList <TraineeVO> traineeAllSelect(TraineeVO tvo)	// trainee와 join lesson, student
	{	// 조건을 걸 시, 매개변수 필요.
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		ResultSet rs = null;	// 결과값 가져와야하니. (커서, result set, 레코드 셋. 반복문 써서 가져오는 것.) 오라클에서 결과물을 받는 객체.
		
		ArrayList <TraineeVO> traineeList = new ArrayList <TraineeVO>();	// 담아야 함.
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_ALL_SELECT);
			rs = pstmt.executeQuery();	// db에 가서 실행하면 결과값을 줌.
			
			while(rs.next())
			{
				int no = rs.getInt("NO");
				String s_num = rs.getString("NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date registDate = rs.getDate("REGISTDATE");
				String s_name = rs.getString("SNAME");
				String l_name = rs.getString("LNAME");
				TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, registDate, s_name, l_name);
				traineeList.add(traineeVO);
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt, rs);
		}
		// toString Override를 안해놨으니!
		return traineeList;
	}


	public boolean traineeDelete(TraineeVO tvo) // 레코드를 삭제. Delete.
	// 삭제가 잘 됐는지 확인해봐야 하니 boolean.
	{
		Connection con = null; // db연결.
		PreparedStatement pstmt = null; // db접속해서 쿼리문을 날려야하니까.
		boolean successFlag = false;

		// 원래는 자바에서 자동으로 커밋을 해줌. 수동으로 바꾸는 방법은 아래. ↓↓↓↓ setAutoCommit부분.
		try
		{
			con = DBUtility.dbCon();
			con.setAutoCommit(false);	// autoCommit을 수동으로.
			
			pstmt = con.prepareStatement(TRAINEE_DELETE);
			pstmt.setInt(1, tvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = count != 0 ? true : false;
			
			// 수동커밋 후 추가된 부분.
			if (count != 0)
			{
				successFlag = true;
				con.commit();
			} else
			{
				successFlag = false;
				con.rollback();
			}

		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	}
}



//package com.kh.subjectMVCProject.controller;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import com.kh.subjectMVCProject.model.TraineeVO;
//
//
//
//public class TraineeDAO {
//		
//	public static final String TRAINEE_SELECT = "SELECT * FROM STUDENT";
//    public static final String TRAINEE_INSERT = "INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
//    public static final String TRAINEE_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()}";
//    public static final String TRAINEE_UPDATE = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ?";
//    public static final String TRAINEE_DELETE = "DELETE FROM STUDENT WHERE NO = ?";
//    public static final String TRAINEE_SORT = "SELECT *FROM STUDENT ORDER BY RANK";
//	
//	public static ArrayList<TraineeVO> studentSelect() throws SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList<TraineeVO> studentList = new ArrayList<TraineeVO>();
//
//		con = DBUtility.dbCon();
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(TRAINEE_SELECT);
//
//		if(rs.next()) {
//			do{
//				int no = rs.getInt("NO");
//				String name = rs.getString("NAME");
//				int kor = rs.getInt("KOR");
//				int eng = rs.getInt("ENG");
//				int mat = rs.getInt("MAT");
//				int total = rs.getInt("TOTAL");
//				int ave = rs.getInt("AVE");
//				int rank = rs.getInt("RANK");
//
//				TraineeVO stu = new TraineeVO();
//				studentList.add(stu);
//			}while (rs.next());
//		}else {
//			studentList = null; 
//		}
//		DBUtility.dbClose(con, stmt, rs);
//		return studentList;
//	}
//	
//	public static boolean studentInsert(TraineeVO svo) throws SQLException {
//		// Conection
//		boolean successFlag = false; 
//		Connection con = null;
//		CallableStatement cstmt = null;
//		PreparedStatement pstmt = null;
//
//		// 1 Load, 2. connect
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(TRAINEE_INSERT);
//		//pstmt.setString(1, svo.getName());
//		
//
//		int result1 = pstmt.executeUpdate();
//		System.out.println((result1 != 0) ? "입력성공" : "입력실패");
//
//		cstmt = con.prepareCall(TRAINEE_CALL_RANK_PROC);
//		int result2 = cstmt.executeUpdate();
//		System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");
//
//		DBUtility.dbClose(con, pstmt, cstmt);
//		successFlag = (result1 != 0 && result2 != 0) ? true : false;
//		
//		return successFlag; 
//	}
//
//	public static boolean studentUpdate(TraineeVO svo) throws SQLException {
//		boolean successFlag = false; 
//		Connection con = null;
//		CallableStatement cstmt = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//		pstmt = con.prepareStatement(TRAINEE_UPDATE);
//		//pstmt.setString(1, svo.getName());
//		
//
//		int result1 = pstmt.executeUpdate();
//		cstmt = con.prepareCall(TRAINEE_CALL_RANK_PROC);
//		int result2 = cstmt.executeUpdate();
//		
//		successFlag = (result1 != 0 && result2 != 0) ? true : false;
//
//		DBUtility.dbClose(con, pstmt, cstmt);
//		return successFlag; 
//	}
//
//	public static boolean studentDelete(TraineeVO svo) throws SQLException {
//		boolean successFlag =false; 
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//		pstmt = con.prepareStatement(TRAINEE_DELETE);
//		pstmt.setInt(1, svo.getNo());
//		int result = pstmt.executeUpdate();
//		successFlag = (result != 0) ? true : false ;
//
//		DBUtility.dbClose(con, pstmt);
//		return successFlag; 
//	}
//
//	public static ArrayList<TraineeVO> studentSort() throws SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList<TraineeVO> studentList = new ArrayList<TraineeVO>();
//
//		con = DBUtility.dbCon();
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(TRAINEE_SORT);
//
//		if(rs.next()) {
//			do {
//				int no = rs.getInt("NO");
//				String name = rs.getString("NAME");
//				int kor = rs.getInt("KOR");
//				int eng = rs.getInt("ENG");
//				int mat = rs.getInt("MAT");
//				int total = rs.getInt("TOTAL");
//				int ave = rs.getInt("AVE");
//				int rank = rs.getInt("RANK");
//
//				TraineeVO stu = new TraineeVO();
//				studentList.add(stu);
//			} while (rs.next());
//		}else {
//			studentList = null; 
//		}
//
//		DBUtility.dbClose(con, stmt, rs);
//		return studentList; 
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package com.kh.subjectMVCProject.controller;
////
////import java.sql.CallableStatement;
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.sql.Statement;
////import java.util.ArrayList;
////
////import com.kh.subjectMVCProject.model.TraineeVO;
////
////public class TraineeDAO
////{
////	public static final String TRAINEE_SELECT = "SELECT * FROM STUDENT";
////	public static final String TRAINEE_INSERT = "INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT001_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
////	public static final String TRAINEE_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()})";
////	public static final String TRAINEE_UPDATE = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ";
////	public static final String TRAINEE_DELETE = "DELETE FROM STUDENT WHERE NO = ?";
////	public static final String TRAINEE_SORT = "SELECT * FROM STUDENT order by RANK";
////	
////	public static ArrayList <TraineeVO> studentSelect() throws SQLException
////	{
////		Connection con = null;
////		Statement stmt = null;
////		ResultSet rs = null;
////		ArrayList <TraineeVO> studentList = new ArrayList <TraineeVO>();
////		
////		con = DBUtility.dbCon();
////		stmt = con.createStatement();
////		rs = stmt.executeQuery(TRAINEE_SELECT);
////		
////		/*while (rs.next())
////		{
////			int no = rs.getInt("NO");
////			String name = rs.getString("NAME");
////			int kor = rs.getInt("KOR");
////			int eng = rs.getInt("ENG");
////			int mat = rs.getInt("MAT");
////			int total = rs.getInt("TOTAL");
////			int ave = rs.getInt("AVE");
////			int rank = rs.getInt("RANK");
////			
////			StudentVO stu = new StudentVO(no, name, kor, eng, mat, total, ave, rank);
////			studentList.add(stu);
////		}*/
////		
////		if(rs.next()) {
////			do{
////				int no = rs.getInt("NO");
////				String name = rs.getString("NAME");
////				int kor = rs.getInt("KOR");
////				int eng = rs.getInt("ENG");
////				int mat = rs.getInt("MAT");
////				int total = rs.getInt("TOTAL");
////				int ave = rs.getInt("AVE");
////				int rank = rs.getInt("RANK");
////
////				TraineeVO stu = new TraineeVO();
////				studentList.add(stu);
////			}while (rs.next());
////		}else {
////			studentList = null; 
////		}
////		// stuListPrint(studentList);
////		DBUtility.dbClose(con, stmt, rs);
////		
////		return studentList;
////	}
////	
////	public static boolean studentInsert(TraineeVO svo) throws SQLException
////	{
////		// Connection
////		boolean successFlag = false;
////		Connection con = null;
////		CallableStatement cstmt = null;
////		PreparedStatement pstmt = null;
////
////		// 1 Load, 2. connect
////		con = DBUtility.dbCon();
////
////		pstmt = con.prepareStatement(TRAINEE_INSERT);
////		// pstmt.setString(1, svo.getName());
////
////		int result1 = pstmt.executeUpdate();
////		//System.out.println((result1 != 0) ? "입력성공" : "입력실패");
////
////		cstmt = con.prepareCall(TRAINEE_CALL_RANK_PROC);
////		int result2 = cstmt.executeUpdate();
////		//System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");
////
////		DBUtility.dbClose(con, cstmt);
////		
////		// 이게 진짜 출력화면임.
////		successFlag = ((result1 != 0 && result2 != 0) ? true : false);
////		
////		return successFlag;
////	}
////
////	public static boolean stuUpdate(TraineeVO svo) throws SQLException
////	{
////		boolean successFlag = false;
////		Connection con = null;
////		CallableStatement cstmt = null;
////		PreparedStatement pstmt = null;
////
////		con = DBUtility.dbCon();
////
////		pstmt = con.prepareStatement(TRAINEE_UPDATE);
////		// pstmt.setString(1, svo.getName());
////
////		pstmt.setInt(5, svo.getNo());
////
////		int result1 = pstmt.executeUpdate();
////		cstmt = con.prepareCall(TRAINEE_CALL_RANK_PROC);
////		
////		int result2 = cstmt.executeUpdate();
////		// System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");
////		
////		successFlag = ((result1 != 0 && result2 != 0) ? true : false);
////
////		DBUtility.dbClose(con, pstmt, cstmt);
////		return successFlag;
////	}
////
////	public static boolean studentDelete(TraineeVO svo) throws SQLException
////	{
////		boolean successFlag = false;
////		
////		Connection con = null;
////		PreparedStatement pstmt = null;
////
////		con = DBUtility.dbCon();
////
////		pstmt = con.prepareStatement(TRAINEE_DELETE);
////		pstmt.setInt(1, svo.getNo());
////		int result = pstmt.executeUpdate();
////
////		successFlag = ((result != 0) ? true : false);
////
////		DBUtility.dbClose(con, pstmt);
////		return successFlag;
////	}
////	
////	public static ArrayList <TraineeVO> studentSort() throws SQLException
////	{
////		Connection con = null;
////		Statement stmt = null;
////		ResultSet rs = null;
////		ArrayList <TraineeVO> studentList = new ArrayList<TraineeVO>();
////
////		con = DBUtility.dbCon();
////		stmt = con.createStatement();
////		rs = stmt.executeQuery(TRAINEE_SORT);
////
////		while (rs.next())
////		{	// order by를 통해 이미 정렬된 값이 나옴.
////			int no = rs.getInt("NO");
////			String name = rs.getString("NAME");
////			int kor = rs.getInt("KOR");
////			int eng = rs.getInt("ENG");
////			int mat = rs.getInt("MAT");
////			int total = rs.getInt("TOTAL");
////			int ave = rs.getInt("AVE");
////			int rank = rs.getInt("RANK");
////
////			TraineeVO stu = new TraineeVO();
////			studentList.add(stu);
////		}
////
////		DBUtility.dbClose(con, stmt, rs);
////		return studentList;
////	}
////}