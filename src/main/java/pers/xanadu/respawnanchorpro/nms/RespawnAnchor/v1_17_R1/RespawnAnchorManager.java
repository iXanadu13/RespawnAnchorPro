package pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_17_R1;

import net.minecraft.world.level.dimension.DimensionManager;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import pers.xanadu.respawnanchorpro.nms.RespawnAnchor.I_RespawnAnchorManager;

import java.lang.reflect.Field;

public class RespawnAnchorManager implements I_RespawnAnchorManager {
    public boolean isRespawnAnchorWorks(World world){
        CraftWorld cw = (CraftWorld) world;
        net.minecraft.world.level.World ew = cw.getHandle();
        DimensionManager dm = ew.getDimensionManager();
        return dm.isRespawnAnchorWorks();
    }

    public void setRespawnAnchorWorks(World world, boolean works){
        CraftWorld cw = (CraftWorld) world;
        net.minecraft.world.level.World ew = cw.getHandle();
        DimensionManager dm = ew.getDimensionManager();
        try{
            Field field = dm.getClass().getDeclaredField("D");
            field.setAccessible(true);
            field.set(dm,works);
        }catch (ReflectiveOperationException e){
            e.printStackTrace();
        }
    }
}
