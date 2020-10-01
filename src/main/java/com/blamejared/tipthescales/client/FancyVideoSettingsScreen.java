package com.blamejared.tipthescales.client;

import net.minecraft.client.*;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.*;

public class FancyVideoSettingsScreen extends VideoSettingsScreen {
    
    public FancyVideoSettingsScreen(Screen screen, GameSettings settings) {
        super(screen, settings);
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
            if(option.translatedBaseMessage.getString().equals(AbstractOption.GUI_SCALE.translatedBaseMessage.getString()) && !(option instanceof SliderPercentageOption)) {
                OPTIONS[i] = new SliderPercentageOption("options.guiScale", 0, Minecraft.getInstance().getMainWindow().calcGuiScale(0, Minecraft.getInstance().getForceUnicodeFont()), 1, gameSettings1 -> (double) gameSettings1.guiScale, (gameSettings1, aDouble) -> {
                    gameSettings1.guiScale = (int) Math.round(aDouble);
                }, (gameSettings1, sliderPercentageOption) -> sliderPercentageOption.getGenericValueComponent(gameSettings1.guiScale == 0 ? new TranslationTextComponent("options.guiScale.auto") : new StringTextComponent(gameSettings1.guiScale + "")));
            }
        }
        this.optionsRowList.addOptions(OPTIONS);
        this.children.add(this.optionsRowList);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonId) {
        guiScale = this.gameSettings.guiScale;
        return superMouseClicked(mouseX, mouseY, buttonId);
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int buttonId) {
        if(this.gameSettings.guiScale != guiScale) {
            this.minecraft.updateWindowSize();
        }
        guiScale = this.gameSettings.guiScale;
        if(superMouseReleased(mouseX, mouseY, buttonId)) {
            return true;
        } else {
            return this.optionsRowList.mouseReleased(mouseX, mouseY, buttonId);
        }
    }
    
    public boolean superMouseClicked(double mouseX, double mouseY, int buttonId) {
        for(IGuiEventListener iguieventlistener : this.getEventListeners()) {
            if(iguieventlistener.mouseClicked(mouseX, mouseY, buttonId)) {
                this.setListener(iguieventlistener);
                if(buttonId == 0) {
                    this.setDragging(true);
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    public boolean superMouseReleased(double mouseX, double mouseY, int buttonId) {
        this.setDragging(false);
        return this.getEventListenerForPos(mouseX, mouseY).filter(listener -> listener.mouseReleased(mouseX, mouseY, buttonId)).isPresent();
    }
}
