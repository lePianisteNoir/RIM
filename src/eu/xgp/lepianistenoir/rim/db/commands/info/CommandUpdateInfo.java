package eu.xgp.lepianistenoir.rim.db.commands.info;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import eu.xgp.lepianistenoir.rim.bean.Info;
import eu.xgp.lepianistenoir.rim.connection.DBConnection;
import eu.xgp.lepianistenoir.rim.util.Util;

public class CommandUpdateInfo implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof ConsoleCommandSender)
		{
			String playerName = args[0];

			StringBuffer result = new StringBuffer();

			for (int i = 1; i < args.length; i++) 
			{
				result.append(args[i]);
				result.append(" ");
			}

			String description = result.toString();

			Info info = new Info(playerName, description);

			updateRulesOnDB(sender, command, args, info);
		}
		else if(sender instanceof Player)
		{
			if(sender.hasPermission("commandInfo.update") || sender.hasPermission("commandInfo.*")) 
			{
				String playerName = args[0];

				StringBuffer result = new StringBuffer();

				for (int i = 1; i < args.length; i++) 
				{
					result.append(args[i]);
					result.append(" ");
				}

				String description = result.toString();

				Info info = new Info(playerName, description);

				updateRulesOnDB(sender, command, args, info);
			}
			else 
			{
				sender.sendMessage(Util.NO_PERMISSION());
			}
		}
		return true;
	}

	private void updateRulesOnDB(CommandSender sender, Command command, String[] args, Info info) 
	{
		try 
		{
			Connection connect = DBConnection.getConnection();

			String playerName  = info.getPlayerName();
			String description = info.getDescription();

			PreparedStatement posted = connect.prepareStatement("UPDATE info SET description = "  + "'" + description + "'"  + " WHERE playerName = " +  "'" + playerName + "'");
			posted.executeUpdate();

			sender.sendMessage("The info: " + playerName + " has been updated successfully");
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
