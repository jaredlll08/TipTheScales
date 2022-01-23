package com.blamejared.tipthescales;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.network.FMLNetworkConstants;

@Mod(Constants.MOD_ID)
public class TipTheScalesForge {
    
    public TipTheScalesForge() {
        
        ModLoadingContext.get()
                .registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
    }
    
}
