package com.kh.cafedbMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.cafedbMVCProject.model.OrderCheckAllVO;
import com.kh.cafedbMVCProject.model.ReviewVO;

public class ReviewRegisterManager
{
	public static Scanner scan = new Scanner(System.in);

	public static void selectManager() throws SQLException
	{
		ArrayList <ReviewVO> reviewList = new ArrayList<ReviewVO>();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		reviewList = reviewDAO.reviewSelect();	// 데이터를 받았으니, 화면에 출력해야 함. jsp로 만들 것.
		// printStudentList(studentList);
		
		if (reviewList == null)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printReviewList(reviewList);
	}
	
	public void selectAllManager()
	{
		ReviewDAO rdao = new ReviewDAO();
		ArrayList <OrderCheckAllVO> OrderCheckAllList = new ArrayList <OrderCheckAllVO>();
		
		/*OrderCheckAllVO ocavo = new OrderCheckAllVO();

		System.out.println("주문번호 >> ");
		int input = scan.nextInt();*/

		
		
		OrderCheckAllList = rdao.orderCheckAllSelect();
		if (OrderCheckAllList == null)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printOrderCheckAllList(OrderCheckAllList);		
	}
	
	public void selectAllselManager() throws SQLException
	{
		ReviewDAO rdao = new ReviewDAO();
		ArrayList <OrderCheckAllVO> OrderCheckAllList = new ArrayList <OrderCheckAllVO>();
		
		/*OrderCheckAllVO ocavo = new OrderCheckAllVO();

		System.out.println("주문번호 >> ");
		int input = scan.nextInt();*/

		System.out.print("주문번호 >> ");
		int input = scan.nextInt();
		
		OrderCheckAllVO ocavo = new OrderCheckAllVO(input);
		OrderCheckAllList = rdao.orderCheckSel(ocavo);
		if (OrderCheckAllList == null)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printOrderCheckAllList(OrderCheckAllList);		
	}
	
	public static void insertManager() throws SQLException
	{	        
        ReviewDAO rd = new ReviewDAO();
        // SubjectVO svo = new SubjectVO();	// setters로 값을 주고자 할 때,

        int m_num;
        String review;
        
        System.out.print("주문번호 : ");
        m_num = Integer.parseInt(scan.nextLine());
        
        System.out.print("리뷰 작성 : ");
        review = scan.nextLine();
        
        // num, name을 줄 수 있음. no는 nextval이므로 불가.
        // EventVO evo = new EventVO(no, area, count);
        ReviewVO rvo = new ReviewVO(m_num, review);
        boolean successFlag = rd.reviewInsert(rvo);
        
        if (successFlag == false)
		{
			System.out.println("입력처리 실패.");
		} else
		{
			System.out.println("리뷰 작성 완료!");
		}
	}	
	
	private void printOrderCheckAllList(ArrayList <OrderCheckAllVO> OrderCheckAllList)
	{

		System.out.println("---------------------------------------------------------------------------------");
		for (OrderCheckAllVO p : OrderCheckAllList)
		{
			System.out.println(p.toString());
		}
		System.out.println("---------------------------------------------------------------------------------");
	}
	
	// 입력받은 주문내역 출력.
	public static void printReviewList(ArrayList <ReviewVO> reviewList)
	{
		System.out.println("---------------------------------------------------------------------------------");
		
		for (ReviewVO p : reviewList)
		{
			System.out.println(p.toString());
		}
		
		System.out.println("---------------------------------------------------------------------------------");
	}
}