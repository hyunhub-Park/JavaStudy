package com.kh.cafedbMVCProject.model;

public class ReviewVO
{
	private int m_num;	// fk(MENU테이블로부터 NO참조.)
	private String review;	
	
	private String drink;
	private String snack;
	private String dessert;
	
	public ReviewVO()
	{
	}

	public ReviewVO(int m_num)
	{
		super();
		this.m_num = m_num;
	}
	
	public ReviewVO(String review)
	{
		super();
		this.review = review;
	}
	
	public ReviewVO(int m_num, String review)
	{
		super();
		this.m_num = m_num;
		this.review = review;
	}
	
	public ReviewVO(int m_num, String drink, String snack, String dessert, String review)
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
		return "ReviewVO [m_num=" + m_num + ", review=" + review + ", drink=" + drink + ", snack=" + snack
				+ ", dessert=" + dessert + "]";
	}
}