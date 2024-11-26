package com.kh.subjectMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.LessonVO;


public class LessonDAO
{
    public final String LESSON_SELECT = "SELECT * FROM LESSON";
    public final String LESSON_SELECT_SORT = "SELECT * FROM LESSON ORDER BY NAME";
    public final String LESSON_DELETE = "DELETE FROM LESSON WHERE NO = ?";
    public final String LESSON_UPDATE = "UPDATE LESSON SET ABBRE=?, NAME=? WHERE NO=?";
    public final String LESSON_INSERT = "INSERT INTO LESSON VALUES(LESSON_SEQ.nextval, ?, ?)";
    
	public ArrayList <LessonVO> lessonSelect(LessonVO lvo)	// select시 출력문을 돌려주는 것. 출력 레코드를 리턴. Read.
	{	// 조건을 걸 시, 매개변수 필요.
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		ResultSet rs = null;	// 결과값 가져와야하니. (커서, result set, 레코드 셋. 반복문 써서 가져오는 것.) 오라클에서 결과물을 받는 객체.
		
		ArrayList <LessonVO> lessonList = new ArrayList <LessonVO>();	// 담아야 함.
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT);
			rs = pstmt.executeQuery();	// db에 가서 실행하면 결과값을 줌.
			
			while(rs.next())
			{
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				LessonVO lessonVO = new LessonVO(no, abbre, name);
				lessonList.add(lessonVO);
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt, rs);
		}

		return lessonList;
	}
	
	public ArrayList <LessonVO> lessonSelectSort(LessonVO lvo)
	{
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		ResultSet rs = null;	// 결과값 가져와야하니. (커서, result set, 레코드 셋. 반복문 써서 가져오는 것.) 오라클에서 결과물을 받는 객체.
		
		ArrayList <LessonVO> lessonList = new ArrayList <LessonVO>();	// 담아야 함.
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT_SORT);
			rs = pstmt.executeQuery();	// db에 가서 실행하면 결과값을 줌.
			
			while(rs.next())
			{
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				LessonVO lessonVO = new LessonVO(no, abbre, name);
				lessonList.add(lessonVO);
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt, rs);
		}

		return lessonList;
	}
    
	public boolean lessonDelete (LessonVO lvo)	// 레코드를 삭제. Delete.
									// 삭제가 잘 됐는지 확인해봐야 하니 boolean.
	{
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		boolean successFlag = false;
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_DELETE);
			pstmt.setInt(1, lvo.getNo());
			int count = pstmt.executeUpdate();
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
	
	public boolean lessonUpdate (LessonVO lvo)	// 레코드를 수정. Update.
	{
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		boolean successFlag = false;
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_UPDATE);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());
			pstmt.setInt(3, lvo.getNo());

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
    
	public boolean lessonInsert (LessonVO lvo)	// 레코드를 삽입. Insert.
	{
		Connection con = null;	// db연결.
		PreparedStatement pstmt = null;	// db접속해서 쿼리문을 날려야하니까.
		boolean successFlag = false;
		
		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_INSERT);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());

			int count = pstmt.executeUpdate();	// 1개 실행됐으면 1, 아무것도 안했으면 0 나올 것.
			successFlag = count != 0 ? true : false;
			// pstmt.execute("commit"); --> 나중에 트랜잭션으로 하기.
			
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


//	public static final String LESSON_SELECT = "SELECT * FROM STUDENT";
//	public static final String LESSON_INSERT = "INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
//	public static final String LESSON_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()}";
//	public static final String LESSON_UPDATE = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ?";
//	public static final String LESSON_DELETE = "DELETE FROM STUDENT WHERE NO = ?";
//	public static final String LESSON_SORT = "SELECT *FROM STUDENT ORDER BY RANK";

