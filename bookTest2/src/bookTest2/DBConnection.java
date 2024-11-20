package bookTest2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection
{
	public static Connection dbCon ()
	{
		// 객체참조 변수 선언.
		Connection con = null;
		
		// 1. JDBC Driver Load
		// 2. Connection
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "hr", "hr");
		} catch (ClassNotFoundException e)
		{
			System.out.println(e.toString());
		}
		catch (SQLException e)
		{
			System.out.println(e.toString());
		}

		return con;
	}	
	
	public static void dbClose(Connection con, Statement stmt, ResultSet rs)
	{
		// 6. Close
		if (con != null)
		{
			try
			{
				con.close();
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			}
		}

		if (stmt != null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			}
		}

		if (rs != null)
		{
			try
			{
				rs.close();
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			}
		}
	}
	
	
	public static void dbClose(Connection con, Statement stmt)
	{
		// 6. Close
		if (con != null)
		{
			try
			{
				con.close();
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			}
		}

		if (stmt != null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{
				System.out.println(e.toString());
			}
		}
	}
}