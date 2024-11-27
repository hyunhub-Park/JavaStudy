import java.sql.SQLException;
import java.util.Scanner;

import com.kh.cafedbMVCProject.controller.CafeMenuRegisterManager;
import com.kh.cafedbMVCProject.controller.EventRegisterManager;
import com.kh.cafedbMVCProject.controller.ReviewRegisterManager;
import com.kh.cafedbMVCProject.view.CAFE_MAIN_CHOICE;
import com.kh.cafedbMVCProject.view.ScreenMenu;
// ✔ 주문번호를 입력하여 자신의 주문건만 조회할 수 있는 기능.
// ✔ NVL사용하여 NULL값을 적절한 문구로 대체하기.
// 메뉴 주문 시 수량도 함께 받을 수 있도록.
// 주문 날짜 및 시간 함께 출력될 수 있도록.
// 예외처리 세부적으로 추가하기.
public class CafeMain
{
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{		
		int num;
		boolean exitFlag = false;
		
        while (!exitFlag)
        {
            try
            {
            	ScreenMenu.mainMenu();
                num = Integer.parseInt(scan.nextLine());

                switch (num)
                {
                case CAFE_MAIN_CHOICE.EAT_IN:
                	// 1
                	inOrout();
                    break;
                
                case CAFE_MAIN_CHOICE.TAKE_OUT:
                	inOrout();
                    break;

                case CAFE_MAIN_CHOICE.REVIEW:
                	reviewMenu();
                    break;
                    
                case CAFE_MAIN_CHOICE.ORDER_CHECK:
                	orderCheckMenu();
                    break;
                    
                case CAFE_MAIN_CHOICE.ORDER_REVIEW_CHECK:
                	orderCheckAllMenu();
                    break;

                case CAFE_MAIN_CHOICE.EXIT:
                    System.out.println("주문을 종료합니다.");
                    exitFlag = true;
                    break;
                
                default:
                    System.out.println("번호를 다시 입력해주세요.");
                }
            } catch (Exception e) 
            {
            	System.out.println(e.toString() + "메인 확인하기");
                //System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
                return;
            }
        }	// end of while.
	}

	// 카페메뉴.
	private static void cafeMenu()
	{
		// CafeMenuRegisterManager cmrm = new CafeMenuRegisterManager();

		// StudentRegisterManager studnetManager = new StudentRegisterManager();
		ScreenMenu.cafeMenu();
	}
	
	private static void eventMenu()
	{
		ScreenMenu.eventMenu();
	}
	
	private static void inOrout() throws SQLException
	{
		int num;
		CafeMenuRegisterManager cmrm = new CafeMenuRegisterManager();
		EventRegisterManager erm =  new EventRegisterManager();

		// StudentRegisterManager studnetManager = new StudentRegisterManager();
		ScreenMenu.afterInOut();
		num = Integer.parseInt(scan.nextLine());
        
		switch (num)
        {
        case 1:
        	cafeMenu();
        	cmrm.insertManager();
        	break;
        case 2:
        	//이벤트관련
        	eventMenu();
        	erm.insertManager();
        	break;
        case 3:
        	
        	return;
        	
        default:
        	System.out.println("해당 메뉴 번호만 입력하세요.");
        }
	}
	
	private static void reviewMenu() throws SQLException
	{
		ScreenMenu.reviewMenu();
		ReviewRegisterManager rrm = new ReviewRegisterManager();
		rrm.insertManager();
	}

	private static void orderCheckMenu() throws SQLException
	{
		CafeMenuRegisterManager cmrm = new CafeMenuRegisterManager();
		cmrm.selectManager();
	}
	
	private static void orderCheckAllMenu() throws SQLException
	{
		ReviewRegisterManager rrm = new ReviewRegisterManager();
		rrm.selectAllselManager();
		//rrm.selectAllManager();
	}
}