package com.kh.cafedbMVCProject.model;

public class OrderCheckVO
{
	private int m_num;	// fk(MENU테이블로부터 NO참조.)
	private String e_area;	// fk(ORDER_CHECK테이블로부터 AREA참조.)
}
