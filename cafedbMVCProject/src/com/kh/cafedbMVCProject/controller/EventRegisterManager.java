package com.kh.cafedbMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.cafedbMVCProject.model.EventVO;

public class EventRegisterManager
{
	public static Scanner scan = new Scanner(System.in);
	// 전체를 계산하는 프로그램.
	// 전체 학생리스트 출력기능.
	public static void selectManager() throws SQLException
	{
		ArrayList <EventVO> eventList = new ArrayList<EventVO>();
		EventDAO eventDAO = new EventDAO();
		
		eventList = eventDAO.eventSelect();	// 데이터를 받았으니, 화면에 출력해야 함. jsp로 만들 것.
		// printStudentList(studentList);
		
		if (eventList == null)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printEventList(eventList);
	}
	
	public static void insertManager() throws SQLException
	{	        
        EventDAO ed = new EventDAO();
        // SubjectVO svo = new SubjectVO();	// setters로 값을 주고자 할 때,

        int no;
        String area;
        int count;
        
        System.out.print("\n주문번호 : ");
        no = Integer.parseInt(scan.nextLine());
        
        System.out.print("지역 : ");
        area = scan.nextLine();
        
        System.out.print("수량 : ");
        count = Integer.parseInt(scan.nextLine());
        
        // num, name을 줄 수 있음. no는 nextval이므로 불가.
        // EventVO evo = new EventVO(no, area, count);
        EventVO evo = new EventVO(no, area, count);
        boolean successFlag = ed.eventInsert(evo);
        
        if (successFlag == false)
		{
			System.out.println("입력처리 실패.");
		} else
		{
			System.out.println("이벤트 신청 완료!");
		}

	}	
	
	// 입력받은 주문내역 출력.
	public static void printEventList(ArrayList <EventVO> eventList)
	{
		System.out.println("---------------------------------------------------------------------------------");
		
		for (EventVO p : eventList)
		{
			System.out.println(p.toString());
		}
		
		System.out.println("---------------------------------------------------------------------------------");
	}
}