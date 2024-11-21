package movieTest;

public class Movie
{
	private int id;	// ID       NOT NULL NUMBER(4)    
	private String title;	// TITLE             VARCHAR2(20) 
	private String director;	// DIRECTOR          VARCHAR2(30) 
	private String year;	// YEAR              VARCHAR2(10) 
	private int price;	// PRICE             NUMBER(6)    
	
	public Movie (int id, String title, String director, String year, int price)
	{
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.year = year;
		this.price = price;
	}
	
	public Movie (String title, String director, String year, int price)
	{
		super();
		this.title = title;
		this.director = director;
		this.year = year;
		this.price = price;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDirector()
	{
		return director;
	}

	public void setDirector(String director)
	{
		this.director = director;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	@Override
	public String toString ()
	{
		return "ID " + id + "번\tTITLE '" + title + "' | DIRECTOR '" + director + "' | YEAR '" + year + "' | PRICE '" + price + "'";
	}
}