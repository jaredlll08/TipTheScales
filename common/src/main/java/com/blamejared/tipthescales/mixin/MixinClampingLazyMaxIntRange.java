package com.blamejared.tipthescales.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionInstance.ClampingLazyMaxIntRange.class)
public class MixinClampingLazyMaxIntRange {
    
    @Inject(method = "createCycleButton", at = @At("HEAD"), cancellable = true)
    public void tipthescales$createCycleButton(CallbackInfoReturnable<Boolean> cir) {
        
        if(this == Minecraft.getInstance().options.guiScale().values()) {
            cir.setReturnValue(false);
        }
    }
    
}
