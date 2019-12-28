package eu.xgp.lepianistenoir.rim.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import eu.xgp.lepianistenoir.rim.RIM;

public class Util 
{
	private static RIM rim = RIM.getInstance();

	private static FileConfiguration config = rim.getConfig();

	private static PluginDescriptionFile yml = rim.getDescription();

	public static String VERSION = yml.getVersion();
	public static String AUTHOR  = yml.getAuthors().get(0);
	public static String NAME    = yml.getName();

	public static List<Command> COMMANDS = PluginCommandYamlParser.parse(rim);

	public static Map<String, Map<String, Object>> COMMAND_PROPERTIES = yml.getCommands();

	private static String format(String str) 
	{
		return ChatColor.translateAlternateColorCodes('&', str).replaceAll("%n", "\n");
	}

	public static String NO_PERMISSION() 
	{
		return format(config.getString("messages.no-permission"));
	}

	public static String USAGE() 
	{
		return format(config.getString("messages.rim-usage"));
	}

	public static String CONFIG_RELOADED() 
	{
		return format(config.getString("messages.config-reloaded"));
	}

	public static String PLUGIN_DISABLED() 
	{
		return format(config.getString("messages.plugin-disabled"));
	}
	
	public static String DB_ENABLED() 
	{
		return format(config.getString("messages.db-enabled"));
	}
	
	public static String CONNECTION_ERROR() 
	{
		return format(config.getString("messages.connection-error"));
	}

	public static String CONNECTED_TO_DB() 
	{
		return format(config.getString("messages.connected-to-db"));
	}
	
	public static boolean GET_DB_ENABLED() 
	{
		return config.getBoolean("db_info.enabled");
	}

	public static String GET_HOST() 
	{
		return config.getString("db_info.hostname");
	}

	public static int GET_PORT() 
	{
		return config.getInt("db_info.port-number");
	}

	public static String GET_DATABASE() 
	{
		return config.getString("db_info.db-name");
	}
	public static String GET_USERNAME() 
	{
		return config.getString("db_info.username");
	}

	public static String GET_PASSWORD() 
	{
		return config.getString("db_info.password");
	}

	public static void reloadConfig() 
	{
		rim.reloadConfig();
		config = rim.getConfig();
	}

	public static boolean isDifferent(String message) 
	{
		String colourised = ChatColor.translateAlternateColorCodes('&', message);
		return !colourised.equals(message);
	}

	public static List<Color> colorsInMessage(String message) 
	{
		List<Color> colors = new ArrayList<Color>();
		for (Color c : Color.values()) 
		{
			if (message.contains("&" + c.getId())) 
			{
				colors.add(c);
			}
		}
		return colors;
	}
}
