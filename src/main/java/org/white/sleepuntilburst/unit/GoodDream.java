package org.white.sleepuntilburst.unit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoodDream {
    //给予玩家一个钻石
    public void giveDiamondForPlayer(Player player) {
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        player.getInventory().addItem(item);
        player.sendActionBar("做了个好梦，梦见获得一个钻石，梦成真了!");
    }
    //给予玩家金锭
    public void giveGoldForPlayer(Player player) {
        ItemStack item = new ItemStack(Material.GOLD_INGOT, 16);
        player.getInventory().addItem(item);
        player.sendActionBar("做了个好梦，梦见获得十六个金锭，梦成真了!");
    }

    //给予玩家铁锭
    public void giveIronForPlayer(Player player) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, 16);
        player.getInventory().addItem(item);
        player.sendActionBar("做了个好梦，梦见获得十六个铁锭，梦成真了!");
    }

    //给予玩家铁锭
    public void giveGoldenAppleForPlayer(Player player) {
        ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 4);
        player.getInventory().addItem(item);
        player.sendActionBar("做了个好梦，梦见金苹果从天而降，梦成真了!");
    }
    //给予玩家50点经验
    public void giveExperienceForPlayer(Player player) {
        player.giveExp(50);
        player.sendActionBar("做了个好梦，梦见获得50经验，梦成真了!");
    }
    //给予玩家村庄英雄效果
    public void giveHeroOfTheVillageEffect(Player player) {
        PotionEffect heroOfTheVillage = new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 10*60*20, 1);
        player.addPotionEffect(heroOfTheVillage); // 给予玩家速度效果
        player.sendActionBar("做了个好梦，梦见自己成为了英雄，梦成真了!");
    }

    //给予一些效果
    public void giveSomePotionForPlayer(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30*20, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30*20, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30*20, 2));
        player.sendActionBar("做了个好梦，梦见自己BUFF叠满，梦成真了!");
    }

    //给予一些效果
    public void giveHarmForPlayer(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 100));
        player.sendActionBar("做了个好梦，梦见活力无穷，梦成真了!");
    }
    //在玩家身边生成村民
    public void spawnVillager(Player player){
        Location playerLocation = player.getLocation();
        playerLocation.getWorld().spawnEntity(playerLocation, EntityType.VILLAGER);
        player.sendActionBar("做了个好梦，梦见村民从天而降，梦成真了!");

    }

    //在玩家身边生成流浪商人
    public void spawnWanderingTrader(Player player){
        Location playerLocation = player.getLocation();
        playerLocation.getWorld().spawnEntity(playerLocation, EntityType.WANDERING_TRADER);
        ItemStack item = new ItemStack(Material.EMERALD, 32);
        player.getInventory().addItem(item);
        player.sendActionBar("做了个好梦，梦见你发财的同时商人来到了你的面前，梦成真了!");

    }
}
