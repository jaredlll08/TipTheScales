package com.blamejared.tipthescales.mixin;

import com.blamejared.tipthescales.ClampingLazyMaxIntRangeSlider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(Options.class)
public class MixinOptions {
    
    @Inject(method = "load", at = @At(value = "HEAD"))
    public void tipthescales$load(CallbackInfo ci) {
        
        ((AccessOptions) this).tipthescales$setGuiScale(new OptionInstance<>("options.guiScale", OptionInstance.noTooltip(), (caption, value) -> {
            Component current = value == 0 ? Component.translatable("options.guiScale.auto") : Component.literal(Integer.toString(value));
            return Options.genericValueLabel(caption, current);
        }, new ClampingLazyMaxIntRangeSlider(0, () -> {
            Minecraft $$0x = Minecraft.getInstance();
            return !$$0x.isRunning() ? 2147483646 : $$0x.getWindow().calculateScale(0, $$0x.isEnforceUnicode());
        }), 0, (value) -> {
        }));
    }
    
}
