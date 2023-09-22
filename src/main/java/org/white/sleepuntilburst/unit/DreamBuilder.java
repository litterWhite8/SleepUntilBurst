package org.white.sleepuntilburst.unit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.white.sleepuntilburst.Entity.Dream;
import org.white.sleepuntilburst.Entity.DreamEvent;
import org.white.sleepuntilburst.SleepUntilBurst;

import java.util.List;

public class DreamBuilder {
    public void buildDream(Dream dream, Player player){
        List<DreamEvent> dreamEventList = dream.getDreamEventList();
        for(DreamEvent event : dreamEventList){
            switch (event.getEventType()) {
                case "give" -> giveExecute(event.getEventContent(),event.getCount(),player);
                case "exp" -> expExecute(event.getCount(),player);
                case "entity" -> entityExecute(event.getEventContent(),event.getCount(),player);
                case "potion" -> PotionExecute(event.getEventContent(),event.getSec(),event.getCount(),player);
                case "boom" -> boomExecute(event.getSec(),event.getCount(),player);
                case "lightning" -> lightningExecute(event.getSec(),event.getCount(),player);
                case "command" -> commandExecute(event.getCommand(),player);

            }
        }
    }

    private void giveExecute(String itemName,int count,Player player){
        Material material = Material.matchMaterial(itemName, true);
        ItemStack itemStack = new ItemStack(material,count);
        player.getInventory().addItem(itemStack);
    }

    private void expExecute(int count,Player player){
        player.giveExp(count);
    }

    private void entityExecute(String entityName,int count,Player player){
        EntityType entityType = EntityType.valueOf(entityName.toUpperCase());
        Location playerLocation = player.getLocation();
        for(int temp = 0;temp < count;temp++) {
            player.getWorld().spawnEntity(playerLocation, entityType);
        }
    }

    private void PotionExecute(String potionName,int sec,int count,Player player){
        PotionEffectType effectType = PotionEffectType.getByName(potionName);
        PotionEffect potionEffect = new PotionEffect(effectType, sec*20, count);
        player.addPotionEffect(potionEffect);
    }

    private void boomExecute(int sec,int count,Player player){

        Location playerLocation = player.getLocation();
        World world = player.getWorld();

        // 创建一个延迟任务以在下一个刻钟中触发爆炸
        new BukkitRunnable() {
            @Override
            public void run() {
                world.createExplosion(playerLocation, count); // 4.0F 是爆炸的威力，可以根据需要调整
            }
        }.runTaskLater(SleepUntilBurst.getInstance(), sec*20L); // 20L 表示延迟 20 刻钟（1 秒 = 20 刻钟）
    }

    private void lightningExecute(int sec,int count,Player player){
        Location playerLocation = player.getLocation();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0;i < count;i++) {
                    player.getWorld().strikeLightning(playerLocation);
                }
            }
        }.runTaskLater(SleepUntilBurst.getInstance(), sec*20L);
    }

    private void commandExecute(String command,Player player){
        String[] ss = command.split("Sleeper");
        if(ss.length > 1){
            command = ss[0];
            for (int i = 1;i < ss.length;i++){
                command += player.getName() + ss[i];
            }
        }
        System.out.println(command);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }


}
