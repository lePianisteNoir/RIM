package eu.xgp.lepianistenoir.rim.command;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.xgp.lepianistenoir.rim.util.Util;

public class CommandRIM implements CommandExecutor 
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
        if (command.getName().equalsIgnoreCase("rim"))
        {
            if (args.length != 1) 
            {
                if (sender.hasPermission("rim.help") || sender.hasPermission("rim.*"))
                {
                    ArrayList<String> messages = new ArrayList<String>();
                    
                    messages.add("§2---------[§6§l" + Util.NAME + " " + Util.VERSION + " §fby §5" + Util.AUTHOR + "§2]---------");
                    messages.add("§bCommands:");
                    
                    for (Command cmd: Util.COMMANDS) 
                    {
                        messages.add("§6/" + cmd.getName() + " §f-> §o" + Util.COMMAND_PROPERTIES.get(cmd.getName()).get("description").toString());
                        messages.add("  §7Usage: §c" + Util.COMMAND_PROPERTIES.get(cmd.getName()).get("usage").toString());
                        
                        String aliases = null;
                        
                        try 
                        {
                            aliases = Util.COMMAND_PROPERTIES.get(cmd.getName()).get("aliases").toString();
                        }
                        catch (NullPointerException e) 
                        {
                        	
                        }
                        if (aliases != null) 
                        {
                            messages.add("  §7Aliases: §c" + aliases.replaceAll("]", "").replaceAll("\\[", ""));
                        }
                    }
                    sender.sendMessage(Arrays.asList(messages.toArray()).toArray(new String[messages.size()]));
                    return true;
                } 
                else 
                {
                    sender.sendMessage(Util.NO_PERMISSION());
                    return true;
                }
            } 
            else 
            {
                if (args[0].equalsIgnoreCase("reload")) 
                {
                    if (sender.hasPermission("rim.reload") || sender.hasPermission("rim.*")) 
                    {
                        Util.reloadConfig();
                        sender.sendMessage(Util.CONFIG_RELOADED());
                    }
                }
            }
        }
        return true;
    }
}

