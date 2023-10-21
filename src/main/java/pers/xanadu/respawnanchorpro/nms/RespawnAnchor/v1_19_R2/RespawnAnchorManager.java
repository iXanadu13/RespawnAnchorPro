package pers.xanadu.respawnanchorpro.nms.RespawnAnchor.v1_19_R2;

import net.minecraft.core.Holder;
import net.minecraft.world.level.dimension.DimensionManager;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import pers.xanadu.respawnanchorpro.nms.RespawnAnchor.I_RespawnAnchorManager;

import java.lang.reflect.Field;

public class RespawnAnchorManager implements I_RespawnAnchorManager {
    public boolean isRespawnAnchorWorks(World world){
        return world.isRespawnAnchorWorks();
    }
    public void setRespawnAnchorWorks(World world, boolean works){
        CraftWorld cw = (CraftWorld) world;
        net.minecraft.world.level.World ew = cw.getHandle();
        DimensionManager dm = ew.r_();
        DimensionManager new_dm = new DimensionManager(
                dm.f(), dm.g(), dm.h(), dm.i(), dm.j(),
                dm.k(), dm.l(), works, dm.n(), dm.o(),
                dm.p(), dm.q(), dm.r(), dm.s(), dm.t()
        );
        Holder<DimensionManager> holder = new Holder.a<>(new_dm);
        try{
            Field field = ew.getClass().getSuperclass().getDeclaredField("E");
            field.setAccessible(true);
            field.set(ew,holder);
        }catch (ReflectiveOperationException e){
            e.printStackTrace();
        }
    }
}
