package com.blamejared.tipthescales.mixin;

import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.VideoSettingsScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(VideoSettingsScreen.class)
public abstract class MixinVideoSettingsScreen extends OptionsSubScreen {
    
    @Unique
    public int tipthescales$cachedGuiScale;
    
    public MixinVideoSettingsScreen(Screen parent, Options options, Component title) {
        
        super(parent, options, title);
    }
    
    @Inject(method = "mouseClicked", at = @At(value = "HEAD"))
    public void tipTheScales$mouseClickedHead(MouseButtonEvent event, boolean doubleClick, CallbackInfoReturnable<Boolean> cir) {
        
        tipthescales$cachedGuiScale = this.options.guiScale().get();
    }
    
    // This feels horrible, but I am not sure how better to do it, and I don't *think* other mods will do this, I hope anyway, future me will deal with it
    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        
        if(this.options.guiScale().get() != tipthescales$cachedGuiScale) {
            Objects.requireNonNull(this.minecraft).resizeGui();;
        }
        tipthescales$cachedGuiScale = this.options.guiScale().get();
        return super.mouseReleased(event);
    }
    
}
