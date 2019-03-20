package com.blamejared.tipthescales.client;


import net.minecraft.client.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class GuiNewOptionSlider extends GuiButton {
    
    public static final String[] GUISCALES = new String[]{"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
    
    public int sliderValue;
    public boolean dragging;
    public final GameSettings.Options options;
    private final int minValue;
    private final int maxValue;
    
    public GuiNewOptionSlider(int buttonId, int x, int y, GameSettings.Options optionIn, int minValueIn, int maxValue) {
        super(buttonId, x, y, 150, 20, "");
        this.sliderValue = 1;
        this.options = optionIn;
        this.minValue = minValueIn;
        this.maxValue = maxValue;
        Minecraft minecraft = Minecraft.getInstance();
        this.sliderValue = MathHelper.clamp(minecraft.gameSettings.guiScale, minValueIn, maxValue);
        this.displayString = getDisplayString(minecraft);
    }
    
    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver) {
        return 0;
    }
    
    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
        if(this.visible) {
            if(this.dragging) {
                final float index = (mouseX - (this.x + 4)) / (float) (this.width - 10);
                sliderValue = Math.round(mc.gameSettings.guiScale > maxValue ? mc.gameSettings.guiScale * index : maxValue * index);
                this.sliderValue = MathHelper.clamp(this.sliderValue, this.minValue, this.maxValue);
                this.displayString = getDisplayString(mc);
            }
            
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            
            int renderX = Math.round(this.x + (sliderValue * ((this.width) / maxValue)));
            renderX = Math.max(this.x, renderX);
            renderX = Math.min(this.x + width - 8, renderX);
            this.drawTexturedModalRect(renderX, this.y, 0, 66, 4, 20);
            this.drawTexturedModalRect(renderX + 4, this.y, 196, 66, 4, 20);
        }
    }
    
    public String getDisplayString(Minecraft mc) {
        String ret = I18n.format("options.guiScale") + ": ";
        if(sliderValue < GUISCALES.length) {
            return ret + getTranslation(GUISCALES, sliderValue) + String.format(" (%s)", sliderValue);
        } else {
            return ret + sliderValue;
        }
    }
    
    /**
     * Returns the translation of the given index in the given String array. If the index is smaller than 0 or greater
     * than/equal to the length of the String array, it is changed to 0.
     */
    public String getTranslation(String[] strArray, int index) {
        if(index < 0 || index >= strArray.length) {
            index = 0;
        }
        
        return I18n.format(strArray[index]);
    }
    
    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        this.dragging = true;
    }
    
    @Override
    public void onRelease(double mouseX, double mouseY) {
        super.onRelease(mouseX, mouseY);
        Minecraft.getInstance().gameSettings.guiScale = this.sliderValue;
    }
}