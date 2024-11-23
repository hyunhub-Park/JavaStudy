package com.kh.cafedbMVCProject.model;

public class CafeMenuVO
{
	private int no;	// pk
	private String drink;
	private String snack;
	private String dessert;
	// private String inOrout;	// check 'Y'or'N'
		
	public CafeMenuVO()
	{
	}
	
	public CafeMenuVO(String drink, String snack, String dessert)
	{
		super();
		this.drink = drink;
		this.snack = snack;
		this.dessert = dessert;
	}
	
	public CafeMenuVO(int no, String drink, String snack, String dessert)
	{
		super();
		this.no = no;
		this.drink = drink;
		this.snack = snack;
		this.dessert = dessert;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public String getSnack() {
		return snack;
	}

	public void setSnack(String snack) {
		this.snack = snack;
	}

	public String getDessert() {
		return dessert;
	}

	public void setDessert(String dessert) {
		this.dessert = dessert;
	}

	@Override
	public String toString() 
	{
		return "CafeMenuVO [no=" + no + ", drink=" + drink + ", snack=" + snack + ", dessert=" + dessert + "]";
	}
}
