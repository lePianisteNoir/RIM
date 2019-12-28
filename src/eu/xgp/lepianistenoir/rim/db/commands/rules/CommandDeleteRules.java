package eu.xgp.lepianistenoir.rim.db.commands.rules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import eu.xgp.lepianistenoir.rim.connection.DBConnection;
import eu.xgp.lepianistenoir.rim.util.Util;

public class CommandDeleteRules implements CommandExecutor
{
	private boolean status = false;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof ConsoleCommandSender)
		{
			int id = Integer.parseInt(args[0]);

			deleteRulesOnDB(sender, command, args, id);
		}
		else if(sender instanceof Player)
		{
			if(sender.hasPermission("commandRules.set") || sender.hasPermission("commandRules.*")) 
			{
				int id = Integer.parseInt(args[0]);

				deleteRulesOnDB(sender, command, args, id);
			}
			else 
			{
				sender.sendMessage(Util.NO_PERMISSION());
			}
		}

		return true;
	}

	private void deleteRulesOnDB(CommandSender sender, Command command, String[] args, int id)
	{
		ResultSet result;

		try 
		{
			Connection connect = DBConnection.getConnection();

			String query = "SELECT id FROM rules";

			Statement state = connect.createStatement();

			result = state.executeQuery(query);

			checkId(sender, id, result);

		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void checkId(CommandSender sender, int id, ResultSet result)
	{
		try 
		{
			if(result.next())
			{
				int value =  Integer.parseInt(result.getString("id"));

				if(id == value) 
				{
					Connection connect = DBConnection.getConnection();

					PreparedStatement posted = connect.prepareStatement("DELETE FROM rules WHERE id = " + "'" + id + "'" + ";");
					posted.executeUpdate();

					sender.sendMessage("The rule: " + id + " has been deleted successfully");

					status = false;
				}
				else 
				{
					checkId(sender, id, result);
				}
			}
			else 
			{
				if(status == false)
				{
					sender.sendMessage("That rule does not exists !");
				}

				status = false;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
