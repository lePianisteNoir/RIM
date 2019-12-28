package eu.xgp.lepianistenoir.rim.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import eu.xgp.lepianistenoir.rim.util.Util;

public class DBConnection 
{
	private static final String DB_DRIVER  = "com.mysql.jdbc.Driver";
	private static final String DB_HOST = Util.GET_HOST();
	
	private static final int    DB_PORT = Util.GET_PORT();
	
	private static final String DB_NAME     = Util.GET_DATABASE();
	private static final String DB_STRING   = "createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String DB_USERNAME = Util.GET_USERNAME();
	private static final String DB_PASSWORD = Util.GET_PASSWORD();
	
	public static Connection getConnection()
	{
		try 
		{
			Class.forName(DB_DRIVER);
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME  + "?" + DB_STRING , DB_USERNAME , DB_PASSWORD);
			
			createTableRules(connect);
			createTableInfo(connect);
			
			
			return connect;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	private static void createTableRules(Connection connect) 
	{
		try 
		{
			PreparedStatement create = connect.prepareStatement("CREATE TABLE IF NOT EXISTS rules(id INT(11) PRIMARY KEY NOT NULL, description TEXT NOT NULL)");

			create.executeUpdate();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private static void createTableInfo(Connection connect) 
	{
		try 
		{
			PreparedStatement create = connect.prepareStatement("CREATE TABLE IF NOT EXISTS info(playerName VARCHAR(16) NOT NULL, description TEXT)");

			create.executeUpdate();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