//public static ArrayList<StudentVO> studentSelect() throws SQLException {
	//Connection con = null;
	//Statement stmt = null;
	//ResultSet rs = null;
	//ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
	//
	//con = DBUtility.dbCon();
	//stmt = con.createStatement();
	//rs = stmt.executeQuery(LESSON_SELECT);
	//
	//if(rs.next()) {
//		do{
//			int no = rs.getInt("NO");
//			String name = rs.getString("NAME");
//			int kor = rs.getInt("KOR");
//			int eng = rs.getInt("ENG");
//			int mat = rs.getInt("MAT");
//			int total = rs.getInt("TOTAL");
//			int ave = rs.getInt("AVE");
//			int rank = rs.getInt("RANK");
	//
//			StudentVO stu = new StudentVO();
//			studentList.add(stu);
//		}while (rs.next());
	//}else {
//		studentList = null; 
	//}
	//DBUtility.dbClose(con, stmt, rs);
	//return studentList;
	//}	
//	public static boolean studentInsert(StudentVO svo) throws SQLException {
//		// Conection
//		boolean successFlag = false; 
//		Connection con = null;
//		CallableStatement cstmt = null;
//		PreparedStatement pstmt = null;
//
//		// 1 Load, 2. connect
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(LESSON_INSERT);
//		pstmt.setString(1, svo.getName());
//		
//
//		int result1 = pstmt.executeUpdate();
//		System.out.println((result1 != 0) ? "입력성공" : "입력실패");
//
//		cstmt = con.prepareCall(LESSON_CALL_RANK_PROC);
//		int result2 = cstmt.executeUpdate();
//		System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");
//
//		DBUtility.dbClose(con, pstmt, cstmt);
//		successFlag = (result1 != 0 && result2 != 0) ? true : false;
//		
//		return successFlag; 
//	}
//
//	public static boolean studentUpdate(StudentVO svo) throws SQLException {
//		boolean successFlag = false; 
//		Connection con = null;
//		CallableStatement cstmt = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//		pstmt = con.prepareStatement(LESSON_UPDATE);
//		pstmt.setString(1, svo.getName());
//		
//
//		int result1 = pstmt.executeUpdate();
//		cstmt = con.prepareCall(LESSON_CALL_RANK_PROC);
//		int result2 = cstmt.executeUpdate();
//		
//		successFlag = (result1 != 0 && result2 != 0) ? true : false;
//
//		DBUtility.dbClose(con, pstmt, cstmt);
//		return successFlag; 
//	}
//
//	public static boolean studentDelete(StudentVO svo) throws SQLException {
//		boolean successFlag =false; 
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//		pstmt = con.prepareStatement(LESSON_DELETE);
//		pstmt.setInt(1, svo.getNo());
//		int result = pstmt.executeUpdate();
//		successFlag = (result != 0) ? true : false ;
//
//		DBUtility.dbClose(con, pstmt);
//		return successFlag; 
//	}
//
//	public static ArrayList<StudentVO> studentSort() throws SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
//
//		con = DBUtility.dbCon();
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(LESSON_SORT);
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
//				StudentVO stu = new StudentVO();
//				studentList.add(stu);
//			} while (rs.next());
//		}else {
//			studentList = null; 
//		}
//
//		DBUtility.dbClose(con, stmt, rs);
//		return studentList; 
//	}






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
//import com.kh.subjectMVCProject.model.StudentVO;
//
//public class LessonDAO
//{
//	public static final String LESSON_SELECT = "SELECT * FROM STUDENT";
//	public static final String LESSON_INSERT = "INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT001_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
//	public static final String LESSON_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()})";
//	public static final String LESSON_UPDATE = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ";
//	public static final String LESSON_DELETE = "DELETE FROM STUDENT WHERE NO = ?";
//	public static final String LESSON_SORT = "SELECT * FROM STUDENT order by RANK";
//	
//	public static ArrayList <StudentVO> studentSelect() throws SQLException
//	{
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList <StudentVO> studentList = new ArrayList <StudentVO>();
//		
//		con = DBUtility.dbCon();
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(LESSON_SELECT);
//		
//		/*while (rs.next())
//		{
//			int no = rs.getInt("NO");
//			String name = rs.getString("NAME");
//			int kor = rs.getInt("KOR");
//			int eng = rs.getInt("ENG");
//			int mat = rs.getInt("MAT");
//			int total = rs.getInt("TOTAL");
//			int ave = rs.getInt("AVE");
//			int rank = rs.getInt("RANK");
//			
//			StudentVO stu = new StudentVO(no, name, kor, eng, mat, total, ave, rank);
//			studentList.add(stu);
//		}*/
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
//				StudentVO stu = new StudentVO();
//				studentList.add(stu);
//			}while (rs.next());
//		}else {
//			studentList = null; 
//		}
//		// stuListPrint(studentList);
//		DBUtility.dbClose(con, stmt, rs);
//		
//		return studentList;
//	}
//	
//	public static boolean studentInsert(StudentVO svo) throws SQLException
//	{
//		// Connection
//		boolean successFlag = false;
//		Connection con = null;
//		CallableStatement cstmt = null;
//		PreparedStatement pstmt = null;
//
//		// 1 Load, 2. connect
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(LESSON_INSERT);
//		pstmt.setString(1, svo.getName());
//
//		int result1 = pstmt.executeUpdate();
//		//System.out.println((result1 != 0) ? "입력성공" : "입력실패");
//
//		cstmt = con.prepareCall(LESSON_CALL_RANK_PROC);
//		int result2 = cstmt.executeUpdate();
//		//System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");
//
//		DBUtility.dbClose(con, cstmt);
//		
//		// 이게 진짜 출력화면임.
//		successFlag = ((result1 != 0 && result2 != 0) ? true : false);
//		
//		return successFlag;
//	}
//
//	public static boolean stuUpdate(StudentVO svo) throws SQLException
//	{
//		boolean successFlag = false;
//		Connection con = null;
//		CallableStatement cstmt = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(LESSON_UPDATE);
//		pstmt.setString(1, svo.getName());
//
//		pstmt.setInt(5, svo.getNo());
//
//		int result1 = pstmt.executeUpdate();
//		cstmt = con.prepareCall(LESSON_CALL_RANK_PROC);
//		
//		int result2 = cstmt.executeUpdate();
//		// System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");
//		
//		successFlag = ((result1 != 0 && result2 != 0) ? true : false);
//
//		DBUtility.dbClose(con, pstmt, cstmt);
//		return successFlag;
//	}
//
//	public static boolean studentDelete(StudentVO svo) throws SQLException
//	{
//		boolean successFlag = false;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		con = DBUtility.dbCon();
//
//		pstmt = con.prepareStatement(LESSON_DELETE);
//		pstmt.setInt(1, svo.getNo());
//		int result = pstmt.executeUpdate();
//
//		successFlag = ((result != 0) ? true : false);
//
//		DBUtility.dbClose(con, pstmt);
//		return successFlag;
//	}
//	
//	public static ArrayList <StudentVO> studentSort() throws SQLException
//	{
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList <StudentVO> studentList = new ArrayList<StudentVO>();
//
//		con = DBUtility.dbCon();
//		stmt = con.createStatement();
//		rs = stmt.executeQuery(LESSON_SORT);
//
//		while (rs.next())
//		{	// order by를 통해 이미 정렬된 값이 나옴.
//			int no = rs.getInt("NO");
//			String name = rs.getString("NAME");
//			int kor = rs.getInt("KOR");
//			int eng = rs.getInt("ENG");
//			int mat = rs.getInt("MAT");
//			int total = rs.getInt("TOTAL");
//			int ave = rs.getInt("AVE");
//			int rank = rs.getInt("RANK");
//
//			StudentVO stu = new StudentVO();
//			studentList.add(stu);
//		}
//
//		DBUtility.dbClose(con, stmt, rs);
//		return studentList;
//	}
//}