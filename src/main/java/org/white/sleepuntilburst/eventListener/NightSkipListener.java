package org.white.sleepuntilburst.eventListener;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.white.sleepuntilburst.unit.RandomAction;

import java.util.HashSet;
import java.util.Set;

public class NightSkipListener implements Listener {

    private Set<Player> sleepingPlayers = new HashSet<>();
    private RandomAction randomAction = new RandomAction();
    // 监听玩家进入床事件
    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        // 将玩家添加到正在睡觉的玩家集合中
        sleepingPlayers.add(player);
    }

    // 监听玩家离开床事件
    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        // 将玩家添加到正在睡觉的玩家集合中
        sleepingPlayers.add(player);
    }

    // 监听时间跳过事件
    @EventHandler
    public void onTimeSkip(TimeSkipEvent event) {
        if (event.getSkipReason() == TimeSkipEvent.SkipReason.NIGHT_SKIP) {
            // 遍历正在睡觉的玩家集合
            for (Player player : sleepingPlayers) {
                // 在控制台输出正在睡觉的玩家信息，或者执行其他操作
                randomAction.performRandomAction(player);
            }
            sleepingPlayers.clear();
        }
    }
}
