package com.blamejared.tipthescales.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class MixinOptions {
    
    @Shadow
    public abstract OptionInstance<Integer> guiScale();
    
    @Inject(method = "load", at = @At("HEAD"))
    public void init(CallbackInfo ci) {
        
        OptionInstance<Integer> newScale = new OptionInstance<>("options.guiScale", OptionInstance.noTooltip(), (caption, value) -> {
            Component current = value == 0 ? Component.translatable("options.guiScale.auto") : Component.literal(Integer.toString(value));
            return Options.genericValueLabel(caption, current);
        }, guiScale().values(), 0, ($$0x) -> {
        });
        
        ((AccessOptions) this).tipthescales$setGuiScale(newScale);
    }
    
}
