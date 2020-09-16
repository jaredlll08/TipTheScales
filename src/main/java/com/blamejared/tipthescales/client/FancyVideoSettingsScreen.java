package com.blamejared.tipthescales.client;

import net.minecraft.client.*;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.*;

import java.util.ArrayList;

public class FancyVideoSettingsScreen extends VideoSettingsScreen {
    
    public FancyVideoSettingsScreen(Screen p_i1062_1_, GameSettings p_i1062_2_) {
        super(p_i1062_1_, p_i1062_2_);
    }
    
    public static int guiScale;
    
    @Override
    protected void init() {
        super.init();
        this.optionsRowList.getEventListeners().clear();
        this.optionsRowList.addOption(new FullscreenResolutionOption(this.minecraft.getMainWindow()));
        this.optionsRowList.addOption(AbstractOption.BIOME_BLEND_RADIUS);
        for(int i = 0; i < OPTIONS.length; i++) {
            AbstractOption option = OPTIONS[i];
            if(option.field_243217_ac.getString().equals(AbstractOption.GUI_SCALE.field_243217_ac.getString()) && !(option instanceof SliderPercentageOption)) {
                OPTIONS[i] = new SliderPercentageOption("options.guiScale", 0, Minecraft.getInstance().getMainWindow().calcGuiScale(0, Minecraft.getInstance().getForceUnicodeFont()), 1, gameSettings1 -> (double) gameSettings1.guiScale, (gameSettings1, aDouble) -> {
                    gameSettings1.guiScale = (int) Math.round(aDouble);
                }, (gameSettings1, sliderPercentageOption) -> sliderPercentageOption.func_243222_a(gameSettings1.guiScale == 0 ? new TranslationTextComponent("options.guiScale.auto") : new StringTextComponent(gameSettings1.guiScale + "")));
            }
        }
        this.optionsRowList.addOptions(OPTIONS);
        this.children.add(this.optionsRowList);
    }
    
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        guiScale = this.gameSettings.guiScale;
        return superMouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }
    
    @Override
    public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
        if(this.gameSettings.guiScale != guiScale) {
            this.minecraft.updateWindowSize();
        }
        guiScale = this.gameSettings.guiScale;
        if(superMouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_)) {
            return true;
        } else {
            return this.optionsRowList.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
        }
    }
    
    public boolean superMouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        for(IGuiEventListener iguieventlistener : this.getEventListeners()) {
            if(iguieventlistener.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
                this.setListener(iguieventlistener);
                if(p_mouseClicked_5_ == 0) {
                    this.setDragging(true);
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    public boolean superMouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
        this.setDragging(false);
        return this.getEventListenerForPos(p_mouseReleased_1_, p_mouseReleased_3_).filter((p_212931_5_) -> p_212931_5_.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_)).isPresent();
    }
}
