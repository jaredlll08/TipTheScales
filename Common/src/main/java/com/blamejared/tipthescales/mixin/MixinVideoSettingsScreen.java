package com.blamejared.tipthescales.mixin;

import com.blamejared.tipthescales.ScaleHelper;
import net.minecraft.client.*;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.VideoSettingsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VideoSettingsScreen.class)
public class MixinVideoSettingsScreen extends OptionsSubScreen {
    
    public MixinVideoSettingsScreen(Screen param0, Options param1, Component param2) {
        
        super(param0, param1, param2);
    }
    
    @Inject(method = "init", at = @At("HEAD"))
    public void init(CallbackInfo ci) {
        
        Option[] options = VideoSettingsScreenAccessor.getOPTIONS();
        for(int i = 0; i < options.length; i++) {
            Option option = options[i];
            if(option.equals(Option.GUI_SCALE)) {
                
                CycleOption<Integer> cycleOption = (CycleOption<Integer>) option;
                OptionAccessor accessOption = (OptionAccessor) cycleOption;
                
                String captionKey = accessOption.getCaption().getContents();
                if(accessOption.getCaption() instanceof TranslatableComponent transComp) {
                    captionKey = transComp.getKey();
                }
                
                int maxScale = Minecraft.getInstance()
                        .getWindow()
                        .calculateScale(0, Minecraft.getInstance().isEnforceUnicode());
                ProgressOption progressOption = new ProgressOption(captionKey,
                        0,
                        maxScale,
                        1,
                        options1 -> (double) options1.guiScale,
                        (options1, aDouble) -> options1.guiScale = aDouble.intValue(),
                        (options1, progressOption1) -> ScaleHelper.genericValueLabel(progressOption1, options1.guiScale == 0 ? new TranslatableComponent("options.guiScale.auto") : new TextComponent(options1.guiScale + "")));
                options[i] = progressOption;
            }
        }
    }
    
    @Inject(method = "mouseClicked", at = @At(value = "HEAD"))
    public void mouseClickedHead(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        
        ScaleHelper.guiScale = this.options.guiScale;
    }
    
    
    @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;resizeDisplay()V"))
    public void mouseClickedResize(Minecraft instance) {}
    
    
    @Inject(method = "mouseReleased", at = @At(value = "HEAD"))
    public void mouseReleasedHead(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        
        if(this.options.guiScale != ScaleHelper.guiScale) {
            this.minecraft.resizeDisplay();
        }
        ScaleHelper.guiScale = this.options.guiScale;
    }
    
    @Redirect(method = "mouseReleased", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;resizeDisplay()V"))
    public void mouseReleasedResize(Minecraft instance) {}
    
    
}
