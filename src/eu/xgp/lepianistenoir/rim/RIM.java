package eu.xgp.lepianistenoir.rim;

import org.bukkit.plugin.java.JavaPlugin;

import eu.xgp.lepianistenoir.rim.command.CommandRIM;
import eu.xgp.lepianistenoir.rim.db.commands.info.CommandDeleteInfo;
import eu.xgp.lepianistenoir.rim.db.commands.info.CommandInfo;
import eu.xgp.lepianistenoir.rim.db.commands.info.CommandSetInfo;
import eu.xgp.lepianistenoir.rim.db.commands.info.CommandUpdateInfo;
import eu.xgp.lepianistenoir.rim.db.commands.rules.CommandDeleteRules;
import eu.xgp.lepianistenoir.rim.db.commands.rules.CommandRules;
import eu.xgp.lepianistenoir.rim.db.commands.rules.CommandSetRules;
import eu.xgp.lepianistenoir.rim.db.commands.rules.CommandUpdateRules;
import eu.xgp.lepianistenoir.rim.util.Util;

public class RIM extends JavaPlugin
{
	private static RIM rim;

	public static RIM getInstance() 
	{
		return rim;
	}

	@Override
	public void onEnable() 
	{
		try 
		{
			saveDefaultConfig();

			rim = this;

			if(Util.GET_DB_ENABLED() == true)
			{
				System.out.println(Util.DB_ENABLED()); 

				registerCommands();
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		super.onEnable();
	}

	private void registerCommands() 
	{
		getCommand("rim").setExecutor(new CommandRIM());

		getCommand("rules").setExecutor(new CommandRules());
		getCommand("info" ).setExecutor(new CommandInfo());

		getCommand("setRules").setExecutor(new CommandSetRules());
		getCommand("setInfo" ).setExecutor(new CommandSetInfo());

		getCommand("delRules").setExecutor(new CommandDeleteRules());
		getCommand("delInfo" ).setExecutor(new CommandDeleteInfo());

		getCommand("updRules").setExecutor(new CommandUpdateRules());
		getCommand("updInfo" ).setExecutor(new CommandUpdateInfo());
	}

	@Override
	public void onDisable() 
	{	
		super.onDisable();
	}

	@Override
	public void onLoad() 
	{
		super.onLoad();
	}
}
