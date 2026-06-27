package toutouchien.nodragonegg;

import org.bukkit.plugin.java.JavaPlugin;
import toutouchien.nodragonegg.listeners.NoDragonEggListener;
import toutouchien.nodragonegg.org.bstats.bukkit.Metrics;

public class NoDragonEgg extends JavaPlugin {
    private static final int BSTATS_PLUGIN_ID = 32230;

    private static NoDragonEgg instance;

    private Metrics bStats;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.bStats = new Metrics(this, BSTATS_PLUGIN_ID);
        this.getServer().getPluginManager().registerEvents(new NoDragonEggListener(), this);
    }

    @Override
    public void onDisable() {
        if (bStats != null) bStats.shutdown();
    }

    public void reload() {
        this.getSLF4JLogger().info("Reloading NoDragonEgg...");
        this.reloadConfig();
        this.getSLF4JLogger().info("NoDragonEgg reloaded.");
    }

    public static NoDragonEgg instance() {
        return instance;
    }
}
