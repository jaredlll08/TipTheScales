package com.blamejared.tipthescales;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class TipTheScalesForge {
    
    public TipTheScalesForge(FMLJavaModLoadingContext context) {
        
        context.registerDisplayTest(IExtensionPoint.DisplayTest.IGNORE_ALL_VERSION);
    }
    
}
