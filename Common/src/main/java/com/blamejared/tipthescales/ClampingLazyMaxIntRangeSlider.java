package com.blamejared.tipthescales;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public record ClampingLazyMaxIntRangeSlider(int minInclusive,
                                            IntSupplier maxSupplier) implements OptionInstance.IntRangeBase, OptionInstance.SliderableOrCyclableValueSet<Integer> {
    
    public @NotNull Optional<Integer> validateValue(@NotNull Integer value) {
        
        return Optional.of(Mth.clamp(value, this.minInclusive(), this.maxInclusive()));
    }
    
    public int maxInclusive() {
        
        return this.maxSupplier.getAsInt();
    }
    
    public @NotNull Codec<Integer> codec() {
        
        Function<Integer, DataResult<Integer>> func = (value) -> {
            int max = this.maxSupplier.getAsInt() + 1;
            return value.compareTo(this.minInclusive) >= 0 && value.compareTo(max) <= 0 ? DataResult.success(value) : DataResult.error("Value " + value + " outside of range [" + this.minInclusive + ":" + max + "]", value);
        };
        return Codec.INT.flatXmap(func, func);
    }
    
    public boolean createCycleButton() {
        
        return false;
    }
    
    public CycleButton.@NotNull ValueListSupplier<Integer> valueListSupplier() {
        
        return CycleButton.ValueListSupplier.create(IntStream.range(this.minInclusive, this.maxInclusive() + 1)
                .boxed()
                .toList());
    }
    
}