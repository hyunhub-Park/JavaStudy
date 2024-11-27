package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeRegisterManager
{
	public static Scanner sc = new Scanner(System.in); 

	//전체 학생리스트를 출력요청
	public static void totalSelectManager()
	{
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		traineeList = tdao.traineeAllSelect(new TraineeVO());
		if(traineeList.size() <= 0)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeAllList(traineeList);
	}
	
	public static void selectManager()
	{
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		traineeList = tdao.traineeSelect(new TraineeVO());
		
		if(traineeList.size() <= 0)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeList(traineeList);
	}

	public static void insertManager()
	{
		TraineeDAO tdao = new TraineeDAO();
		// 3.statement
		// 학생번호를 입력해야하는데, 학번을 모를 수 있으니 이름을 검색 시 자신의 학번을 알 수 있도록 검색기능 추가하기.
		
		// 검색된 이름으로 학번과 이름, 이메일을 출력.
		StudentRegisterManager srm = new StudentRegisterManager();
		srm.selectNameSearchManager();
		
											// studentNameSelect가 static이기 때문에 객체를 새로 만들지 않아도 불러올 수 있음.
		
											// 객체를 만들어서 접근, 클래스로 접근. 구분을 확실히 할 것 !!!
//		ArrayList <StudentVO> studentList = StudentDAO.studentNameSelect(name);
//		for (StudentVO p : studentList)
//		{
//			System.out.println("Name : " + p.getName() + "Num : " + p.getNum() + "Email : " + p.getEmail());
//		}
		
		System.out.print("학생번호를 입력하세요 : ");
		String s_num = sc.nextLine();
		
		// Lesson의 abbreviation을 제공해야 함.
		// LessonDAO ldo = new LessonDAO();
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectSortManager();
		System.out.print("과목요약을 입력하세요 : ");
		String abbre = (sc.nextLine()).trim();
		
		System.out.print("전공 / 부전공 / 교양 입력 : ");
		String section = (sc.nextLine()).trim();
		
		TraineeVO traineeVO = new TraineeVO(0, s_num, abbre, section, null);
				
		boolean successFlag = tdao.traineeInsert(traineeVO);
		
		if(successFlag == true)
		{
			System.out.println("입력처리 성공");
		} else
		{
			System.out.println("입력처리 실패");
		}
	}

	public static void updateManager()
	{
		TraineeDAO tdao = new TraineeDAO();
		
		// Trainee 전체 내용 출력.
		selectManager();
		
		// 3.statement
		// 학생번호를 입력해야하는데, 학번을 모를 수 있으니 이름을 검색 시 자신의 학번을 알 수 있도록 검색기능 추가하기.
		System.out.print("수정할 번호를 입력하세요 : ");
		int no = Integer.parseInt(sc.nextLine());

		// 검색된 이름으로 학번과 이름, 이메일을 출력.
		StudentRegisterManager srm = new StudentRegisterManager();
		srm.selectNameSearchManager();
		
		System.out.print("학생번호를 입력하세요 : ");
		String s_num = sc.nextLine();
		
		// Lesson의 abbreviation을 제공해야 함.
		// LessonDAO ldo = new LessonDAO();
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectSortManager();
		
		System.out.print("과목요약을 입력하세요 : ");
		String abbre = (sc.nextLine()).trim();
		
		System.out.print("전공 / 부전공 / 교양 입력 : ");
		String section = (sc.nextLine()).trim();
		
		TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, null);
		
		boolean successFlag = tdao.traineeUpdate(traineeVO);
		
		if(successFlag == true)
		{
			System.out.println("수정 성공");
		} else
		{
			System.out.println("수정 실패");
		}
	}

	public static void deleteManager()
	{
		TraineeVO tvo = new TraineeVO();
		TraineeDAO tdao = new TraineeDAO();
		
		System.out.print("삭제할 수강신청 번호를 입력하세요 >> ");
		
		int no = Integer.parseInt(sc.nextLine());
		tvo.setNo(no);
		
		boolean successFlag = tdao.traineeDelete(tvo);
		
		if(successFlag == true)
		{
			System.out.println("삭제처리 성공");
		} else
		{
			System.out.println("삭제처리 실패");
		}
	}

	public static void sortManager() throws SQLException
	{
		TraineeDAO tdao = new TraineeDAO();
		
		ArrayList<TraineeVO> traineeList = null;
		
		traineeList = tdao.traineeSelectSort(new TraineeVO()); 
		
		if(traineeList.size() <= 0)
		{
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		
		printTraineeList(traineeList);
	}

	//전체 학생리스트를 출력진행
	public static void printTraineeList(ArrayList <TraineeVO> traineeList)
	{
		System.out.println("============================================");
		for( TraineeVO tvo :  traineeList)
		{
			System.out.println(tvo.toString());
		}
		System.out.println("============================================");
	}
	
	//전체 학생리스트를 출력진행
	public static void printTraineeAllList(ArrayList <TraineeVO> traineeList) {
		System.out.println("============================================");
		for(TraineeVO tvo :  traineeList)
		{
			System.out.println(tvo.toAllString());
		}
		System.out.println("============================================");
	}
}



