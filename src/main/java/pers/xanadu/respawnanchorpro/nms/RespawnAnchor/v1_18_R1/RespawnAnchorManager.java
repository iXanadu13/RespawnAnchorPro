package pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_18_R1;

import net.minecraft.world.level.dimension.DimensionManager;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import pers.xanadu.respawnanchorpro.nms.RespawnAnchor.I_RespawnAnchorManager;

import java.lang.reflect.Field;

public class RespawnAnchorManager implements I_RespawnAnchorManager {
    public boolean isRespawnAnchorWorks(World world){
        return world.isRespawnAnchorWorks();
    }
    public void setRespawnAnchorWorks(World world, boolean works){
        CraftWorld cw = (CraftWorld) world;
        net.minecraft.world.level.World ew = cw.getHandle();
        DimensionManager dm = ew.q_();
        try{
            Field field = dm.getClass().getDeclaredField("F");
            field.setAccessible(true);
            field.set(dm,works);
        }catch (ReflectiveOperationException e){
            e.printStackTrace();
        }
    }
}
