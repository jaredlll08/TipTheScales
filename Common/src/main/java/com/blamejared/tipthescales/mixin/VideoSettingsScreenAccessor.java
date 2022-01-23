package com.blamejared.tipthescales.mixin;

import net.minecraft.client.Option;
import net.minecraft.client.gui.screens.VideoSettingsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(VideoSettingsScreen.class)
public interface VideoSettingsScreenAccessor {
    
    @Accessor
    static Option[] getOPTIONS() {
        
        throw new UnsupportedOperationException();
    }
    
}
