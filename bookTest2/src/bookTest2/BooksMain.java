package bookTest2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

public class BooksMain
{
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException
	{
		// 사용자로부터 Books 출력, 입력, 수정, 삭제 요청을 받음.

		boolean exitFlag = false;

		while (!exitFlag)
		{
			printMenu();
			int num = Integer.parseInt(scan.nextLine());

			switch (num)
			{
			case BookMenu.PRINT:
				booksPrint();
				break;

			case BookMenu.INSERT:
				booksInsert();
				break;

			case BookMenu.UPDATE:
				booksUpdate();
				break;

			case BookMenu.DELETE:
				booksDelete();
				break;

			case BookMenu.SALARY_UP_PROC:
				employeeSalaryUpProc();
				break;
				
			case BookMenu.SALARY_UP_FUNC:
				employeeSalaryUpFunc();
				break;

			case BookMenu.EXIT:
				exitFlag = true;
				break;

			default:
				throw new IllegalArgumentException("Unexpected value : " + num);
			}
		}
		System.out.println("The end.");
	}
	
	private static void printMenu()	// PRINT.
	{
		System.out.println("Books Menu (1. print(select??), 2. insert, 3. update, 4. delete, 5. 책값 인상, 6. 책값 조회, 7. 종료)");
		System.out.print(">> ");
	}
	
	public static void booksPrint() throws SQLException
	{
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Books> booksList = new ArrayList<Books>();

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM BOOKS");

		// 4. 테이블 가져오기.
		while (rs.next())
		{
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String publisher = rs.getString("PUBLISHER");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");

			Books books = new Books(id, title, publisher, year, price);
			booksList.add(books);
		}

		// 5. 출력하기
		booksListPrint(booksList);

		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt, rs);
	}
	
	private static void booksListPrint(ArrayList<Books> booksList)
	{
		for (Books books : booksList)
		{
			System.out.println(books.toString());
		}
	}

	private static void booksInsert() throws SQLException // INSERT.
	{
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();
		
		// 트랜잭션 시작점.
		con.setAutoCommit(false);

		// 3. statement
		Books books = new Books(0, "Head First Java", "psh", "2008", 23000);
		// ==============================================================================
		// String publisher = "psh";
		// stmt = con.createStatement();
		// stmt.executeUpdate("INSERT INTO books VALUES (BOOKS_ID_SEQ.nextval, 'Head
		// First Java', publisher, '2008', 23000)");
		pstmt = con.prepareStatement("INSERT INTO books VALUES (books_id_seq.nextval, ?, ?, ?, ?)");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());

		int result = pstmt.executeUpdate();

		/*
		 * int result =
		 * stmt.executeUpdate("INSERT INTO books VALUES (BOOKS_ID_SEQ.nextval, '" +
		 * books.getTitle() + "', '" + books.getPublisher() + "', '" + books.getYear() +
		 * "', " + books.getPrice() + ")");
		 */

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "입력 성공." : "입력 실패.");

		if (result != 0)
		{
			con.commit();
		} else
		{
			con.rollback();
		}
		
		// 6. sql객체 반납.
		DBConnection.dbClose(con, pstmt);
	}
	
	private static void booksUpdate() throws SQLException // UPDATE.
	{

		// Connection
		Connection con = null;
		// Statement stmt = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		// 수정할 데이터 입력.
		Books books = new Books(3, "Java Java Java", "psh", "2004", 44000);
		// stmt = con.createStatement();
		pstmt = con.prepareStatement("UPDATE BOOKS SET TITLE=?, PUBLISHER=?, YEAR=?, PRICE=? WHERE ID=?");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		pstmt.setInt(5, books.getId());
		int result = pstmt.executeUpdate();

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "수정 성공." : "수정 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, pstmt);
	}
	
	private static void booksDelete() throws SQLException // DELETE.
	{
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		System.out.print("삭제 할 번호 입력 >> ");
		int no = Integer.parseInt(scan.nextLine()); // 삭제할 번호 입력받기 위한 변수 선언 및 스캐너.

		pstmt = con.prepareStatement("DELETE FROM BOOKS WHERE ID = ? ");
		pstmt.setInt(1, no);

		int result = pstmt.executeUpdate();

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "삭제 성공." : "삭제 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, pstmt);
	}
	
	private static void employeeSalaryUpProc() throws SQLException // SALARY_UP_PROC.
	{
		// Connection
		Connection con = null;
		CallableStatement cstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		
		System.out.print("인상될 ID 입력 : ");
		int id = Integer.parseInt(scan.nextLine());
		
		System.out.print("인상률 입력(ex 10% -> 1.1) : ");
		int price = Integer.parseInt(scan.nextLine());
		
		// stmt = con.createStatement();
		cstmt = con.prepareCall("{call BOOKS_PROCEDURE(?,?,?)}");
		cstmt.setInt(1, id);
		cstmt.setDouble(2, price);
		// 바인딩을 시키는데, 출력될 데이터 값으로 3번을 바인딩.
		cstmt.registerOutParameter(3, Types.VARCHAR);
		
		int result = cstmt.executeUpdate();
		String message = cstmt.getString(3);
		System.out.println(message);

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "프로시저 성공." : "프로시저 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, cstmt);
		
		// 3. statement
		// 수정할 데이터 입력.
		/*System.out.print("인상될 부서 번호 입력 : ");
		int departmentId = Integer.parseInt(scan.nextLine());
		
		System.out.print("인상률 입력(ex 10% -> 1.1) : ");
		double incrementSalary = Double.parseDouble(scan.nextLine());
		
		// stmt = con.createStatement();
		cstmt = con.prepareCall("{call EMP_PROCEDURE(?,?)}");
		cstmt.setInt(1, departmentId);
		cstmt.setDouble(2, incrementSalary);
		
		int result = cstmt.executeUpdate();

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "프로시저 성공." : "프로시저 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, cstmt);*/
	}
	
	private static void employeeSalaryUpFunc() throws SQLException	// SALARY_UP_FUNC.
	{
		// Connection
		Connection con = null;
		CallableStatement cstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();
		
		// 트랜잭션 시작.
		con.setAutoCommit(false);
				
		System.out.print("조회할 ID 입력 : ");
		int id = Integer.parseInt(scan.nextLine());
				
		// stmt = con.createStatement();
		cstmt = con.prepareCall("{ ? = call BOOKS_FUNCTION(?)}");
		cstmt.registerOutParameter(1, Types.VARCHAR);
		cstmt.setInt(2, id);
		
		// 바인딩을 시키는데, 출력될 데이터 값으로 3번을 바인딩.		
		int result = cstmt.executeUpdate();
		String message = cstmt.getString(1);
		System.out.println(message);

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "함수 성공." : "함수 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, cstmt);
	}
}