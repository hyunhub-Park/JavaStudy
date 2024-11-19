package movieTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieMain
{
	public static Scanner scan = new Scanner(System.in);
	public static final int PRINT = 1
						,INSERT = 2
						,UPDATE = 3	
						,DELETE = 4
						,EXIT = 5;
	public static void main (String [] args) throws SQLException
	{
		// 사용자로부터 Movie 출력, 입력, 수정, 삭제 요청을 받음.
		boolean exitFlag = false;
		
		while (!exitFlag)
		{
			printMenu();
			int num = Integer.parseInt(scan.nextLine());
			
			switch (num)
			{
			case PRINT:
				moviePrint();
				break;
				
			case INSERT:
				movieInsert();
				break;
				
			case UPDATE:
				movieUpdate();
				break;
				
			case DELETE:
				movieDelete();
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
	
	private static void printMenu ()
	{
		System.out.println("- Movie Information Menu -");
		System.out.println("1. 현재 상영중인 영화");
		System.out.println("2. 상영 정보 추가");
		System.out.println("3. 정보 수정");
		System.out.println("4. 정보 삭제");
		System.out.println("5. 종료");
		System.out.println("--------------------------");
		System.out.print(">> ");
	}
	
	private static void moviePrint () throws SQLException
	{
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList <Movie> movieList = new ArrayList <Movie>();

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM MOVIE");

		// 4. 테이블 가져오기.
		while (rs.next())
		{
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String director = rs.getString("DIRECTOR");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");

			Movie movie = new Movie(id, title, director, year, price);
			movieList.add(movie);
		}

		// 5. 출력하기
		movieListPrint(movieList);

		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt, rs);
		
	}
	
	private static void movieListPrint(ArrayList <Movie> movieList)
	{
		for (Movie movie : movieList)
		{
			System.out.println(movie.toString());
		}
	}

	private static void movieInsert () throws SQLException
	{
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		// ID, TITLE, DIRECTOR, YEAR, PRICE
		// Movie movie = new Movie(300, "External Movie A", "EMA", "2008", 23000);
		
		System.out.println("추가할 상영 정보를 입력하세요.");
		System.out.print("TITLE : ");
		String title = scan.nextLine();
		
		System.out.print("DIRECTOR : ");
		String director = scan.nextLine();
		
		System.out.print("YEAR : ");
		String year = scan.nextLine();
		
		System.out.print("PRICE : ");
		int price = Integer.parseInt(scan.nextLine());
		
		Movie movie = new Movie(title, director, year, price);
		// movieList.add(movie);
	
		
		/*System.out.print("ID : ");
		int id = Integer.parseInt(scan.nextLine());
		
		System.out.print("\nTITLE : ");
		String title = sc.nextLine();
		
		System.out.print("\nDIRECTOR : ");
		String director = sc.nextLine();
		
		System.out.print("\nYEAR : ");
		String year = sc.nextLine();
		
		System.out.print("\nPRICE : ");
		int price = Integer.parseInt(scan.nextLine());
		
		Movie movie = new Movie(id, title, director, year, price);
		movieList.add(movie);*/
		
		
		
		
		// String publisher = "psh";
		
		stmt = con.createStatement();
		
		int result = stmt.executeUpdate("INSERT INTO MOVIE VALUES (MOVIE_ID_SEQ.nextval, '"
										+ movie.getTitle() + "', '"
										+ movie.getDirector() + "', '"
										+ movie.getYear() + "', "
										+ movie.getPrice() + ")");

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "입력 성공." : "입력 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt);
	}
	
	private static void movieUpdate() throws SQLException
	{
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		// 수정할 데이터 입력.
		System.out.println("수정할 정보의 ID를 입력하세요.");
		
		System.out.print("ID : ");
		int id = Integer.parseInt(scan.nextLine());
		
		System.out.println(">>  ID " + id + "번의 수정사항 입력하기.  <<");
				
		System.out.print("TITLE : ");
		String title = scan.nextLine();
				
		System.out.print("DIRECTOR : ");
		String director = scan.nextLine();
				
		System.out.print("YEAR : ");
		String year = scan.nextLine();
				
		System.out.print("PRICE : ");
		int price = Integer.parseInt(scan.nextLine());
		
		//Movie movie = new Movie(id, title, director, year, price);

		/*stmt = con.createStatement();
		int result = stmt.executeUpdate("UPDATE MOVIE SET TITLE='" +
										movie.getTitle() +
						"', DIRECTOR='" + movie.getDirector() +
						"', YEAR='" + movie.getYear() +
						"', PRICE=" + movie.getPrice() +
						"WHERE ID=" + movie.getId());*/
		
		// Movie movie = new Movie(3, "lll", "C", "2023", 23000);
		Movie movie = new Movie(id, title, director, year, price);
		stmt = con.createStatement();
		int result = stmt.executeUpdate("UPDATE MOVIE SET TITLE='" + movie.getTitle() +
				"', DIRECTOR='" + movie.getDirector() +
				"', YEAR='" + movie.getYear() +
				"', PRICE=" + movie.getPrice() +
				"WHERE ID=" + movie.getId());
		
		

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0)? "수정 성공." : "수정 실패.");
				
		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt);
	}
	
	private static void movieDelete() throws SQLException
	{
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		System.out.println("삭제할 정보의 ID를 입력하세요.");
		System.out.print("ID : ");
		int id = Integer.parseInt(scan.nextLine());	// 삭제할 번호 입력받기 위한 변수 선언 및 스캐너.
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE FROM MOVIE WHERE ID = " + id);

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0)? "삭제 성공." : "삭제 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, stmt);
	}
}
