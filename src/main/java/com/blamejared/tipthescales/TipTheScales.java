package com.blamejared.tipthescales;

import com.blamejared.tipthescales.events.ClientEventHandler;
import com.blamejared.tipthescales.reference.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.*;
import net.minecraftforge.userdev.FMLUserdevClientLaunchProvider;
import org.apache.logging.log4j.*;

@Mod(Reference.MODID)
public class TipTheScales {
    
    
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    
    public TipTheScales() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        
    }
    
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
    
}
