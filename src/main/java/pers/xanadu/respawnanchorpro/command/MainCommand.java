package pers.xanadu.respawnanchorpro.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pers.xanadu.respawnanchorpro.config.Lang;

import static pers.xanadu.respawnanchorpro.RespawnAnchorPro.reloadAll;
import static pers.xanadu.respawnanchorpro.config.Lang.*;

public class MainCommand implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String name, String[] args) {
		if(args.length == 0){
			sendCommandTips(sender);
			return false;
		}
		switch (args[0].toLowerCase()){
			case "reload" : {
				if(!sender.hasPermission("respawnanchorpro.admin")){
					sendFeedback(sender, command_no_permission);
					return false;
				}
				if(args.length != 1){
					sendCommandTips(sender);
					return false;
				}
				reloadAll();
				sendFeedback(sender, Lang.command_reload_config);
				return true;
			}
		}
		sendCommandTips(sender);
		return false;
	}
	private static void sendCommandTips(CommandSender sender){
		for(String str : CommandTips){
			sender.sendMessage(str);
		}
	}
}
