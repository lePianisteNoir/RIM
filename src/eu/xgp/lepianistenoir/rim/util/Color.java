package eu.xgp.lepianistenoir.rim.util;

import java.util.HashMap;
import java.util.Map;

public enum Color 
{
	BLACK("0"),
	DARK_BLUE("1"),
	DARK_GREEN("2"),
	DARK_AQUA("3"),
	DARK_RED("4"),
	PURPLE("5"),
	GOLD("6"),
	GRAY("7"),
	DARK_GRAY("8"),
	INDIGO("9"),
	BRIGHT_GREEN("a"),
	AQUA("b"),
	RED("c"),
	PINK("d"),
	YELLOW("e"),
	WHITE("f"),
	SPECIAL("k"),
	BOLD("l"),
	STRIKE("m"),
	UNDERLINE("n"),
	ITALIC("o"),
	RESET("r");

	private String id;

	Color(String id)
	{
		this.id = id;
	}

	private static final Map<String, Color> ids = new HashMap<String, Color>();

	static 
	{
		for (Color c : Color.values())
		{
			ids.put(c.getId(), c);
		}
	}

	public String getId() 
	{
		return id;
	}

	public String getPermissionString()
	{
		return "rim.chatcolor." + this.toString().toLowerCase();
	}
}
