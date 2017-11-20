package com.blamejared.tipthescales.events;

import com.blamejared.tipthescales.client.GuiNewVideoSettings;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {
    
    public ClientEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        GuiScreen gui = event.getGui();
        if(gui instanceof GuiVideoSettings && !(gui instanceof GuiNewVideoSettings)){
            event.setGui(new GuiNewVideoSettings(((GuiVideoSettings) gui).parentGuiScreen, ((GuiVideoSettings) gui).guiGameSettings));
        }
    }
}
