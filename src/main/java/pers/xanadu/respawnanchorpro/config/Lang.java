package pers.xanadu.respawnanchorpro.config;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class Lang {
    public static String version;
    public static String plugin_prefix;
    public static String plugin_version_below_1_16;
    public static String plugin_wrong_file_version;
    public static String command_no_permission;
    public static String command_reload_config;
    public static String player_no_permission;
    public static String world_respawn_anchor_rule_changed;

    public static List<String> CommandTips;
    public static void reload(FileConfiguration file){
        Field[] fields = Lang.class.getFields();
        for(Field field : fields){
            try{
                field.set(null,"");
            }catch (Exception ignored){}
        }
        Iterator<String> it = file.getKeys(true).iterator();
        while (it.hasNext()){
            String str = it.next();
            try{
                if(file.isConfigurationSection(str)) continue;
                Lang.class.getField(str.replace(".","_")).set(null, file.get(str));
            }catch (Exception e){
                error("Language loading error! Key: "+str);
            }
        }
//        while (it.hasNext()){
//            String str = it.next();
//            try{
//                if(!file.isString(str)) continue;
//                Lang.class.getField(str.replace(".","_")).set(null,file.getString(str));
//            }catch (Exception e){
//                error("Language loading error! Key: "+str);
//            }
//        }
    }
    public static void sendMessage(String str) {
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage(Lang.plugin_prefix + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                sendMessage(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage(Lang.plugin_prefix + str);
    }
    public static void info(String str){
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage("§a[RespawnAnchorPro] " + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                info(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage("§a[RespawnAnchorPro] "+str);
    }
    public static void warn(String str){
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage("§e[RespawnAnchorPro] " + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                warn(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage("§e[RespawnAnchorPro] "+str);
    }
    public static void error(String str){
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage("§c[RespawnAnchorPro] " + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                error(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage("§c[RespawnAnchorPro] "+str);
    }
    public static void sendFeedback(CommandSender sender, String str){
        if(str == null) {
            sender.sendMessage(Lang.plugin_prefix + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                sender.sendMessage(Lang.plugin_prefix + s);
            }
        }
        else sender.sendMessage(Lang.plugin_prefix + str);
    }
}
