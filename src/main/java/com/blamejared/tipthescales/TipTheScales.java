package com.blamejared.tipthescales;

import com.blamejared.tipthescales.proxy.CommonProxy;
import com.blamejared.tipthescales.reference.Reference;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class TipTheScales {
    
    
    @Mod.Instance(Reference.MODID)
    public static TipTheScales INSTANCE;
    
    @SidedProxy(clientSide = "com.blamejared.tipthescales.proxy.ClientProxy", serverSide = "com.blamejared.tipthescales.proxy.CommonProxy")
    public static CommonProxy PROXY;
    
    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        PROXY.registerEvents();
    }
    
}
