package org.palsfreniers.jadejs.kubejs.providers;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.fluid.JadeFluidObject;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.ColorPalette;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.api.ui.ProgressStyle;

import java.util.Optional;

public class JadeJS {

    public static Vec2 vec2(float x, float y) {
        return new Vec2(x, y);
    }

    public static BoxStyle tooltipBox() {
        return IThemeHelper.get().theme().tooltipStyle;
    }

    public static BoxStyle nestedBox() {
        return IThemeHelper.get().theme().nestedBoxStyle;
    }

    public static BoxStyle viewGroupBox() {
        return IThemeHelper.get().theme().viewGroupStyle;
    }

    public static BoxStyle emptyGradientBorderBox() {
        return BoxStyle.GradientBorder.TRANSPARENT;
    }

    public static BoxStyle spriteBox(ColorPalette palette, String sprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.empty(), palette, Optional.empty(), loc, Optional.empty());
    }

    public static BoxStyle spriteBox(float[] progressOffset, ColorPalette palette, String sprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.ofNullable(progressOffset), palette, Optional.empty(), loc, Optional.empty());
    }

    public static BoxStyle spriteBox(ColorPalette palette, int[] padding, String sprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.empty(), palette, Optional.ofNullable(padding), loc, Optional.empty());
    }

    public static BoxStyle spriteBox(float[] progressOffset, ColorPalette palette, int[] padding, String sprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.ofNullable(progressOffset), palette, Optional.ofNullable(padding), loc, Optional.empty());
    }

    public static BoxStyle spriteBox(ColorPalette palette, String sprite, String withIconSprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        ResourceLocation loc2 = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.empty(), palette, Optional.empty(), loc, Optional.of(loc2));
    }

    public static BoxStyle spriteBox(float[] progressOffset, ColorPalette palette, String sprite, String withIconSprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        ResourceLocation loc2 = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.ofNullable(progressOffset), palette, Optional.empty(), loc, Optional.of(loc2));
    }

    public static BoxStyle spriteBox(ColorPalette palette, int[] padding, String sprite, String withIconSprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        ResourceLocation loc2 = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.empty(), palette, Optional.ofNullable(padding), loc, Optional.of(loc2));
    }

    public static BoxStyle spriteBox(float[] progressOffset, ColorPalette palette, int[] padding, String sprite, String withIconSprite) {
        ResourceLocation loc = ResourceLocation.parse(sprite);
        ResourceLocation loc2 = ResourceLocation.parse(sprite);
        return new BoxStyle.SpriteBase(Optional.ofNullable(progressOffset), palette, Optional.ofNullable(padding), loc, Optional.of(loc2));
    }

    public static JadeFluidObject fluidObject() {
        return fluidObject(Fluids.EMPTY, 0L);
    }

    public static JadeFluidObject fluidObject(Fluid fluid) {
        return fluidObject(fluid, JadeFluidObject.blockVolume());
    }

    public static JadeFluidObject fluidObject(Fluid fluid, long amount) {
        return fluidObject(fluid, amount, DataComponentPatch.EMPTY);
    }

    public static JadeFluidObject fluidObject(Fluid fluid, long amount, DataComponentPatch components) {
        return JadeFluidObject.of(fluid, amount, components);
    }

    public static ProgressStyle progressStyle() {
        return IElementHelper.get().progressStyle();
    }
}
