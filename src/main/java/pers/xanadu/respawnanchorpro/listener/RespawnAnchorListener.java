package pers.xanadu.respawnanchorpro.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pers.xanadu.respawnanchorpro.RespawnAnchorPro;
import pers.xanadu.respawnanchorpro.config.Config;
import pers.xanadu.respawnanchorpro.config.Lang;

public class RespawnAnchorListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void OnRespawnAnchorUse(final PlayerInteractEvent e){
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = e.getClickedBlock();
        if(block == null || block.getType() != Material.RESPAWN_ANCHOR) return;
        RespawnAnchor anchor = (RespawnAnchor) block.getBlockData();
        if(e.getMaterial()==Material.GLOWSTONE && anchor.getCharges()<4) return;
        if(anchor.getCharges()==0) return;
        Player p = e.getPlayer();
        if(!RespawnAnchorPro.getInstance().getRespawnAnchorManager().isRespawnAnchorWorks(p.getWorld())) return;
        if(!Config.permission.equals("") && !p.hasPermission(Config.permission)){
            p.sendMessage(Lang.player_no_permission);
            e.setCancelled(true);
        }
    }
}
