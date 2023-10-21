package pers.xanadu.respawnanchorpro.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Config {
    public static String version;
    public static String lang;
    public static String permission;
    public static List<String> enable_world_list;

    public static void reload(FileConfiguration file){
        Field[] fields = Config.class.getFields();
        for(Field field : fields){
            Type type = field.getType();
            if(type.equals(String.class)){
                try{
                    field.set(null,"");
                }catch (Exception ignored){}
            }
            else if(type.equals(java.util.List.class)){
                try{
                    field.set(null, Collections.emptyList());
                }catch (Exception ignored){}
            }
        }
        Iterator<String> it = file.getKeys(true).iterator();
        while (it.hasNext()){
            String str = it.next();
            try{
                if(file.isConfigurationSection(str)) continue;
                Config.class.getField(str.replace(".","_")).set(null, file.get(str));
            }catch (Exception e){
                Lang.error("Config loading error! Key: "+str);
            }
        }

    }
}