//package com.kh.subjectMVCProject.controller;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import com.kh.subjectMVCProject.model.StudentVO;
//
//public class TraineeRegisterManager
//{
//	public static Scanner sc = new Scanner(System.in);
//	// 전체를 계산하는 프로그램.
//	// 전체 학생리스트 출력기능.
//	public static void totalSelectManager() throws SQLException
//	{
//		ArrayList <StudentVO> studentList = new ArrayList<StudentVO>();
//		studentList = StudentDAO.studentSelect();	// 데이터를 받았으니, 화면에 출력해야 함. jsp로 만들 것.
//		// printStudentList(studentList);
//		
//		if (studentList == null)
//		{
//			System.out.println("데이터가 존재하지 않습니다.");
//			return;
//		}
//		printStudentList(studentList);
//	}
//	
//	public static void insertManager() throws SQLException
//	{
//		// 3.statement
//		System.out.print("학생 이름을 입력하세요: ");
//		String name = sc.nextLine();
//		System.out.print("국어 점수를 입력하세요: ");
//		int kor = Integer.parseInt(sc.nextLine());
//		System.out.print("영어 점수를 입력하세요: ");
//		int eng = Integer.parseInt(sc.nextLine());
//		System.out.print("수학 점수를 입력하세요: ");
//		int mat = Integer.parseInt(sc.nextLine());
//		
//		StudentVO studentVO = new StudentVO();
//		boolean successFlag = StudentDAO.studentInsert(studentVO);
//		
//		if (successFlag == true)
//		{
//			System.out.println("입력처리 성공! - callback까지 다 된 것.");
//		} else
//		{
//			System.out.println("입력처리 실패.");
//		}
//	}	
//
//	public static void updateManager() throws SQLException
//	{
//		System.out.print("수정할 학생의 번호를 입력하세요: ");
//		int no = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 이름을 입력하세요: ");
//		String name = sc.nextLine();
//		System.out.print("새로운 국어 점수를 입력하세요: ");
//		int kor = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 영어 점수를 입력하세요: ");
//		int eng = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 수학 점수를 입력하세요: ");
//		int mat = Integer.parseInt(sc.nextLine());
//		
//		StudentVO svo = new StudentVO();
//		boolean successFlag = StudentDAO.stuUpdate(svo);
//		
//		if (successFlag == true)
//		{
//			System.out.println("입력처리 성공! - callback까지 다 된 것.");
//		} else
//		{
//			System.out.println("입력처리 실패.");
//		}
//	}
//
//	public static void deleteManager() throws SQLException
//	{
//		System.out.print("삭제할 학생 번호를 입력하세요: ");
//		int no = Integer.parseInt(sc.nextLine());
//		StudentVO svo = new StudentVO();
//		svo.setNo(no);
//		
//		boolean successFlag = StudentDAO.studentDelete(svo);
//		
//		if (successFlag == true)
//		{
//			System.out.println("삭제처리 성공! - callback까지 다 된 것.");
//		} else
//		{
//			System.out.println("삭제처리 실패.");
//		}
//	}
//	
//	public static void sortManager() throws SQLException
//	{
//		ArrayList <StudentVO> studentList = null;
//		studentList = StudentDAO.studentSort();
//		printStudentList(studentList);
//	}
//
//	
//	// 전체 학생리스트 출력 실행.
//	public static void printStudentList(ArrayList <StudentVO> studentList)
//	{
//		System.out.println("------------------------------------");
//		
//		for (StudentVO sv : studentList)
//		{
//			System.out.println(sv.toString());
//		}
//		
//		System.out.println("------------------------------------");
//	}
//}