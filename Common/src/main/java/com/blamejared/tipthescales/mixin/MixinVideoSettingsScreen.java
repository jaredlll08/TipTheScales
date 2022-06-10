package com.blamejared.tipthescales.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.VideoSettingsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(VideoSettingsScreen.class)
public class MixinVideoSettingsScreen extends OptionsSubScreen {
    
    @Unique
    public int tipthescales$cachedGuiScale;
    
    public MixinVideoSettingsScreen(Screen parent, Options options, Component title) {
        
        super(parent, options, title);
    }
    
    @Inject(method = "mouseClicked", at = @At(value = "HEAD"))
    public void tipTheScales$mouseClickedHead(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        
        tipthescales$cachedGuiScale = this.options.guiScale().get();
    }
    
    @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;resizeDisplay()V"))
    public void tipTheScales$mouseClickedResize(Minecraft instance) {}
    
    @Inject(method = "mouseReleased", at = @At(value = "HEAD"))
    public void tipTheScales$mouseReleasedHead(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        
        if(this.options.guiScale().get() != tipthescales$cachedGuiScale) {
            Objects.requireNonNull(this.minecraft).resizeDisplay();
        }
        tipthescales$cachedGuiScale = this.options.guiScale().get();
    }
    
    @Redirect(method = "mouseReleased", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;resizeDisplay()V"))
    public void tipTheScales$mouseReleasedResize(Minecraft instance) {}
    
}
