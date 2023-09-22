package org.white.sleepuntilburst;

import org.bukkit.plugin.java.JavaPlugin;
import org.white.sleepuntilburst.eventListener.NightSkipListener;
import org.white.sleepuntilburst.unit.Files;

public final class SleepUntilBurst extends JavaPlugin {

    private static SleepUntilBurst instance;
    @Override
    public void onEnable() {
        instance = this;
        NightSkipListener nightSkipListener = new NightSkipListener();
        Files.load(getDataFolder());
        getServer().getPluginManager().registerEvents(nightSkipListener,this);
        getLogger().info("\"睡到爆炸\"已加载");

    }

    @Override
    public void onDisable() {
        getLogger().info("\"睡到爆炸\"已卸载");
    }

    public static SleepUntilBurst getInstance() {
        return instance;
    }
}
