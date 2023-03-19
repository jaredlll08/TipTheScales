package com.blamejared.tipthescales.mixin;

import org.spongepowered.asm.mixin.extensibility.*;

import java.util.*;

public class MixinPlugin implements IMixinConfigPlugin {
    
    public boolean optifineLoaded;
    
    @Override
    public void onLoad(String mixinPackage) {
        
        try {
            Class.forName("net.optifine.Config", false, MixinPlugin.class.getClassLoader());
            optifineLoaded = true;
        } catch(Exception ignored) {
        }
    }
    
    @Override
    public String getRefMapperConfig() {
        
        return null;
    }
    
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        
        return !optifineLoaded;
    }
    
    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    
    }
    
    @Override
    public List<String> getMixins() {
        
        return null;
    }
    
    @Override
    public void postApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    
    }
    
    @Override
    public void preApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    
    }
    
    
}
