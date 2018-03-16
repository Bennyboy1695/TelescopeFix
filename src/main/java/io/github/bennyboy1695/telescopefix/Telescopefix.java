package io.github.bennyboy1695.telescopefix;

import hellfirepvp.astralsorcery.client.gui.GuiTelescope;
import hellfirepvp.astralsorcery.client.gui.base.GuiTileBase;
import hellfirepvp.astralsorcery.common.CommonProxy;
import hellfirepvp.astralsorcery.common.block.BlockMachine;
import hellfirepvp.astralsorcery.common.tile.TileTelescope;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.event.RegistryEvent;

@Mod(
        modid = Telescopefix.MOD_ID,
        name = Telescopefix.MOD_NAME,
        version = Telescopefix.VERSION, acceptableRemoteVersions = "*"
)
public class Telescopefix {

    public static final String MOD_ID = "telescopefix";
    public static final String MOD_NAME = "Telescopefix";
    public static final String VERSION = "1.0.0";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Telescopefix INSTANCE;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void rightClickTelescope(PlayerInteractEvent.RightClickBlock event) {
        if (doesExist("hellfirepvp.astralsorcery.AstralSorcery")) {
            EntityPlayer player = event.getEntityPlayer();
            Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
            if (block instanceof BlockMachine) {
                TileEntity tile = event.getWorld().getTileEntity(event.getPos());
                if (tile instanceof TileTelescope) {
                    TileTelescope telescope = (TileTelescope) event.getWorld().getTileEntity(event.getPos());
                  
                    try {
                        new GuiTelescope(player, telescope);

                    }catch (Exception e) {
                        //e.printStackTrace();
                        FMLLog.log.warn("It broke :/");
                    }
                }
            }
        }
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public boolean doesExist(String className) {
        try  {
            Class.forName(className);
            return true;
        }  catch (ClassNotFoundException e) {
            return false;
        }
    }
    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */
}
