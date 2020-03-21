package com.blamejared.tipthescales.client;

import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.settings.*;
import net.minecraft.util.math.MathHelper;

import java.util.function.*;

public class SliderStepOption extends AbstractOption {
    
    protected final float stepSize;
    protected final double minValue;
    protected double maxValue;
    private final Function<GameSettings, Double> getter;
    private final BiConsumer<GameSettings, Double> setter;
    private final BiFunction<GameSettings, SliderStepOption, String> getDisplayStringFunc;
    
    public SliderStepOption(String translationKey, double minValueIn, double maxValueIn, float stepSizeIn, Function<GameSettings, Double> getter, BiConsumer<GameSettings, Double> setter, BiFunction<GameSettings, SliderStepOption, String> getDisplayString) {
        super(translationKey);
        this.minValue = minValueIn;
        this.maxValue = maxValueIn;
        this.stepSize = stepSizeIn;
        this.getter = getter;
        this.setter = setter;
        this.getDisplayStringFunc = getDisplayString;
    }
    
    public Widget createWidget(GameSettings options, int xIn, int yIn, int widthIn) {
        return new GuiNewOptionSlider( xIn, yIn, options, (int)getMinValue(), (int)getMaxValue());
    }
    
    public double normalizeValue(double value) {
        return MathHelper.clamp((this.snapToStepClamp(value) - this.minValue) / (this.maxValue - this.minValue), 0.0D, 1.0D);
    }
    
    public double denormalizeValue(double value) {
        return this.snapToStepClamp(MathHelper.lerp(MathHelper.clamp(value, 0.0D, 1.0D), this.minValue, this.maxValue));
    }
    
    private double snapToStepClamp(double valueIn) {
        if(this.stepSize > 0.0F) {
            valueIn = this.stepSize * (float) Math.round(valueIn / (double) this.stepSize);
        }
    
        return MathHelper.clamp(valueIn, this.minValue, this.maxValue);
    }
    
    public double getMinValue() {
        return this.minValue;
    }
    
    public double getMaxValue() {
        return this.maxValue;
    }
    
    public void setMaxValue(float valueIn) {
        this.maxValue = (double) valueIn;
    }
    
    public void set(GameSettings options, double valueIn) {
        this.setter.accept(options, valueIn);
    }
    
    public double get(GameSettings options) {
        return this.getter.apply(options);
    }
    
    public String getText(GameSettings options) {
        return this.getDisplayStringFunc.apply(options, this);
    }
}
