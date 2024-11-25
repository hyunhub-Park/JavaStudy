package com.kh.cafedbMVCProject.view;

public class ScreenMenu
{
	public static void mainMenu()
	{
		System.out.println("-------------------------------WELCOME TO THE CAFE-------------------------------");
		System.out.println("          1. 매장에서 먹을게요(EAT IN)  2. 포장할게요(TAKE OUT)  3. 리뷰작성(REVIEW)");
		System.out.println("          4. 주문 조회(ORDER CHECK)   5. 주문 및 리뷰 조회      6. 종료(EXIT)");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print("입력 >> ");
	}
	
	public static void afterInOut()
	{
		System.out.println("-------------------------------WELCOME TO THE CAFE-------------------------------");
		System.out.println("              1. 메뉴보기          2. EVENT 참여하기          3. 이전으로               ");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print("입력 >> ");
		
	}
	
	public static void cafeMenu()
	{
		// 숫자 아니고 문자 받아야함^^^^^^^^^
		System.out.println("------------------------------------CAFE MENU-----------------------------------");
		System.out.println("              ■ COFFEE      ■ NON-COFFEE      ■ SNACKS      ■ DESSERTS");
		System.out.println("            아메리카노(4.0)     초코라떼(4.0)      초코쿠키(2.0)    초코케익(4.0)");
		System.out.println("             카페라떼(4.5)      녹차라떼(4.0)      버터쿠키(2.0)   치즈케익(4.0)");
		System.out.println("             카푸치노(5.0)      아이스티(3.5)       두부칩(3.0)     마카롱(3.0)");
		System.out.println("             카페모카(5.0)     레몬에이드(4.0)      이클립스(2.0)    마들렌(3.0)");
		System.out.println("---------------------------------------------------------------------------------");
	}
	
	public static void eventMenu()
	{
		System.out.println("------------------------------------EVENT MENU-----------------------------------");
		System.out.println("                        이벤트를 신청하고자 하는 매장을 입력해주세요                       \n");
		System.out.println("                   ■강남R점       ■코엑스별마당점       ■청담스타R점                      ");
		System.out.println("                   ■수서역R점     ■스타필드코엑스몰R점    ■신사가로수점                    \n");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print("                             >> 매장명을 정확히 입력해주세요. <<                           ");
	}
	
	public static void reviewMenu()
	{
		System.out.println("-------------------------------------REVIEW--------------------------------------");
		System.out.println("                           주문번호 입력 후, 한 줄 리뷰를 남겨주세요!                      ");
		System.out.println("---------------------------------------------------------------------------------");	
	}
	
	/*public static void orderReviewCheckMenu()
	{
		System.out.println("-----------------------------------ORDER CHECK-----------------------------------");
		System.out.println("                                주문번호를 입력해주세요.                               ");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print(">> ");
	}*/
}