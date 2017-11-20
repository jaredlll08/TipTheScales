package com.blamejared.tipthescales.events;

import com.blamejared.tipthescales.client.GuiNewVideoSettings;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientEventHandler {
    
    public ClientEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }
    
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        GuiScreen gui = event.gui;
        if(gui instanceof GuiVideoSettings && !(gui instanceof GuiNewVideoSettings)){
            event.gui = new GuiNewVideoSettings(((GuiVideoSettings) gui).parentGuiScreen, ((GuiVideoSettings) gui).guiGameSettings);
        }
    }
}
