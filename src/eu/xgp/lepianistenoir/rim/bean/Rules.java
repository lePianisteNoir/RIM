package eu.xgp.lepianistenoir.rim.bean;

public class Rules
{
	private int id;

	private String description;
	
	public Rules(int id, String description) 
	{
		this.id 		 = id;
		this.description = description;
	}

	public int getId()
	{
		return id;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	@Override
	public String toString() 
	{
		return "Rules [id=" + id + ", description=" + description + "]";
	}
}
