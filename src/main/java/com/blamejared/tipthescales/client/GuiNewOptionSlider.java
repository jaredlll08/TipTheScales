package com.blamejared.tipthescales.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiNewOptionSlider extends GuiButton {
    public static final String[] GUISCALES = new String[]{
        "options.guiScale.auto",
        "options.guiScale.small",
        "options.guiScale.normal",
        "options.guiScale.large"
    };
    public final GameSettings.Options options;
    private final int minValue;
    private final int maxValue;
    public int sliderValue;
    public boolean dragging;

    public GuiNewOptionSlider(int buttonId, int x, int y, GameSettings.Options optionIn, int minValueIn, int maxValue) {
        super(buttonId, x, y, 150, 20, "");
        this.sliderValue = 1;
        this.options = optionIn;
        this.minValue = minValueIn;
        this.maxValue = maxValue;
        Minecraft minecraft = Minecraft.getMinecraft();
        this.sliderValue = MathHelper.clamp_int(minecraft.gameSettings.guiScale, minValueIn, maxValue);
        this.displayString = getDisplayString(minecraft);
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    public int getHoverState(boolean mouseOver) {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            if (this.dragging) {
                final float index = (mouseX - (this.xPosition + 4)) / (float) (this.width - 10);
                sliderValue = Math.round(mc.gameSettings.guiScale > maxValue ? mc.gameSettings.guiScale * index : maxValue * index);
                this.sliderValue = MathHelper.clamp_int(this.sliderValue, this.minValue, this.maxValue);
                this.displayString = getDisplayString(mc);
            }

            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1, 1, 1, 1);

            int renderX = Math.round(this.xPosition + (sliderValue * ((this.width) / maxValue)));
            renderX = Math.max(this.xPosition, renderX);
            renderX = Math.min(this.xPosition + width - 8, renderX);
            this.drawTexturedModalRect(renderX, this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(renderX + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public String getDisplayString(Minecraft mc) {
        String ret = I18n.format("options.guiScale") + ": ";
        if (sliderValue < GUISCALES.length) {
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
        if (index < 0 || index >= strArray.length) {
            index = 0;
        }

        return I18n.format(strArray[index]);
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY) {
        this.dragging = false;
        Minecraft.getMinecraft().gameSettings.guiScale = this.sliderValue;
    }
}
