package org.palsfreniers.jadejs.jade;

import net.minecraft.resources.ResourceLocation;
import org.palsfreniers.jadejs.PMain;
import org.palsfreniers.jadejs.kubejs.JadeEvents;
import org.palsfreniers.jadejs.kubejs.events.BlockTooltipEvent;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum KubeJSProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        JadeEvents.BLOCK_TOOLTIP.post(new BlockTooltipEvent(iTooltip, blockAccessor));
    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(PMain.MODID, "kubejs");
    }
}
