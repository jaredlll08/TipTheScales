package com.blamejared.tipthescales.client;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

@SideOnly(Side.CLIENT)
public class GuiNewVideoSettings extends GuiVideoSettings {
    
    public GuiNewVideoSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn);
    }
    
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        this.screenTitle = I18n.format("options.videoTitle");
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height - 27, I18n.format("gui.done")));
        
        if(OpenGlHelper.field_153197_d) {
            this.optionsRowList = new GuiNewOptionsRowList(this.mc, this.width, this.height, 32, this.height - 32, 25, videoOptions);
        } else {
            GameSettings.Options[] agamesettings$options = new GameSettings.Options[videoOptions.length - 1];
            int i = 0;
            
            for(GameSettings.Options gamesettings$options : videoOptions) {
                if(gamesettings$options == GameSettings.Options.ADVANCED_OPENGL) {
                    break;
                }
                
                agamesettings$options[i] = gamesettings$options;
                ++i;
            }
            
            this.optionsRowList = new GuiNewOptionsRowList(this.mc, this.width, this.height, 32, this.height - 32, 25, agamesettings$options);
        }
    }
}