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
		
		// 1번(JDBC Driver Load), 2번(Connection)을 가져온다.
		con = DBConnection.dbCon();

		// 3. Statement(Query : C R U D)
		// 4. Result Set
		// 5. (출력 진행.) 데이터 저장 진행.
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
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
			System.out.println("데이터베이스 실행문 에러." + e.toString());
		}

		// 6. 데이터 출력.
		employeesListPrint(employeesList);
		
		// 7. Close
		DBConnection.dbClose(con, stmt, rs);
	}
	
	// 6. 테이블 내용 출력.
	private static void employeesListPrint(ArrayList <Employees> employeesList)
	{
		for (Employees e : employeesList)
		{
			System.out.println(e.toString());
		}
	}
}