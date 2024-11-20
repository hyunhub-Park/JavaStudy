package movieTest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieMain
{
	public static Scanner scan = new Scanner(System.in);
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
			case MovieMenu.PRINT:
				moviePrint();
				break;
				
			case MovieMenu.INSERT:
				movieInsert();
				break;
				
			case MovieMenu.UPDATE:
				movieUpdate();
				break;
				
			case MovieMenu.DELETE:
				movieDelete();
				break;
				
			case MovieMenu.DISCOUNT_PRICE_PROC:
				dicountPriceProc();
				break;
				
			case MovieMenu.DICOUNT_PRICE_FUNC:
				dicountPriceFucn();
				break;
				
			case MovieMenu.PACKAGE_PRICE_TRG:
				packagePriceTrg();
				break;
				
			case MovieMenu.PACKAGE_PRICE:
				packagePrice();
				break;

			case MovieMenu.EXIT:
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
		System.out.println("5. 상영 영화 할인받기_PROC");
		System.out.println("6. 상영 영화 할인받기_FUNC");
		System.out.println("7. [프리미엄 패키지] 상영정보 추가");
		System.out.println("8. [프리미엄 패키지] 확인");
		System.out.println("9. 종료");
		System.out.println("--------------------------");
		System.out.print(">> ");
	}
	
	private static void moviePrint () throws SQLException	// PRINT.
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

	private static void movieInsert () throws SQLException	// INSERT.
	{
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();
				
		// 트랜잭션 시작점.
		// con.setAutoCommit(false);

		// 3. PreparedStatement
		
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

		pstmt = con.prepareStatement("INSERT INTO MOVIE VALUES (MOVIE_ID_SEQ.nextval, ?, ?, ?, ?)");
		pstmt.setString(1, movie.getTitle());
		pstmt.setString(2, movie.getDirector());
		pstmt.setString(3, movie.getYear());
		pstmt.setInt(4, movie.getPrice());

		int result = pstmt.executeUpdate();


		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "입력 성공." : "입력 실패.");

		/*if (result != 0)
		{
			con.commit();
		} else
		{
			con.rollback();
		}*/
				
		// 6. sql객체 반납.
		DBConnection.dbClose(con, pstmt);
	}
	
	private static void movieUpdate() throws SQLException	// UPDATE.
	{
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. PreparedStatement
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
		
		Movie movie = new Movie(id, title, director, year, price);
		
		// pstmt = con.prepareStatement();
		pstmt = con.prepareStatement("UPDATE MOVIE SET TITLE=?, DIRECTOR=?, YEAR=?, PRICE=? WHERE ID=?");
		pstmt.setString(1, movie.getTitle());
		pstmt.setString(2, movie.getDirector());
		pstmt.setString(3, movie.getYear());
		pstmt.setInt(4, movie.getPrice());
		pstmt.setInt(5, movie.getId());
		
		int result = pstmt.executeUpdate();

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "수정 성공." : "수정 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, pstmt);
	}
	
	private static void movieDelete() throws SQLException	// DELETE.
	{
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. PreparedStatement
		System.out.println("삭제할 정보의 ID를 입력하세요.");
		System.out.print("ID : ");
		int id = Integer.parseInt(scan.nextLine());

		pstmt = con.prepareStatement("DELETE FROM MOVIE WHERE ID = ? ");
		pstmt.setInt(1, id);

		int result = pstmt.executeUpdate();

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "삭제 성공." : "삭제 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, pstmt);
	}

	private static void dicountPriceProc() throws SQLException // DISCOUNT_PRICE_PROC.
	{
		// Connection
		Connection con = null;
		CallableStatement cstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		System.out.println("할인을 적용할 ID를 입력하세요.");
		System.out.print(">> ");
		int id = Integer.parseInt(scan.nextLine());
		
		// cstmt = con.prepareCall();
		cstmt = con.prepareCall("{call MOVIE_PROCEDURE(?,?)}");
		
		cstmt.setInt(1, id);
		//cstmt.setInt(1, id);

		// 바인딩을 시키는데, 출력될 데이터 값으로 3번을 바인딩.
		cstmt.registerOutParameter(2, Types.VARCHAR);//-----------------------------------------------------------------
		
		// ✔✔ ocedure 매개변수 갯수만큼의 인덱스 잊지말기. ✔✔
		int result = cstmt.executeUpdate();
		String message = cstmt.getString(2);
		System.out.println(message);

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "프로시저 성공." : "프로시저 실패.");

		// 6. sql객체 반납.
		DBConnection.dbClose(con, cstmt);
	}
	
	private static void dicountPriceFucn() throws SQLException	// DICOUNT_PRICE_FUNC.
	{
		// Connection
		Connection con = null;
		CallableStatement cstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();
				
		// 트랜잭션 시작.
		// con.setAutoCommit(false);
						
		System.out.println("할인을 적용할 ID를 입력하세요.");
		System.out.print(">> ");
		int id = Integer.parseInt(scan.nextLine());
						
		// cstmt = con.prepareCall();
		cstmt = con.prepareCall("{ ? = call MOVIE_FUNCTION(?)}");
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

	private static void packagePriceTrg() throws SQLException	// PACKAGE_PRICE_TRG.
	{
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 트랜잭션 시작점.
		// con.setAutoCommit(false);

		// 3. PreparedStatement

		System.out.println("[프리미엄 패키지] 상영 정보를 입력하세요.");
		System.out.print("TITLE : ");
		String title = scan.nextLine();

		System.out.print("DIRECTOR : ");
		String director = scan.nextLine();

		System.out.print("YEAR : ");
		String year = scan.nextLine();

		System.out.print("PRICE : ");
		int price = Integer.parseInt(scan.nextLine());

		Movie movie = new Movie(title, director, year, price);

		pstmt = con.prepareStatement("INSERT INTO MOVIE2 VALUES (MOVIE2_ID_SEQ.nextval, ?, ?, ?, ?)");
		pstmt.setString(1, movie.getTitle());
		pstmt.setString(2, movie.getDirector());
		pstmt.setString(3, movie.getYear());
		pstmt.setInt(4, movie.getPrice());

		int result = pstmt.executeUpdate();

		// 4. 내용 입력에대한 체크.
		System.out.println((result != 0) ? "입력 성공." : "입력 실패.");

		/*
		 * if (result != 0) { con.commit(); } else { con.rollback(); }
		 */

		// 6. sql객체 반납.
		DBConnection.dbClose(con, pstmt);
	}
	
	private static void packagePrice() throws SQLException	// PACKAGE_PRICE.
	{
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Movie> movieList = new ArrayList<Movie>();

		// 1. Load, 2. Connect
		con = DBConnection.dbCon();

		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM MOVIE2");

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
}
