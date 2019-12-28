package eu.xgp.lepianistenoir.rim.db.commands.rules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.xgp.lepianistenoir.rim.connection.DBConnection;

public class CommandRules implements CommandExecutor
{
	private boolean status = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		readRulesFromDB(sender);

		return true;
	}

	private void readRulesFromDB(CommandSender sender)
	{
		ResultSet result;

		try
		{
			Connection connect = DBConnection.getConnection();

			Statement state = connect.createStatement();

			String query = "SELECT * FROM rules ORDER BY id";

			result = state.executeQuery(query);

			query = "SELECT * FROM rules";

			result = state.executeQuery(query);

			sender.sendMessage("Server Rules:" + System.lineSeparator()); 

			checkTableContent(sender, result);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void checkTableContent(CommandSender sender, ResultSet result)
	{
		try 
		{
			if(result.next())
			{
				sender.sendMessage(result.getString("id") + ") " + " Desc: " + result.getString("description"));

				status = true;

				checkTableContent(sender, result);
			}
			else 
			{
				if(status == false)
				{
					sender.sendMessage("The table rules is empty!");
				}

				status = false;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
