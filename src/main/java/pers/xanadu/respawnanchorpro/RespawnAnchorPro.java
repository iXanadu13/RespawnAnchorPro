package pers.xanadu.respawnanchorpro;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pers.xanadu.respawnanchorpro.command.MainCommand;
import pers.xanadu.respawnanchorpro.command.TabCompleter;
import pers.xanadu.respawnanchorpro.config.Config;
import pers.xanadu.respawnanchorpro.config.Lang;
import pers.xanadu.respawnanchorpro.listener.RespawnAnchorListener;
import pers.xanadu.respawnanchorpro.metrics.Metrics;
import pers.xanadu.respawnanchorpro.nms.RespawnAnchor.I_RespawnAnchorManager;
import pers.xanadu.respawnanchorpro.util.Version;

import java.io.File;
import java.util.Objects;

import static pers.xanadu.respawnanchorpro.config.Lang.*;

public final class RespawnAnchorPro extends JavaPlugin{
	private static RespawnAnchorPro instance;
	public static Plugin plugin;
	public static PluginManager pm;
	public static File langF;
	public static FileConfiguration lang;
	private I_RespawnAnchorManager i_respawnAnchorManager;
	private static boolean disable = false;
	@Override
	public void onLoad(){
		plugin = this;
		instance = this;
		pm = Bukkit.getPluginManager();
	}
	@Override
	public void onEnable() {
		info("Enabling plugin...");
		info("Author: Xanadu13");
		Version.init();
		if(Version.mcMainVersion>=16) {
			i_respawnAnchorManager = Version.getRespawnAnchorManager();
			registerEvents();
			registerCommands();
		}
		else disable = true;
		new Metrics(this,20078);
		reloadAll();
		if(!Version.lang.equals(Lang.version)){
			warn(Lang.plugin_wrong_file_version.replace("{file_name}",Config.lang + ".yml"));
		}
		if(!Version.config.equals(Config.version)){
			warn(Lang.plugin_wrong_file_version.replace("{file_name}","config.yml"));
		}
	}
	public static void reloadAll(){
		getInstance().loadFiles();
		if(disable) {
			warn(Lang.plugin_version_below_1_16);
			return;
		}
		Bukkit.getWorlds().forEach(world -> {
			boolean tag = Config.enable_world_list.contains(world.getName());
			if(tag!=getInstance().getRespawnAnchorManager().isRespawnAnchorWorks(world)){
				getInstance().getRespawnAnchorManager().setRespawnAnchorWorks(world,tag);
				Lang.info(Lang.world_respawn_anchor_rule_changed.replaceFirst("\\{world}",world.getName()).replaceFirst("\\{tag}",String.valueOf(tag)));
			}
		});
	}
	public static RespawnAnchorPro getInstance() {
		return instance;
	}
	public I_RespawnAnchorManager getRespawnAnchorManager(){
		return i_respawnAnchorManager;
	}
	private void loadFiles(){
		saveDefaultConfig();
		reloadConfig();
		Config.reload(getConfig());
		if(Config.lang.equals("")) {
			error("Key \"lang\" in config is missing, please check your config.yml.");
			Config.lang = "English";
		}
		String langPath = "lang/" + Config.lang + ".yml";
		if (!new File(getDataFolder(), langPath).exists()) {
			this.saveResource(langPath, false);
		}
		langF = new File(getDataFolder(), langPath);
		lang = YamlConfiguration.loadConfiguration(langF);
		info("Language: §6" + Config.lang);
		Lang.reload(lang);
	}
	private void registerEvents(){
		pm.registerEvents(new RespawnAnchorListener(),this);
	}
	private void registerCommands(){
		Objects.requireNonNull(getCommand("respawnanchorpro")).setExecutor(new MainCommand());
		Objects.requireNonNull(getCommand("respawnanchorpro")).setTabCompleter(new TabCompleter());
	}
}
