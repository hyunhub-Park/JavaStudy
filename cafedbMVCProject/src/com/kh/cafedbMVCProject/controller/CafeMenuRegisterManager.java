package com.kh.cafedbMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.cafedbMVCProject.model.CafeMenuVO;

public class CafeMenuRegisterManager
{
	public static Scanner scan = new Scanner(System.in);
	// 전체를 계산하는 프로그램.
	// 전체 학생리스트 출력기능.
	public static void selectManager() throws SQLException
	{
		ArrayList <CafeMenuVO> cafeMenuList = new ArrayList<CafeMenuVO>();
		CafeMenuDAO cafeMenuDAO = new CafeMenuDAO();
		
		cafeMenuList = cafeMenuDAO.cafeMenuSelect();	// 데이터를 받았으니, 화면에 출력해야 함. jsp로 만들 것.
		// printStudentList(studentList);
		
		if (cafeMenuList == null)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printCafeMenuList(cafeMenuList);
	}
	
	public static void insertManager() throws SQLException
	{
        CafeMenuDAO cm = new CafeMenuDAO();
        // SubjectVO svo = new SubjectVO();	// setters로 값을 주고자 할 때,

        int totalSum = 0;
        
        String drink;
        String snack;
        String dessert;
        
        System.out.print("\nDRINK : ");
        drink = scan.nextLine();
        if (drink.equals("아메리카노"))
        {
        	totalSum = 4000;
        } else if(drink.equals("카페라떼"))
        {
        	totalSum = 4500;
        } else if(drink.equals("카푸치노"))
        {
        	totalSum = 5000;
        } else if(drink.equals("카페모카"))
        {
        	totalSum = 5000;
        } else if(drink.equals("초코라떼"))
        {
        	totalSum = 4000;
        } else if(drink.equals("녹차라떼"))
        {
        	totalSum = 4000;
        } else if(drink.equals("아이스티"))
        {
        	totalSum = 3500;
        } else if(drink.equals("레몬에이드"))
        {
        	totalSum = 4000;
        }
    
        else
        { // Drink는 Not Null제약조건.
        	totalSum = 4000;
        }
        
        System.out.print("SNACK : ");
        snack = scan.nextLine();
        
        if (snack.equals("초코쿠키"))
        {
        	totalSum += 2000;
        } else if(snack.equals("버터쿠키"))
        {
        	totalSum += 2000;
        } else if(snack.equals("두부칩"))
        {
        	totalSum += 3000;
        } else if(snack.equals("이클립스"))
        {
        	totalSum += 2000;
        } else
        {	// null일 경우.
        	totalSum += 0;
        }
        
        System.out.print("DESSERT : ");
        dessert = scan.nextLine();
        
        if (dessert.equals("초코케익"))
        {
        	totalSum += 4000;
        } else if(dessert.equals("치즈케익"))
        {
        	totalSum += 4000;
        } else if(dessert.equals("마카롱"))
        {
        	totalSum += 3000;
        } else if(dessert.equals("마들렌"))
        {
        	totalSum += 3000;
        } else
        {	// null일 경우.
        	totalSum += 0;
        }
        
        // num, name을 줄 수 있음. no는 nextval이므로 불가.
        CafeMenuVO cmvo = new CafeMenuVO(drink, snack, dessert);
        boolean successFlag = cm.cafeMenuInsert(cmvo);
        
        if (successFlag == false)
		{
			System.out.println("입력처리 실패.");
		} else
		{
			System.out.println("주문 완료! 총 금액은 " + totalSum + "원 입니다.");
		}

	}	
	
	// 입력받은 주문내역 출력.
	public static void printCafeMenuList(ArrayList <CafeMenuVO> cafeMenuList)
	{
		System.out.println("---------------------------------------------------------------------------------");
		
		for (CafeMenuVO p : cafeMenuList)
		{
			System.out.println(p.toString());
		}
		
		System.out.println("---------------------------------------------------------------------------------");
	}

}
