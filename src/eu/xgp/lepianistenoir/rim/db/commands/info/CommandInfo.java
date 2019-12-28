package eu.xgp.lepianistenoir.rim.db.commands.info;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.xgp.lepianistenoir.rim.connection.DBConnection;

public class CommandInfo implements CommandExecutor
{
	private boolean status = false;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		readInfoFromDB(sender);

		return true;
	}

	private void readInfoFromDB(CommandSender sender) 
	{
		ResultSet result;

		try
		{
			Connection connect = DBConnection.getConnection();

			Statement state = connect.createStatement();

			String query = "SELECT * FROM info";

			result = state.executeQuery(query);

			sender.sendMessage("Server Staff Info:" + System.lineSeparator()); 

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
				sender.sendMessage("PlayerName: " + result.getString("playerName") + ", " + "Desc: " + result.getString("description"));

				status = true;

				checkTableContent(sender, result);
			}
			else 
			{
				if(status == false)
				{
					sender.sendMessage("The table info is empty!");
				}

				status = false;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
