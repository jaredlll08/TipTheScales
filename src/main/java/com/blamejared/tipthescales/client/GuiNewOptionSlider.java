package com.blamejared.tipthescales.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.*;
import net.minecraft.client.gui.widget.button.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;

public class GuiNewOptionSlider extends AbstractButton {
    
    public static final String[] GUISCALES = new String[] {"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
    
    public int sliderValue;
    public boolean dragging;
    public final GameSettings options;
    private final int minValue;
    private final int maxValue;
    
    public GuiNewOptionSlider(int x, int y, GameSettings optionIn, int minValueIn, int maxValue) {
        super(x, y, 150, 20, "");
        this.sliderValue = 1;
        this.options = optionIn;
        this.minValue = minValueIn;
        this.maxValue = maxValue;
        Minecraft minecraft = Minecraft.getInstance();
        this.sliderValue = MathHelper.clamp(minecraft.gameSettings.guiScale, minValueIn, maxValue);
        this.setMessage(getDisplayString(minecraft));
    }
    
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        if(this.visible) {
            if(this.dragging) {
                final float index = (float) ((mouseX - (this.x + 4)) / (float) (this.width - 10));
                sliderValue = Math.round(Minecraft.getInstance().gameSettings.guiScale > maxValue ? Minecraft.getInstance().gameSettings.guiScale * index : maxValue * index);
                this.sliderValue = MathHelper.clamp(this.sliderValue, this.minValue, this.maxValue);
                this.setMessage(getDisplayString(Minecraft.getInstance()));
                return true;
            }
            
            //            Minecraft.getInstance().getTextureManager().bindTexture(BUTTON_TEXTURES);
            
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            
            int renderX = Math.round(this.x + (sliderValue * ((this.width) / maxValue)));
            renderX = Math.max(this.x, renderX);
            renderX = Math.min(this.x + width - 8, renderX);
            this.blit(renderX, this.y, 0, 66, 4, 20);
            this.blit(renderX + 4, this.y, 196, 66, 4, 20);
        }
        return false;
    }
    
    //    /**
    //     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
    //     */
    //    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
    //        if(this.visible) {
    //            if(this.dragging) {
    //                final float index = (mouseX - (this.x + 4)) / (float) (this.width - 10);
    //                sliderValue = Math.round(mc.gameSettings.guiScale > maxValue ? mc.gameSettings.guiScale * index : maxValue * index);
    //                this.sliderValue = MathHelper.clamp(this.sliderValue, this.minValue, this.maxValue);
    //                this.displayString = getDisplayString(mc);
    //            }
    //
    //            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
    //            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    //
    //            int renderX = Math.round(this.x + (sliderValue * ((this.width) / maxValue)));
    //            renderX = Math.max(this.x, renderX);
    //            renderX = Math.min(this.x + width-8, renderX);
    //            this.drawTexturedModalRect(renderX, this.y, 0, 66, 4, 20);
    //            this.drawTexturedModalRect(renderX + 4, this.y, 196, 66, 4, 20);
    //        }
    //    }
    
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
    
    //    /**
    //     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
    //     * e).
    //     */
    //    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
    //        if(super.mousePressed(mc, mouseX, mouseY)) {
    //            this.dragging = true;
    //            return true;
    //        } else {
    //            return false;
    //        }
    //    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int p_mouseReleased_5_) {
        this.dragging = false;
        Minecraft.getInstance().gameSettings.guiScale = this.sliderValue;
        Minecraft.getInstance().gameSettings.saveOptions();
        return super.mouseReleased(mouseX, mouseY, p_mouseReleased_5_);
    }
    
    @Override
    public void onPress() {
        this.dragging = true;
    }
}