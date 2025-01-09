package src.com.airplaneService.main;

import java.util.Scanner;

import src.com.airplaneService.controller.AirBookingRegisterManager;
import src.com.airplaneService.view.MY_PAGE_CHOICE;
import src.com.airplaneService.view.MenuMain;

public class MyPageCustomerMain
{

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String [] args)
	{
		int num;
		
		boolean exitFlag = false;
		
		AirBookingRegisterManager abrm = new AirBookingRegisterManager();

		while (!exitFlag)
		{
			try
			{
				MenuMain.myPageMenu();
				num = Integer.parseInt(scan.nextLine());
				
				switch(num)
				{
				case MY_PAGE_CHOICE.INFO_SELECT :
					abrm.selectCheckManager();
					break;
					
				case MY_PAGE_CHOICE.INFO_UPDATE :
					abrm.updateManager();
					break;
					
				case MY_PAGE_CHOICE.INFO_DELETE :
					abrm.deleteManager();
					break;
					
				case MY_PAGE_CHOICE.COUNT_SELECT :
					abrm.selectCountManager();
					break;
					
				case MY_PAGE_CHOICE.ALL_SELECT :
					abrm.selectManager();
					break;
					
				case MY_PAGE_CHOICE.EXIT :
					System.out.println("프로그램을 종료합니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당되는 메뉴를 다시 입력해주세요.");
				}
			} catch (Exception e)
			{
				System.out.println(e.toString());
				//System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
			}
		}
	}
}
