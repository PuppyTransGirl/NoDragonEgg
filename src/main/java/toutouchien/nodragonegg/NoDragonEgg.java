package toutouchien.nodragonegg;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.java.JavaPlugin;

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

        registerMetrics();
    }

    @Override
    public void onDisable() {
        if (bStats != null) bStats.shutdown();
    }

    private void registerMetrics() {
        this.bStats = new Metrics(this, BSTATS_PLUGIN_ID);
        bStats.addCustomChart(new SimplePie("enabled", () -> String.valueOf(this.getConfig().getBoolean("enabled"))));
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
