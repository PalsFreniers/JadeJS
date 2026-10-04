package org.palsfreniers.jadejs.kubejs.events;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.commands.arguments.item.ItemParser;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.JadeIds;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.impl.ui.TextElement;

import java.util.ArrayList;
import java.util.List;

public class BlockTooltipEvent implements KubeEvent {
    private final ITooltip tooltip;
    private final BlockAccessor accessor;
    private final float offsetY = -3;
    private static final Vec2 ITEM_SIZE = new Vec2(10.0F, 0.0F);
    private static final ResourceLocation DEFAULT_JADEJS_LOCATION = makeId("kubejs_custom");

    public static ResourceLocation makeId(String name) {
        return ResourceLocation.fromNamespaceAndPath("jadejs", name);
    }

    public BlockTooltipEvent(ITooltip tooltip, BlockAccessor accessor) {
        this.tooltip = tooltip;
        this.accessor = accessor;
    }

    public String getBlockId() {
        return BuiltInRegistries.BLOCK.getKey(accessor.getBlock()).toString();
    }

    public ITooltip getTooltip() {
        return tooltip;
    }

    public DataComponentPatch parseComponentPatch(String components) throws CommandSyntaxException {
        ItemParser parser = new ItemParser(accessor.getLevel().registryAccess());
        ItemParser.ItemResult result = parser.parse(new StringReader("minecraft:stick" + components));
        return result.components();
    }

    public Player getPlayer() {
        return accessor.getPlayer();
    }

    public void addComponent(Component line) {
        addComponent(tooltip.size(), line);
    }

    public void addComponent(String tag, Component line) {
        ResourceLocation loc = ResourceLocation.parse(tag);
        addComponentLoc(tooltip.size(), loc, line);
    }

    public void addComponent(int index, Component line) {
        addComponentLoc(index, DEFAULT_JADEJS_LOCATION, line);
    }

    public void addComponent(int index, String tag, Component line) {
        ResourceLocation loc = ResourceLocation.parse(tag);
        addComponentLoc(index, loc, line);
    }

    private void addComponentLoc(int index, ResourceLocation loc, Component line) {
        tooltip.add(index, line, loc);
    }

    public void addElement(IElement line) {
        addElement(tooltip.size(), line);
    }

    public void addElement(String tag, IElement line) {
        ResourceLocation loc = ResourceLocation.parse(tag);
        addElementLoc(tooltip.size(), loc, line);
    }

    public void addElement(int index, IElement line) {
        addElementLoc(index, DEFAULT_JADEJS_LOCATION, line);
    }

    public void addElement(int index, String tag, IElement line) {
        ResourceLocation loc = ResourceLocation.parse(tag);
        addElementLoc(index, loc, line);
    }

    public void addElementLoc(int index, ResourceLocation loc, IElement line) {
        tooltip.add(index, line.tag(loc));
    }

    public boolean hasTag(String tag) {
        ResourceLocation loc = ResourceLocation.parse(tag);
        return tooltip.get(loc).isEmpty();
    }

    public void setHarvestToolValid(boolean valid) {
        List<IElement> elements = new ArrayList<>(tooltip.get(JadeIds.MC_HARVEST_TOOL));
        if(elements.isEmpty()) return;

        if(elements.stream().noneMatch(e -> e instanceof TextElement)) return;
        elements.removeIf(e -> e instanceof TextElement);


        setHarvestTooltipMark(valid, elements);
    }

    public void setHarvestTools(List<String> items, boolean valid) {
        List<IElement> elements = new ArrayList<>();
        List<ItemStack> tools = new ArrayList<>();

        for(String item: items) {
            if(item.startsWith("#")) {
                TagKey<Item> tag = TagKey.create(Registries.ITEM, ResourceLocation.parse(item.substring(1)));
                for(Holder<Item> holder: BuiltInRegistries.ITEM.getTagOrEmpty(tag)) {
                    tools.add(new ItemStack(holder.value()));
                }
            } else {
                tools.add(new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(item))));
            }
        }

        IElementHelper helper = IElementHelper.get();
        elements.add(helper.spacer(5, 0));
        for(ItemStack tool : tools) {
            elements.add(helper.item(tool, 0.75F).translate(new Vec2(-1.0F, (float)offsetY)).size(ITEM_SIZE).message((String)null).tag(JadeIds.MC_HARVEST_TOOL));
        }
        setHarvestTooltipMark(valid, elements);
    }

    private void setHarvestTooltipMark(boolean valid, List<IElement> elements) {
        IThemeHelper theme = IThemeHelper.get();
        Component component = valid
                ? theme.success(Component.literal("✔"))
                : theme.danger(Component.literal("✕"));
        elements.add(IElementHelper.get().text(component)
                .scale(0.75F).zOffset(800).size(Vec2.ZERO)
                .translate(new Vec2(-3.0F, 6.25F + (float)offsetY)).tag(JadeIds.MC_HARVEST_TOOL));

        tooltip.remove(JadeIds.MC_HARVEST_TOOL);
        elements.forEach(e -> e.align(IElement.Align.RIGHT));
        tooltip.add(0, elements);
    }

    public boolean remove(String tag) {
        ResourceLocation loc = ResourceLocation.parse(tag);
        return tooltip.remove(loc);
    }
}
