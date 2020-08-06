package com.blamejared.tipthescales;

import com.blamejared.tipthescales.events.ClientEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("tipthescales")
public class TipTheScales {
    
    public TipTheScales() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        
        
        try {
            
            Class.forName("net.optifine.Config");
            System.out.println("OPTIFINE DETECTED! DISABLING TIPTHESCALES!!!");
            return;
        } catch(final Exception e) {
            // no-op
        }
        
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
    
}
