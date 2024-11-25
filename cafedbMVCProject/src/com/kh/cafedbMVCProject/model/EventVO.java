package com.kh.cafedbMVCProject.model;

public class EventVO
{
	private int no;	// fk(MENU테이블로부터 NO참조.)
	private String area;	// pk
	private int count;
	
	private String dessert;
	private String drink;
	private String snack;

	public EventVO()
	{
	}
	
	public EventVO(String area, int count)
	{
		super();
		this.area = area;
		this.count = count;
	}
	
	public EventVO(int no, String area, int count)
	{
		super();
		this.no = no;
		this.area = area;
		this.count = count;
	}
	

	public int getNo()
	{
		return no;
	}

	public void setNo(int no)
	{
		this.no = no;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	@Override
	public String toString()
	{
		return "주문번호 " + String.format("%5d", no) +  ">>" + "이벤트 참여매장은 [" + String.format("%5s", area) + "]이며, 총 주문 수량은 " +
							String.format("%5s", count) + "개 입니다.";
	}
}