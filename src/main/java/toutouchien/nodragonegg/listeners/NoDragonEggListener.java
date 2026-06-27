package toutouchien.nodragonegg.listeners;

import io.papermc.paper.event.block.DragonEggFormEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import toutouchien.nodragonegg.NoDragonEgg;

public class NoDragonEggListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onDragonEggForm(DragonEggFormEvent event) {
        if (!NoDragonEgg.instance().getConfig().getBoolean("enabled"))
            return;

        event.setCancelled(true);
    }
}
