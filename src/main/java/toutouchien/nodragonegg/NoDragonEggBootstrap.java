package toutouchien.nodragonegg;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import toutouchien.nodragonegg.commands.NoDragonEggCommand;

@SuppressWarnings("UnstableApiUsage")
public class NoDragonEggBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(BootstrapContext ctx) {
        ctx.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(NoDragonEggCommand.get());
        });
    }
}
