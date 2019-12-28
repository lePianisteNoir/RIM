package eu.xgp.lepianistenoir.rim.db.commands.info;

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

public class CommandDeleteInfo implements CommandExecutor
{
	private boolean status = false;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof ConsoleCommandSender)
		{
			String playerName = args[0];

			String param = args[1];

			deleteInfoOnDB(sender, command, args, playerName, param);
		}
		else if(sender instanceof Player)
		{
			if(sender.hasPermission("commandInfo.set") || sender.hasPermission("commandInfo.*")) 
			{
				String playerName = args[0];

				String param = args[1];

				deleteInfoOnDB(sender, command, args, playerName, param);
			}
			else 
			{
				sender.sendMessage(Util.NO_PERMISSION());
			}
		}

		return true;
	}

	private void deleteInfoOnDB(CommandSender sender, Command command, String[] args, String playerName, String param)
	{
		ResultSet result;

		try 
		{
			Connection connect = DBConnection.getConnection();

			String query = "SELECT playerName FROM info";

			Statement state = connect.createStatement();

			result = state.executeQuery(query);

			checkPlayerName(sender, result, playerName, param);

		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void checkPlayerName(CommandSender sender, ResultSet result, String playerName, String param) 
	{
		try 
		{
			if(result.next())
			{
				String value =  result.getString("playerName");

				if(playerName.equalsIgnoreCase(value)) 
				{
					if(param.equalsIgnoreCase("player") || param.contains("player")) 
					{
						Connection connect = DBConnection.getConnection();

						PreparedStatement posted = connect.prepareStatement("DELETE FROM info WHERE playerName = " + "'" + playerName + "'");
						posted.executeUpdate();

						sender.sendMessage("The info: " + playerName + " has been deleted successfully");
					}
					else if(param.equalsIgnoreCase("description") || param.contains("description"))
					{
						Connection connect = DBConnection.getConnection();

						PreparedStatement posted = connect.prepareStatement("UPDATE info SET description = " + "''" + "WHERE playerName = " + "'" + playerName + "'"); 
						posted.executeUpdate();

						sender.sendMessage("The info description about" + playerName + " has been deleted successfully");
					}
					status = false;
				}
				else 
				{
					checkPlayerName(sender, result, playerName, param);
				}
			}
			else 
			{
				if(status == false)
				{
					sender.sendMessage("That info does not exists !");
				}
				status = false;
			}
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
