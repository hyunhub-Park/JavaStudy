package com.kh.cafedbMVCProject.model;

public class OrderCheckAllVO
{
	private int m_num;	// fk(MENU테이블로부터 NO참조.)
	private String review;	
	
	private String drink;
	private String snack;
	private String dessert;
	
	public OrderCheckAllVO()
	{
		super();
	}
	
	public OrderCheckAllVO(int m_num)
	{
		super();
		this.m_num = m_num;
	}

	public OrderCheckAllVO(int m_num, String drink, String snack, String dessert, String review)
	{
		super();
		this.m_num = m_num;
		this.drink = drink;
		this.snack = snack;
		this.dessert = dessert;
		this.review = review;
	}

	public int getM_num()
	{
		return m_num;
	}

	public void setM_num(int m_num)
	{
		this.m_num = m_num;
	}

	public String getReview()
	{
		return review;
	}

	public void setReview(String review)
	{
		this.review = review;
	}

	public String getDrink()
	{
		return drink;
	}

	public void setDrink(String drink)
	{
		this.drink = drink;
	}

	public String getSnack()
	{
		return snack;
	}

	public void setSnack(String snack)
	{
		this.snack = snack;
	}

	public String getDessert()
	{
		return dessert;
	}

	public void setDessert(String dessert)
	{
		this.dessert = dessert;
	}

	@Override
	public String toString()
	{
		return "주문번호 " + String.format("%5d", m_num) + ">>" + String.format("%5s", drink) +
				String.format("%5s", snack) + String.format("%5s", dessert) + " 주문자의 한 줄 리뷰 <" + String.format("%5s", review) + ">";
	}
}