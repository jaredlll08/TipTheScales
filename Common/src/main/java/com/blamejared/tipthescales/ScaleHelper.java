package com.blamejared.tipthescales;

import com.blamejared.tipthescales.mixin.OptionAccessor;
import net.minecraft.client.Option;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class ScaleHelper {
    public static int guiScale;
    
    public static Component genericValueLabel(Option option, Component args) {
        return new TranslatableComponent("options.generic_value", ((OptionAccessor)option).getCaption(), args);
    }
}
