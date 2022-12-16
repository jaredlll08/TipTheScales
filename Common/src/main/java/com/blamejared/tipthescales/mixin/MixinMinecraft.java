package com.blamejared.tipthescales.mixin;

import com.blamejared.tipthescales.ClampingLazyMaxIntRangeSlider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.main.GameConfig;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Shadow
    @Final
    public Options options;
    
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(GameConfig gameConfig, CallbackInfo ci) {
        ((AccessOptions) this.options).tipthescales$setGuiScale(new OptionInstance<>("options.guiScale", OptionInstance.noTooltip(), (caption, value) -> {
            Component current = value == 0 ? Component.translatable("options.guiScale.auto") : Component.literal(Integer.toString(value));
            return Options.genericValueLabel(caption, current);
        }, new ClampingLazyMaxIntRangeSlider(0, () -> {
            Minecraft mc = Minecraft.getInstance();
            return !mc.isRunning() ? 2147483646 : mc.getWindow().calculateScale(0, mc.isEnforceUnicode());
        }), 0, (value) -> {
        }));
    }
    
}
