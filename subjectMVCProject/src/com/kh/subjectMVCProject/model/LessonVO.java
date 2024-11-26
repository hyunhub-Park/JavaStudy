package com.kh.subjectMVCProject.model;

public class LessonVO {
	private int no; 		// --pk seq
	private String abbre; 	// --과목요약
	private String name; 	// --과목이름
	
	public LessonVO()
	{
	}
	
	public LessonVO(String abbre, String name)
	{
		super();
		this.abbre = abbre;
		this.name = name;
	}
	
	public LessonVO(int no, String abbre, String name)
	{
		super();
		this.no = no;
		this.abbre = abbre;
		this.name = name;
	}
	
	public int getNo()
	{
		return no;
	}
	
	public void setNo(int no)
	{
		this.no = no;
	}
	
	public String getAbbre()
	{
		return abbre;
	}
	
	public void setAbbre(String abbre)
	{
		this.abbre = abbre;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return "[no=" + no + ", abbre=" + abbre + ", name=" + name + "]";
	}
}












//package com.kh.subjectMVCProject.model;
//
//public class LessonVO
//{
//	 private int no;	// -- pk, seq
//	 private String arre;	// -- 과목요약.  
//	 private String name;	// -- 과목이름.
//	public LessonVO() {
//		super();
//	}
//	public LessonVO(int no, String arre, String name) {
//		super();
//		this.no = no;
//		this.arre = arre;
//		this.name = name;
//	}
//	
//	
//	
//	public int getNo() {
//		return no;
//	}
//	public void setNo(int no) {
//		this.no = no;
//	}
//	public String getArre() {
//		return arre;
//	}
//	public void setArre(String arre) {
//		this.arre = arre;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	@Override
//	public String toString() {
//		return "LessonVO [no=" + no + ", arre=" + arre + ", name=" + name + "]";
//	}
//
//	 
//	
//}
