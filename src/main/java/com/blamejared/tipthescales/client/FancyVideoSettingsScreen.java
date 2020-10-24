package com.blamejared.tipthescales.client;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.*;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.renderer.GPUWarning;
import net.minecraft.client.settings.GraphicsFanciness;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.*;

import java.util.List;

public class FancyVideoSettingsScreen extends VideoSettingsScreen {
    
    private final GPUWarning gpuWarning;
    
    public FancyVideoSettingsScreen(Screen screen, GameSettings settings) {
        super(screen, settings);
        this.gpuWarning = Minecraft.getInstance().getGPUWarning();
        this.gpuWarning.func_241702_i_();
        if(settings.graphicFanciness == GraphicsFanciness.FABULOUS) {
            this.gpuWarning.func_241698_e_();
        }
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
        if(superMouseClicked(mouseX, mouseY, buttonId)) {
            if(this.gpuWarning.func_241700_g_()) {
                List<ITextProperties> list = Lists.newArrayList(field_241599_p_, field_241603_t_);
                String s = this.gpuWarning.func_241703_j_();
                if(s != null) {
                    list.add(field_241603_t_);
                    list.add((new TranslationTextComponent("options.graphics.warning.renderer", s)).mergeStyle(TextFormatting.GRAY));
                }
                
                String s1 = this.gpuWarning.func_241705_l_();
                if(s1 != null) {
                    list.add(field_241603_t_);
                    list.add((new TranslationTextComponent("options.graphics.warning.vendor", s1)).mergeStyle(TextFormatting.GRAY));
                }
                
                String s2 = this.gpuWarning.func_241704_k_();
                if(s2 != null) {
                    list.add(field_241603_t_);
                    list.add((new TranslationTextComponent("options.graphics.warning.version", s2)).mergeStyle(TextFormatting.GRAY));
                }
                
                this.minecraft.displayGuiScreen(new GPUWarningScreen(field_241600_q_, list, ImmutableList.of(new GPUWarningScreen.Option(field_241601_r_, (p_241606_1_) -> {
                    this.gameSettings.graphicFanciness = GraphicsFanciness.FABULOUS;
                    Minecraft.getInstance().worldRenderer.loadRenderers();
                    this.gpuWarning.func_241698_e_();
                    this.minecraft.displayGuiScreen(this);
                }), new GPUWarningScreen.Option(field_241602_s_, (p_241605_1_) -> {
                    this.gpuWarning.func_241699_f_();
                    this.minecraft.displayGuiScreen(this);
                }))));
            }
            
            return true;
        } else {
            return false;
        }
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
