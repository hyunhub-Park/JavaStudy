package com.kh.cafedbMVCProject.model;

public class EventVO
{
	private int no;	// fk(MENU테이블로부터 NO참조.)
	private String area;	// pk
	private int count;

	public EventVO()
	{
	}
	
	public EventVO(int no, String area, int count)
	{
		super();
		this.no = no;
		this.area = area;
		this.count = count;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString()
	{
		return "EventDAO [no=" + no + ", area=" + area + ", count=" + count + "]";
	}	
}