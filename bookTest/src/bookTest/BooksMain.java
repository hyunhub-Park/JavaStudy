package bookTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class BooksMain
{
	public static Scanner scan = new Scanner(System.in);
	public static final int PRINT = 1
						,INSERT = 2
						,UPDATE = 3	
						,DELETE = 4
						,EXIT = 5;

	public static void main(String [] args) throws SQLException
	{
		// 사용자로부터 Books 출력, 입력, 수정, 삭제 요청을 받음.
		
		boolean exitFlag = false;
		
		while (!exitFlag)
		{
			
			printMenu();
			int num = Integer.parseInt(scan.nextLine());
			
			switch (num)
			{
			case PRINT:
				booksPrint();
				break;
				
			case INSERT:
				booksInsert();
				break;
				
			case UPDATE:
				booksUpdate();
				break;
				
			case DELETE:
				booksDelete();
				break;

			case EXIT:
				exitFlag = true;
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value : " + num);	
			}
		}
		System.out.println("The end.");
	}
	
	private static void booksInsert () throws SQLException	// 삽입.
	{
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		Books books = new Books(0, "Head First Java" , "psh", "2008", 23000);
		String publisher = "psh";
		stmt = con.createStatement();
		// stmt.executeUpdate("INSERT INTO books VALUES (BOOKS_ID_SEQ.nextval, 'Head First Java', publisher, '2008', 23000)");
		int result = stmt.executeUpdate("INSERT INTO books VALUES (BOOKS_ID_SEQ.nextval, '" + books.getTitle() + 
				"', '" + books.getPublisher() + "', '" + books.getYear() + "', " + books.getPrice() + ")");

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0)? "입력 성공." : "입력 실패.");
		
		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt);

	}

	private static void booksUpdate () throws SQLException	// 수정.
	{
		
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		// 수정할 데이터 입력.
		Books books = new Books(3, "Java Java Java" , "psh", "2004", 33000);
		stmt = con.createStatement();
		int result = stmt.executeUpdate("UPDATE BOOKS SET TITLE='" +
										books.getTitle() +
						"', PUBLISHER='" + books.getPublisher() +
						"', YEAR='" + books.getYear() +
						"', PRICE=" + books.getPrice() +
						"WHERE ID = " + books.getId());

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0)? "수정 성공." : "수정 실패.");
				
		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt);
	}

	private static void booksDelete () throws SQLException	// 삭제.
	{
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		System.out.print("삭제 할 번호 입력 >> ");
		int no = Integer.parseInt(scan.nextLine());	// 삭제할 번호 입력받기 위한 변수 선언 및 스캐너.
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE FROM BOOKS WHERE ID = " + no);

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0)? "삭제 성공." : "삭제 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt);
	}

	public static void booksPrint () throws SQLException
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

	private static void printMenu()
	{
		System.out.println("Books Menu (1. print(select??), 2. insert, 3. update, 4. delete, 5. exit)");
		System.out.print(">> ");
	}

	private static void booksListPrint(ArrayList<Books> booksList)
	{
		for (Books books : booksList)
		{
			System.out.println(books.toString());
		}
	}
}