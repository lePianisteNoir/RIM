package eu.xgp.lepianistenoir.rim.bean;

public class Info 
{
	private String playerName;
	private String description;

	public Info(String playerName, String description) 
	{
		this.playerName  = playerName;
		this.description = description;
	}

	public String getPlayerName() 
	{
		return playerName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString() 
	{
		return "Info [playerName=" + playerName + ", description=" + description + "]";
	}
}
