package pers.xanadu.respawnanchorpro.nms.RespawnAnchor;

import org.bukkit.World;

public interface I_RespawnAnchorManager {
    boolean isRespawnAnchorWorks(World world);
    void setRespawnAnchorWorks(World world, boolean works);
}
