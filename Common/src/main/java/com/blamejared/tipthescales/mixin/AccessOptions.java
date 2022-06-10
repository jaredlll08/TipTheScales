package com.blamejared.tipthescales.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Options.class)
public interface AccessOptions {
    
    @Mutable
    @Accessor("guiScale")
    void tipthescales$setGuiScale(OptionInstance<Integer> guiScale);
    
}
