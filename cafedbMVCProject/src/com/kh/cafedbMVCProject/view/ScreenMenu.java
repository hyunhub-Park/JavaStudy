package com.kh.cafedbMVCProject.view;

public class ScreenMenu
{
	public static void mainMenu()
	{
		System.out.println("-------------------------------WELCOME TO THE CAFE-------------------------------");
		System.out.println("  1. 매장에서 먹을게요(EAT IN)  2. 포장할게요(TAKE OUT)  3. 주문확인(CHECK)  4. 종료(EXIT)  ");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print("입력 >> ");
	}
	
	public static void afterInOut()
	{
		System.out.println("--------------------------WELCOME TO THE CAFE--------------------------");
		System.out.println("          1. 메뉴보기          2. EVENT 참여하기          3. 이전으로          ");
		System.out.println("-----------------------------------------------------------------------");
		System.out.print("입력 >> ");
		
	}
	
	public static void cafeMenu()
	{
		// 숫자 아니고 문자 받아야함^^^^^^^^^
		System.out.println("--------------------------CAFE MENU--------------------------");
		System.out.println("   ■ COFFEE      ■ NON-COFFEE      ■ SNACKS      ■ DESSERTS   ");
		System.out.println("    아메리카노         초코라떼          초코쿠키          초코케익    ");
		System.out.println("    카페라떼          녹차라떼          버터쿠키          치즈케익    ");
		System.out.println("    카푸치노          아이스티           두부칩           마카롱     ");
		System.out.println("    카페모카          레몬에이드         이클립스          마들렌     ");
		System.out.println("--------------------------------------------------------------");
	}
	
	public static void eventMenu()
	{
		System.out.println("--------------------------EVENT MENU--------------------------");
		System.out.println("              이벤트를 신청하고자 하는 매장을 입력해주세요 !            \n");
		System.out.println("            ■강남R점       ■코엑스별마당점       ■청담스타R점          ");
		System.out.println("            ■수서역R점     ■스타필드코엑스몰R점    ■신사가로수점       \n");
		System.out.println("--------------------------------------------------------------");
		System.out.print("입력(매장명을 정확히 입력해주세요.) >> ");
	}
	
	public static void orderCheckMenu()
	{
		System.out.println("-------------------------------ORDER CHECK-------------------------------");
		System.out.println("                            주문번호를 입력해주세요                            ");
		System.out.println("-------------------------------------------------------------------------");
		System.out.print(">> ");
	}

}
