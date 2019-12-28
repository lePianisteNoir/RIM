package eu.xgp.lepianistenoir.rim.db.commands.rules;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import eu.xgp.lepianistenoir.rim.bean.Rules;
import eu.xgp.lepianistenoir.rim.connection.DBConnection;
import eu.xgp.lepianistenoir.rim.util.Util;

public class CommandUpdateRules implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof ConsoleCommandSender)
		{
			int id = Integer.parseInt(args[0]);

			StringBuffer result = new StringBuffer();

			for (int i = 1; i < args.length; i++) 
			{
				result.append(args[i]);
				result.append(" ");
			}

			String description = result.toString();

			Rules rules = new Rules(id, description);

			updateRulesOnDB(sender, command, args, rules);
		}
		else if(sender instanceof Player)
		{
			if(sender.hasPermission("commandRules.update") || sender.hasPermission("commandRules.*")) 
			{
				int id = Integer.parseInt(args[0]);

				StringBuffer result = new StringBuffer();

				for (int i = 1; i < args.length; i++) 
				{
					result.append(args[i]);
					result.append(" ");
				}

				String description = result.toString();

				Rules rules = new Rules(id, description);

				updateRulesOnDB(sender, command, args, rules);
			}
			else 
			{
				sender.sendMessage(Util.NO_PERMISSION());
			}
		}
		return true;
	}

	private void updateRulesOnDB(CommandSender sender, Command command, String[] args, Rules rules) 
	{
		try 
		{
			Connection connect = DBConnection.getConnection();

			int    id          = rules.getId();
			String description = rules.getDescription();

			PreparedStatement posted = connect.prepareStatement("UPDATE rules SET description = "  + "'" + description + "'"  + " WHERE id =" +  "'" + id + "'");
			posted.executeUpdate();

			sender.sendMessage("The rule: " + id + " has been updated successfully");
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
