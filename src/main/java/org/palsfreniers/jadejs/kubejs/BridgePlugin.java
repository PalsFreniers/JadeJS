package org.palsfreniers.jadejs.kubejs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import org.palsfreniers.jadejs.kubejs.providers.JadeElements;
import org.palsfreniers.jadejs.kubejs.providers.JadeJS;


public class BridgePlugin implements KubeJSPlugin {
    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(JadeEvents.GROUP);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("JadeElements", JadeElements.class);
        bindings.add("Jade", JadeJS.class);
    }
}
