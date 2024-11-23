import java.sql.SQLException;
import java.util.Scanner;

import com.kh.cafedbMVCProject.controller.CafeMenuRegisterManager;
import com.kh.cafedbMVCProject.view.CAFE_MAIN_CHOICE;
import com.kh.cafedbMVCProject.view.ScreenMenu;

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
                    
                case CAFE_MAIN_CHOICE.ORDER_CHECK:
                	orderCheckMenu();
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
                System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
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
	
	private static void inOrout() throws SQLException
	{
		int num;
		CafeMenuRegisterManager cmrm = new CafeMenuRegisterManager();

		// StudentRegisterManager studnetManager = new StudentRegisterManager();
		ScreenMenu.afterInOut();
		num = Integer.parseInt(scan.nextLine());
		
    	switch (num)
    	{
    	case 1:
    		cafeMenu();
    		cmrm.insertManager();
    	case 2:
    		//이벤트관련
    	case 3:
    		return;
    		
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
    	}
	}
	
	
	private static void orderCheckMenu()
	{
		
	}
	
	// 학생정보.
	/*private static void studentMenu() throws SQLException
	{
		int no;
		StudentRegisterManager srm = new StudentRegisterManager();

		StudentRegisterManager studnetManager = new StudentRegisterManager();
		MenuViewer.studentMenuView();
		no = Integer.parseInt(sc.nextLine());
		
		switch (no)
		{
		
		case STUDENT_CHOICE.INSERT:
			System.out.println("");
			srm.insertManager();
			break;
			
		case STUDENT_CHOICE.UPDATE:
			System.out.println("");
//            studnetManager.studnetUpdate();
			break;
			
		case STUDENT_CHOICE.LIST:
			System.out.println("");
//            studnetManager.studnetTotalList();
			break;
			
		case STUDENT_CHOICE.DELETE:
			System.out.println("");
//            studnetManager.studnetTotalList();
			break;
			
		case STUDENT_CHOICE.MAIN:
			return;
			
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
        }
	}*/
}
