package student_jbdc;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

								/* 네트워크로 보내야하니 시리얼라이저블. */
public class Employees implements Serializable
{
	private int employeeID;	// EMPLOYEE_ID    NOT NULL NUMBER(6) --------> PK.
	private String firstName;	// FIRST_NAME              VARCHAR2(20) 
	private String lastName;	// LAST_NAME      NOT NULL VARCHAR2(25) 
	private String email;	// EMAIL          NOT NULL VARCHAR2(25) 
	private String phoneNumber;	// PHONE_NUMBER            VARCHAR2(20) 
	private Date hireDate;	// HIRE_DATE      NOT NULL DATE         
	private String jobID;	// JOB_ID         NOT NULL VARCHAR2(10) 
	private double salary;	// SALARY                  NUMBER(8,2)  
	private double commissionPCT;	// COMMISSION_PCT          NUMBER(2,2)  
	private int managerID;	// MANAGER_ID              NUMBER(6)    
	private int departmentID;	// DEPARTMENT_ID           NUMBER(4)
	
	// 생성자 오버로딩.
	public Employees(int employeeID, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			String jobID, double salary, double commissionPCT, int managerID, int departmentID)
	{

	}
	
	

	public Employees(int employeeID, String firstName, double salary) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.salary = salary;
	}



	// getters, setters
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommissionPCT() {
		return commissionPCT;
	}

	public void setCommissionPCT(double commissionPCT) {
		this.commissionPCT = commissionPCT;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	// 객체 비교.
	@Override
	public int hashCode()
	{
		return Objects.hash(employeeID);
		// return Objects.hash(employeeID, firstName); --> 두 개 하고싶으면.
	}

	@Override
	public boolean equals(Object obj)
	{
		// 다운캐스팅 필요.
		// Employees e = null;
		
		if (!(obj instanceof Employees))
		{
			return false;
		}
		// e = (Employees)obj;
		// return this.employeeID == e.employeeID;
		return this.employeeID == ((Employees)obj).employeeID;
	}

	// toString().
	@Override
	public String toString()
	{
		return "[employeeID=" + employeeID + ", firstName=" + firstName + ", salary=" + salary + "]";
	}
}