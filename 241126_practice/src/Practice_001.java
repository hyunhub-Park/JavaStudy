import com.kh.subjectMVCProject.controller.LessonDAO;
import com.kh.subjectMVCProject.model.LessonVO;

public class Practice_001
{
	public void insertManager ()
	{
		LessonDAO ldao = new LessonDAO();
		
		// 화면으로부터 입력받는다.
		// 화면으로부터 주문번호 입력받기.
		System.out.print("주문번호를 입력하세요.");
		int input = Integer.parseInt(sc.nextLine());
		
		// String abbre = (sc.nextLine()).trim();	// 양쪽의 공간을 없애는 trim().
		
		// System.out.print("과목명 입력(O-운영 / A-어셈블 / C-컴파일  / J-자료  / P-프로그래밍 / D-디비 / S-소프트공학)>>");
		// String name = (sc.nextLine()).trim();	// 양쪽의 공간을 없애는 trim().
		
		// VO vo = new VO(생성자.);
		LessonVO lvo = new LessonVO(abbre, name);
		
		boolean successFlag = ldao.lessonInsert(lvo);
		
		// 화면출력.
		if (successFlag == true)
		{
			System.out.println(name + "번 입력 완료.");
		} else
		{
			System.out.println(name + "번 입력 실패.");
		}
	}

}
