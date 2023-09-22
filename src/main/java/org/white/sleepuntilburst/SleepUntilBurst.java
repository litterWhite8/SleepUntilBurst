package org.white.sleepuntilburst;

import org.bukkit.plugin.java.JavaPlugin;
import org.white.sleepuntilburst.eventListener.NightSkipListener;
import org.white.sleepuntilburst.unit.DreamParser;
import org.white.sleepuntilburst.unit.Files;

import java.util.List;

public final class SleepUntilBurst extends JavaPlugin {

    private static SleepUntilBurst instance;

    private static final DreamParser parser = new DreamParser();
    @Override
    public void onEnable() {
        instance = this;
        Files.load(getDataFolder());
        parser.dreamCheck();
        NightSkipListener nightSkipListener = new NightSkipListener();
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
