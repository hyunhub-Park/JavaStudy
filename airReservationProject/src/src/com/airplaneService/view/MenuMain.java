package src.com.airplaneService.view;

public class MenuMain
{
	public static void loginMenu()
	{
		System.out.println("접속 권한을 입력하세요(1. 관리자 / 2. 사용자)");
		System.out.print(">>");
	}
	
	public static void signUpMenu()
	{
		System.out.println("-------------------------------------------------------------------");
		System.out.println("\t1. 만 14세 이상 가입하기\t2. 만 14세 미만 가입하기(필 법정 대리인)");
		System.out.println("\t3. 이전으로");	// break.
		System.out.println("-------------------------------------------------------------------");
		System.out.print("\t번호를 입력하세요 : ");
	}
	
	public static void myPageMenu()
	{
		System.out.println("\t1. 내 가입정보 확인하기\t2. 내 가입정보 수정하기");	// 아이디와 휴대폰번호로 함께 조회.(개인정보유출방지를 위해)
		System.out.println("\t3. 회원 탈퇴하기\t\t4. 예약 현황(Count) 확인하기 5. allselect");
		System.out.println("\t6. 이전으로");	// break.
		System.out.println("-------------------------------------------------------------------");
		System.out.print("\t번호를 입력하세요 : ");
	}
}