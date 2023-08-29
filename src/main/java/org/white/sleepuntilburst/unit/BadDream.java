package org.white.sleepuntilburst.unit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BadDream {

    //生成僵尸并致盲玩家
    public void spawnZombie(Player player){
        Location playerLocation = player.getLocation();
        for(int temp = 0;temp < 5;temp++){
            playerLocation.getWorld().spawnEntity(playerLocation, EntityType.ZOMBIE);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*10, 1));
        player.sendActionBar("做了个噩梦，梦见僵尸戳瞎了你的眼，梦实现了!");

    }

    public void giveSlowDiggingEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20*60, 1));
        player.sendActionBar("做了个噩梦，梦见挖矿挖个十天十夜，再也不想挖矿了!");
    }
}
