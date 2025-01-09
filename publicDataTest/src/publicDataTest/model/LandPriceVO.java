package publicDataTest.model;

public class LandPriceVO
{
	private int nodeno;	// <nodeno>44810</nodeno>
						// PK.
	private double gpslati;	// <gpslati>36.43535</gpslati>
	private double gpslong;	// <gpslong>127.3863</gpslong>
	private String nodeId;	// <nodeid>DJB8001793</nodeid>
	private String nodenm;	// <nodenm>송강전통시장</nodenm>
	
	public LandPriceVO()
	{
	}

	public LandPriceVO(int nodeno, double gpslati, double gpslong, String nodeId, String nodenm)
	{
		super();
		this.nodeno = nodeno;
		this.gpslati = gpslati;
		this.gpslong = gpslong;
		this.nodeId = nodeId;
		this.nodenm = nodenm;
	}

	public int getNodeno()
	{
		return nodeno;
	}

	public void setNodeno(int nodeno)
	{
		this.nodeno = nodeno;
	}

	public double getGpslati()
	{
		return gpslati;
	}

	public void setGpslati(double gpslati)
	{
		this.gpslati = gpslati;
	}

	public double getGpslong()
	{
		return gpslong;
	}

	public void setGpslong(double gpslong)
	{
		this.gpslong = gpslong;
	}

	public String getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}

	public String getNodenm()
	{
		return nodenm;
	}

	public void setNodenm(String nodenm)
	{
		this.nodenm = nodenm;
	}

	@Override
	public String toString()
	{
		return "LandPrice [nodeno=" + nodeno + ", gpslati=" + gpslati + ", gpslong=" + gpslong + ", nodeId=" + nodeId
				+ ", nodenm=" + nodenm + "]";
	}
}