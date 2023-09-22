package org.white.sleepuntilburst.unit;

import org.bukkit.entity.Player;
import org.white.sleepuntilburst.Entity.Dream;

import java.util.*;

public class RandomAction {

    private DreamBuilder dreamBuilder = new DreamBuilder();

    private static Map<String,String> record = new HashMap<>();
    private DreamParser dreamParser = new DreamParser();

    private  Dream GetRandomDream(String userName) {
        int totalWeight = 0;
        List<Dream> dreams = dreamParser.getDream();
        record.putIfAbsent(userName, "bad-1");
        String type = record.get(userName).split("-")[0];
        int count = Integer.parseInt(record.get(userName).split("-")[1]);

        if (dreams != null) {
            for (Dream obj : dreams) {
                if(obj.getType().equals(type)){
                    obj.reduceWeights((10-count*2)*0.1f);//减少连续好梦或连续噩梦的概率
                }
                totalWeight += obj.getWeight();
            }
        }
        Random random = new Random();
        int randomWeight = random.nextInt(totalWeight);
        int currentIndex = 0;
        for (Dream obj : dreams) {
            currentIndex += obj.getWeight();
            if (randomWeight < currentIndex) {
                return obj;
            }
        }
        return null;
    }

    public void performRandomAction(Player player) {
        String playerName = player.getName();
        Dream dream = GetRandomDream(playerName);

        String type = record.get(playerName).split("-")[0];
        int count = Integer.parseInt(record.get(playerName).split("-")[1]);
        if(dream.getType().equals(type)){
            record.put(playerName,type + "-" + (count + 1));
        }else{
            record.put(playerName,type.equals("good") ? "bad-1" : "good-1");
        }
        dreamBuilder.buildDream(dream,player);
        player.sendActionBar(dream.getDes());

    }

}
