package com.blamejared.tipthescales;


import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class TipTheScalesNeoForge {
    
    public TipTheScalesNeoForge() {
        
        ModLoadingContext.get()
                .registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (remote, isServer) -> true));
    }
    
}
