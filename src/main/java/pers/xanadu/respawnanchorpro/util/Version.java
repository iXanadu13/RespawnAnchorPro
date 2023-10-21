package pers.xanadu.respawnanchorpro.util;

import pers.xanadu.respawnanchorpro.config.Lang;
import pers.xanadu.respawnanchorpro.nms.RespawnAnchor.I_RespawnAnchorManager;

import static org.bukkit.Bukkit.getServer;

public class Version {
    public static final String lang = "1.0.0";
    public static final String config = "1.0.0";
    public static int mcMainVersion;
    public static int mcPatchVersion;
    private static String version = "no version found";
    public static void init(){
        try {
            version = getServer().getClass().getPackage().getName().split("\\.")[3];
            String[] mc_version = getServer().getBukkitVersion().split("-")[0].split("\\.");
            mcMainVersion = Integer.parseInt(mc_version[1]);
            if(mc_version.length == 2) mcPatchVersion = 0;
            else mcPatchVersion = Integer.parseInt(mc_version[2]);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Lang.warn("Failed to get nms-version!");
        }
        Lang.info("Found version: " + version);
    }
    public static I_RespawnAnchorManager getRespawnAnchorManager(){
        switch(version){
            case "v1_16_R1" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_16_R1.RespawnAnchorManager();
            case "v1_16_R2" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_16_R2.RespawnAnchorManager();
            case "v1_16_R3" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_16_R3.RespawnAnchorManager();
            case "v1_17_R1" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_17_R1.RespawnAnchorManager();
            case "v1_18_R1" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_18_R1.RespawnAnchorManager();
            case "v1_18_R2" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_18_R2.RespawnAnchorManager();
            case "v1_19_R1" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_19_R1.RespawnAnchorManager();
            case "v1_19_R2" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_19_R2.RespawnAnchorManager();
            case "v1_19_R3" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_19_R3.RespawnAnchorManager();
            case "v1_20_R1" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_20_R1.RespawnAnchorManager();
            case "v1_20_R2" : return new pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_20_R2.RespawnAnchorManager();
            default: return null;
        }
    }

    public static String getVersion() {
        return version;
    }

}
