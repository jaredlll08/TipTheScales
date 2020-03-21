package com.blamejared.tipthescales.client;

import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.widget.AbstractSlider;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StepSlider extends AbstractSlider {
    
    private final SliderStepOption option;
    
    public StepSlider(GameSettings settings, int xIn, int yIn, int widthIn, int heightIn, SliderStepOption optionIn) {
        super(settings, xIn, yIn, widthIn, heightIn, (double) ((float) optionIn.normalizeValue(optionIn.get(settings))));
        this.option = optionIn;
        this.updateMessage();
    }
    
    protected void applyValue() {
        this.option.set(this.options, this.option.denormalizeValue(this.value));
        this.options.saveOptions();
    }
    
    @Override
    public void onClick(double p_onClick_1_, double p_onClick_3_) {
        super.onClick(p_onClick_1_, p_onClick_3_);
    }
    
    @Override
    public void onRelease(double p_onRelease_1_, double p_onRelease_3_) {
//        super.onRelease(p_onRelease_1_, p_onRelease_3_);
        System.out.println(this.value);
        super.onClick(p_onRelease_1_, p_onRelease_3_);
        System.out.println(this.value);
    }
    
    @Override
    protected void onDrag(double p_onDrag_1_, double p_onDrag_3_, double p_onDrag_5_, double p_onDrag_7_) {
//        super.onClick(p_onDrag_1_, p_onDrag_3_);
//        System.out.println(options.guiScale);
    }
    
    protected void updateMessage() {
        this.setMessage(this.option.getText(this.options));
    }
    
}