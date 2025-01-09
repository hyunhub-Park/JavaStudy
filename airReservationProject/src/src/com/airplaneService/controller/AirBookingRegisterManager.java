package src.com.airplaneService.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import src.com.airplaneService.model.AirBookingCustomerVO;

public class AirBookingRegisterManager
{
	public Scanner scan = new Scanner(System.in);

	public void insertManager ()	// 1번 메뉴 <만 14세 이상 가입하기>
	{
		AirBookingCustomerDAO abcdao = new AirBookingCustomerDAO();

		System.out.println("*가입정보를 입력해주세요.");
		
		System.out.print("성명 : ");
		String name = scan.nextLine();
		
		String birth = null;
		String phone = null;
		String id = null;
		String pw = null;
	
			System.out.print("생년월일(YYYYMMDD) : ");
			birth = scan.nextLine();
			

				System.out.println("생년월일 8자리를 다시 입력해주세요.(YYYYMMDD)");
			
			// ⛔추가 제약
			System.out.print("휴대폰번호 : ");
			phone = scan.nextLine();
			
	
				System.out.println("휴대폰번호 13자리를 다시 입력해주세요.(NNN-NNNN-NNNN)");
	
		

			// ⛔추가 제약
			System.out.print("휴대폰번호 : ");
			phone = scan.nextLine();

				System.out.println("휴대폰번호 13자리를 다시 입력해주세요.(NNN-NNNN-NNNN)");
	

	
			// ⛔추가 제약
			System.out.print("아이디(8자리@이메일.주소) : ");
			id = scan.nextLine();
	
				System.out.println("8자리 이상으로 다시 입력해주세요.(NNNNNNNN@naver.com)");
		

			// ⛔추가 제약
			System.out.print("비밀번호 : ");
			pw = scan.nextLine();
			
	
				System.out.println("6자리 이상으로 다시 입력해주세요.");

		
		/////////////////////////////
		/*System.out.println("REGIST : ");
		String regist = scan.nextLine();*/
		
		/////////////////////////////
		/*System.out.print("RIGHT : ");
		int right = Integer.parseInt(scan.nextLine());*/
		
//		// ⛔추가 제약
//		System.out.print("아이디(8자리@이메일.주소) : ");
//		String id = scan.nextLine();
//		
//		// ⛔추가 제약
//		System.out.print("비밀번호 : ");
//		String pw = scan.nextLine();
		
		///////////////////////////////
		/*System.out.println("COUNT : ");
		int count = Integer.parseInt(scan.nextLine());*/

		AirBookingCustomerVO abcvo = new AirBookingCustomerVO(name, birth, phone, id, pw);
		
		boolean successFlag;
		try
		{
			successFlag = abcdao.insertDB(abcvo);
			if (successFlag == true)
			{
				System.out.println(name + "님의 가입이 완료되었습니다.");
			} else
			{
				System.out.println(name + "님의 가입이 완료되지않았습니다.");
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}
	
	public void selectManager ()	// <가입자 전체 정보 출력.>
	{
		AirBookingCustomerDAO abcdao = new AirBookingCustomerDAO();
		ArrayList <AirBookingCustomerVO> airBookingCustomerVOList = new ArrayList <AirBookingCustomerVO>();
		
		// AirBookingCustomerVO abcvo = new AirBookingCustomerVO();

//		System.out.print("조회할 아이디를 입력해주세요 : ");
//		String id = scan.nextLine().trim();
//		
//		System.out.print("가입 시 등록한 휴대폰번호를 입력해주세요 : ");
//		String phone = scan.nextLine().trim();
		
		airBookingCustomerVOList = abcdao.selectDB();
		
		if (airBookingCustomerVOList == null)
		{
			System.out.println("출력할 데이터가 존재하지 않습니다.");
			return;
		}
		printAirBookingCustomerVOList(airBookingCustomerVOList);
		
	}

	
	
	
	
	public void selectCheckManager ()	// 3번 메뉴 <내 가입정보 확인하기> - 아이디, 휴대폰번호 입력받는 조건.
	{		
		AirBookingCustomerDAO abcdao = new AirBookingCustomerDAO();
		ArrayList <AirBookingCustomerVO> airBookingCustomerVOList = new ArrayList <AirBookingCustomerVO>();
		
		// AirBookingCustomerVO abcvo = new AirBookingCustomerVO();

		System.out.print("조회할 아이디를 입력해주세요 : ");
		String id = scan.nextLine().trim();
		
		System.out.print("가입 시 등록한 휴대폰번호를 입력해주세요 : ");
		String phone = scan.nextLine().trim();
		
		airBookingCustomerVOList = abcdao.selectCheckDB(id, phone);
		
		if (airBookingCustomerVOList == null)
		{
			System.out.println("출력할 데이터가 존재하지 않습니다.");
			return;
		}
		printAirBookingCustomerVOList(airBookingCustomerVOList);
	}

	public void updateManager ()	// 4번 메뉴 <내 가입정보 수정하기>
	{
		AirBookingCustomerDAO abcdao = new AirBookingCustomerDAO();
		
		////////////////////// 안되면 빼기.
//		ArrayList <AirBookingCustomerVO> airBookingCustomerVOList;
//
//		try
//		{
//			airBookingCustomerVOList = abcdao.selectCheckDB(name, id);	// 출력이 되려나????????????
//			// 화면출력.
//			if (airBookingCustomerVOList.size() != 0)
//			{
//				printAirBookingCustomerVOList(airBookingCustomerVOList);
//			} else
//			{
//				System.out.println("출력할 데이터가 존재하지 않습니다.");
//				return;
//			}
//		} catch (Exception e)
//		{
//			System.out.println(e.toString());
//		}
		
		System.out.print("가입하신 아이디를 입력하세요 : ");
		String id = scan.nextLine().trim();

		System.out.print("수정할 이름을 입력하세요 : ");
		String name = scan.nextLine().trim();
		
		System.out.print("수정할 생년월일을 입력하세요(YYYYMMDD) : ");
		String birth = scan.nextLine().trim();
		
		System.out.print("수정할 휴대폰번호를 입력하세요 : ");
		String phone = scan.nextLine().trim();
		
		
//		////////////////////////////////////////////
//		System.out.print("수정할 COUNT를 입력하세요 : ");
//		int count = Integer.parseInt(scan.nextLine());
		
		AirBookingCustomerVO abcvo = new AirBookingCustomerVO();
		
		boolean successFlag;
		
		abcvo = new AirBookingCustomerVO(name, birth, phone, id);
		
		try
		{
			successFlag = abcdao.updateDB(abcvo);
			// 화면출력.
			if (successFlag == true)
			{
				System.out.println(name + "님의 회원정보 수정이 완료되었습니다!");
			} else
			{
				System.out.println(name + "님의 회원정보 수정이 완료되지 않았습니다!");
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
	}
	
	public void deleteManager ()	// 5번 메뉴 <회원 탈퇴하기>
	{
		AirBookingCustomerDAO abcdao = new AirBookingCustomerDAO();

		AirBookingCustomerVO abcvo = new AirBookingCustomerVO();

//		ArrayList <LandPriceVO> landPriceVOList = lpdao.landPriceSelect();
//
//		
//		 // 화면출력.
//		if (landPriceVOList.size() != 0)
//		{
//			printLandPriceList(landPriceVOList);
//		} else
//		{
//			System.out.println("출력 데이터 없음.");
//			return;
//		}
		
		System.out.print("탈퇴할 아이디를 입력해주세요 : ");
		String id = scan.nextLine().trim();
		
		System.out.print("가입 시 등록한 휴대폰번호를 입력해주세요 : ");
		String phone = scan.nextLine().trim();

		abcvo.setId(id);
		abcvo.setPhone(phone);
		boolean successFlag = abcdao.deleteDB(abcvo);
		
		// 화면출력.
		if (successFlag == true)
		{
			System.out.println("아이디 " + id + "님의 회원 탈퇴가 완료되었습니다.");
		} else
		{
			System.out.println("아이디 " + id + "님의 회원 탈퇴가 완료되지 않았습니다.");
		}
	}
	
	public void selectCountManager ()
	{
//		AirBookingCustomerDAO abcdao = new AirBookingCustomerDAO();
//		abcdao.selectCountDB();
//		
//		AirBookingCustomerVO abcvo = new AirBookingCustomerVO();
//		abcvo.countPrint();
		
		AirBookingCustomerDAO abcdao = new AirBookingCustomerDAO();
		// ArrayList <AirBookingCustomerVO> airBookingCustomerVOList = new ArrayList <AirBookingCustomerVO>();
		
		AirBookingCustomerVO abcvo = new AirBookingCustomerVO();
		
		String print = 	abcdao.selectCountDB();
		System.out.print(print);
//		abcvo.countPrint();
		
//		airBookingCustomerVOList = abcdao.selectCountDB();
//		
//		if (airBookingCustomerVOList == null)
//		{
//			System.out.println("출력할 데이터가 존재하지 않습니다.");
//			return;
//		}
//		printAirBookingCustomerVOList(airBookingCustomerVOList);
	}
	
	public void insertInfoManager ()
	{
		
	}
	
	
	
	private void printAirBookingCustomerVOList(ArrayList <AirBookingCustomerVO> airBookingCustomerVOList)	// 출력메소드.
	{
		for (AirBookingCustomerVO p : airBookingCustomerVOList)
		{
			System.out.println(p);
		}
	}
	
	
}
