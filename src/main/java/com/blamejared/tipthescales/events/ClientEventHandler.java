package com.blamejared.tipthescales.events;

import com.blamejared.tipthescales.client.GuiNewOptionsRowList;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.lang.reflect.Field;

public class ClientEventHandler {
    
    //    @SubscribeEvent
    //    public void onGuiOpen(GuiOpenEvent event) {
    //        try {
    //            if(event.getGui() instanceof GuiVideoSettings && !(event.getGui() instanceof GuiNewVideoSettings)) {
    //                GuiVideoSettings gui = (GuiVideoSettings) event.getGui();
    //                Field field = gui.getClass().getDeclaredField("parentScreen");
    //                field.setAccessible(true);
    //                event.setGui(new GuiNewVideoSettings((GuiScreen) field.get(gui), Minecraft.getInstance().gameSettings));
    //            }
    //        } catch(Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
    
    private static final GameSettings.Options[] VIDEO_OPTIONS = new GameSettings.Options[]{GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.ENABLE_VSYNC, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.ATTACK_INDICATOR, GameSettings.Options.GAMMA, GameSettings.Options.RENDER_CLOUDS, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.PARTICLES, GameSettings.Options.MIPMAP_LEVELS, GameSettings.Options.USE_VBO, GameSettings.Options.ENTITY_SHADOWS, GameSettings.Options.BIOME_BLEND_RADIUS};
    
    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent event) {
        try {
            if(event.getGui() instanceof GuiVideoSettings) {
                GuiVideoSettings gui = (GuiVideoSettings) event.getGui();
                Field field = gui.getClass().getDeclaredField("optionsRowList");
                field.setAccessible(true);
                GuiOptionsRowList optionsRowList = (GuiOptionsRowList) field.get(gui);
                Minecraft mc = Minecraft.getInstance();
                int width = mc.mainWindow.getScaledWidth();
                int height = mc.mainWindow.getScaledHeight();
                if(OpenGlHelper.vboSupported) {
                    optionsRowList = new GuiNewOptionsRowList(mc, width, height, 32, height - 32, 25, VIDEO_OPTIONS);
                } else {
                    GameSettings.Options[] agamesettings$options = new GameSettings.Options[VIDEO_OPTIONS.length - 1];
                    int i = 0;
                    
                    for(GameSettings.Options gamesettings$options : VIDEO_OPTIONS) {
                        if(gamesettings$options == GameSettings.Options.USE_VBO) {
                            break;
                        }
                        
                        agamesettings$options[i] = gamesettings$options;
                        ++i;
                    }
                    
                    optionsRowList = new GuiNewOptionsRowList(mc, width, height, 32, height - 32, 25, agamesettings$options);
                }
                field.set(gui, optionsRowList);
            }
            //TODO this breaks it all, mouse no longer works
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
