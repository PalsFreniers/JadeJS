package org.palsfreniers.jadejs.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import org.palsfreniers.jadejs.kubejs.events.BlockTooltipEvent;

public interface JadeEvents {
    EventGroup GROUP = EventGroup.of("JadeEvents");
    EventHandler BLOCK_TOOLTIP = GROUP.client("blockTooltip", () -> BlockTooltipEvent.class);
}
