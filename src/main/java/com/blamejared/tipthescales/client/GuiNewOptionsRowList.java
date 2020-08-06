package com.blamejared.tipthescales.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.relauncher.*;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiNewOptionsRowList extends GuiOptionsRowList {
    
    public GuiNewOptionsRowList(Minecraft mcIn, int p_i45015_2_, int p_i45015_3_, int p_i45015_4_, int p_i45015_5_, int p_i45015_6_, GameSettings.Options... p_i45015_7_) {
        super(mcIn, p_i45015_2_, p_i45015_3_, p_i45015_4_, p_i45015_5_, p_i45015_6_, p_i45015_7_);
    }
    
    @Override
    public GuiButton createButton(Minecraft mcIn, int p_148182_2_, int p_148182_3_, GameSettings.Options options) {
        if(options == GameSettings.Options.GUI_SCALE) {
            int j = 1000;
            int max = 1;
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            
            while(max < j && width / (max + 1) >= 160 && height / (max + 1) >= 120) {
                ++max;
            }
            
            if(mc.isUnicode() && max % 2 != 0 && max != 1) {
                --max;
            }
            
            return new GuiNewOptionSlider(options.getOrdinal(), p_148182_2_, p_148182_3_, options, 0, max - 1);
        } else {
            return super.createButton(mc, p_148182_2_, p_148182_3_, options);
        }
    }
}