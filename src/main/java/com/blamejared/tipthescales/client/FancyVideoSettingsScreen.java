package com.blamejared.tipthescales.client;

import net.minecraft.client.*;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.*;

public class FancyVideoSettingsScreen extends VideoSettingsScreen {
    
    public FancyVideoSettingsScreen(Screen p_i1062_1_, GameSettings p_i1062_2_) {
        super(p_i1062_1_, p_i1062_2_);
    }
    
    @Override
    protected void init() {
        super.init();
        this.children.remove(this.optionsRowList);
        this.optionsRowList = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.optionsRowList.addOption(new FullscreenResolutionOption(this.minecraft.getMainWindow()));
        this.optionsRowList.addOption(AbstractOption.BIOME_BLEND_RADIUS);
        for(int i = 0; i < OPTIONS.length; i++) {
            AbstractOption option = OPTIONS[i];
            if(option.getDisplayString().equals(AbstractOption.GUI_SCALE.getDisplayString())) {
                OPTIONS[i] = new SliderStepOption("options.guiScale", 0, Minecraft.getInstance().getMainWindow().calcGuiScale(0, Minecraft.getInstance().getForceUnicodeFont()) + 1, 1, gameSettings1 -> (double) gameSettings1.guiScale, (gameSettings1, aDouble) -> gameSettings1.guiScale = (int) Math.round(aDouble), (gameSettings1, sliderPercentageOption) -> gameSettings1.guiScale + "");
            }
        }
        this.optionsRowList.addOptions(OPTIONS);
        this.children.add(this.optionsRowList);
    }
}
