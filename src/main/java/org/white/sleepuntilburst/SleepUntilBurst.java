package org.white.sleepuntilburst;

import org.bukkit.plugin.java.JavaPlugin;
import org.white.sleepuntilburst.eventListener.NightSkipListener;

public final class SleepUntilBurst extends JavaPlugin {
    @Override
    public void onEnable() {
        NightSkipListener nightSkipListener = new NightSkipListener();
        getLogger().info("\"睡到爆炸\"已加载");
        getServer().getPluginManager().registerEvents(nightSkipListener,this);
    }

    @Override
    public void onDisable() {
        getLogger().info("\"睡到爆炸\"已卸载");
    }
}
