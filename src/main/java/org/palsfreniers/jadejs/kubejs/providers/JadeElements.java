package org.palsfreniers.jadejs.kubejs.providers;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.ITooltip;
import snownee.jade.api.fluid.JadeFluidObject;
import snownee.jade.api.ui.*;

public class JadeElements {
    private static final IElementHelper HELPER = IElementHelper.get();

    public static IElement text(Component component) {
        return HELPER.text(component);
    }

    public static IElement spacer(int width, int height) {
        return HELPER.spacer(width, height);
    }

    public static IElement item(ItemStack itemStack) {
        return HELPER.item(itemStack);
    }

    public static IElement item(ItemStack itemStack, float scale) {
        return HELPER.item(itemStack, scale);
    }

    public static IElement item(ItemStack itemStack, float scale, @Nullable String text) {
        return HELPER.item(itemStack, scale, text);
    }

    public static IElement smallItem(ItemStack itemStack) {
        return HELPER.smallItem(itemStack);
    }

    public static IElement fluid(JadeFluidObject jadeFluidObject) {
        return HELPER.fluid(jadeFluidObject);
    }

    public static IElement progress(float progress, @Nullable Component text, ProgressStyle style, BoxStyle boxStyle, boolean canDecrease) {
        return HELPER.progress(progress, text, style, boxStyle, canDecrease);
    }

    public static IElement progress(float progress) {
        return HELPER.progress(progress);
    }

    public static IElement progress(float progress, ResourceLocation baseSprite, ResourceLocation progressSprite, int width, int height, boolean canDecrease) {
        return HELPER.progress(progress, baseSprite, progressSprite, width, height, canDecrease);
    }

    public static IElement box(ITooltip tooltip, BoxStyle boxStyle) {
        return HELPER.box(tooltip, boxStyle);
    }

    public static IElement sprite(ResourceLocation sprite, int width, int height) {
        return HELPER.sprite(sprite, width, height);
    }
}
