package student_jbdc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB
{
	public static void main (String [] args)
	{
		// 객체참조 변수 선언.
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList <Employees> employeesList = new ArrayList <Employees>();
		
		// 1. JDBC Driver Load
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 드라이버 적재 성공.");
		} catch (ClassNotFoundException e)
		{
			System.out.println("1. 드라이버 적재 실패." + e.toString());
		}
		
		// 2. Connection
		try
		{																			/* 사용자명, 비밀번호. */
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "hr", "hr");
			System.out.println("2. 오라클 접속 성공.");
		} catch (SQLException e)
		{
			System.out.println("2. 오라클 접속 실패." + e.toString());
		}

		// 3. Statement(Query : C R U D)
		try
		{
			stmt = con.createStatement();
			System.out.println("3. Statement 객체 생성 성공.");
		} catch (SQLException e)
		{
			System.out.println("3. Statement 객체 생성 실패.");
		}
		
		// 4. Result Set
		try {
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
			System.out.println("4. Result Set 객체 생성 성공.");
		} catch (SQLException e)
		{
			System.out.println("4. Result Set 객체 생성 실패.");
		}
		
		// 5. (출력 진행.) 데이터 저장 진행.
		try
		{
			while (rs.next())
			{
				int employeeID = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				int salary = rs.getInt("SALARY");
				System.out.println(employeeID + "\t" + firstName + "\t" + salary);
				Employees employees = new Employees(employeeID, firstName, salary);
				employeesList.add(employees);
			}
		
			
		} catch (SQLException e)
		{
			System.out.println("5. Result Set 출력 실패.");
		}
		
		// 데이터 출력.
		employeesListPrint(employeesList);
		
		
		// 6. Close
		if (con != null)
		{
			try
			{
				con.close();
				System.out.println("5. 오라클 con close 성공.");
			} catch (SQLException e)
			{
				System.out.println("5. 오라클 con close 실패." + e.toString());
			}
		}
		
		if (stmt != null)
		{
			try
			{
				stmt.close();
				System.out.println("6. 오라클 stmt close 성공.");
			} catch (SQLException e)
			{
				System.out.println("6. stmt close 실패." + e.toString());
			}
		}
		
		if (rs != null)
		{
			try
			{
				rs.close();
				System.out.println("6. 오라클 rs close 성공.");
			} catch (SQLException e)
			{
				System.out.println("6. rs close 실패." + e.toString());
			}
		}
	}

	private static void employeesListPrint(ArrayList <Employees> employeesList)
	{
		for (Employees e : employeesList)
		{
			System.out.println(e.toString());
		}
	}
}