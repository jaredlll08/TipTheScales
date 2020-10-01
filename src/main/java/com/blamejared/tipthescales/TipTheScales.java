package com.blamejared.tipthescales;

import com.blamejared.tipthescales.proxy.CommonProxy;
import com.blamejared.tipthescales.reference.Reference;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class TipTheScales {
    @Mod.Instance(Reference.MODID)
    public static TipTheScales INSTANCE;

    @SidedProxy(clientSide = "com.blamejared.tipthescales.proxy.ClientProxy", serverSide = "com.blamejared.tipthescales.proxy.CommonProxy")
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        if (!FMLClientHandler.instance().hasOptifine()) {
            PROXY.registerEvents();
        } else {
            FMLLog.bigWarning("OPTIFINE DETECTED! DISABLING TIPTHESCALES!!!");
        }
    }
}
