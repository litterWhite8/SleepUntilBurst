package org.white.sleepuntilburst.unit;

import org.bukkit.entity.Player;

import java.util.*;

public class RandomAction {
    private static final Random random = new Random();
    private final List<WeightedMethod> weightedMethods = new ArrayList<>();

    public void performRandomAction(Player player) {
        GoodDream goodDream = new GoodDream();
        BadDream badDream = new BadDream();
        // 添加方法并设置权重
        addMethod(new WeightedMethod(() -> goodDream.giveIronForPlayer(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.giveGoldForPlayer(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.giveExperienceForPlayer(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.giveDiamondForPlayer(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.giveHeroOfTheVillageEffect(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.giveGoldenAppleForPlayer(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.giveSomePotionForPlayer(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.giveHarmForPlayer(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.spawnVillager(player), 33));
        addMethod(new WeightedMethod(() -> goodDream.spawnWanderingTrader(player), 33));

        addMethod(new WeightedMethod(() -> badDream.spawnZombie(player), 33));
        addMethod(new WeightedMethod(() -> badDream.giveSlowDiggingEffect(player), 33));

        // 随机调用一个方法
        callRandomMethod();
    }

    // 添加带有权重的方法
    public void addMethod(WeightedMethod method) {
        weightedMethods.add(method);
    }

    // 随机调用一个方法
    public void callRandomMethod() {
        // 权重总和
        int totalWeight = weightedMethods.stream().mapToInt(WeightedMethod::getWeight).sum();

        // 随机选择一个方法
        int randomIndex = random.nextInt(totalWeight);
        int currentIndex = 0;

        for (WeightedMethod method : weightedMethods) {
            currentIndex += method.getWeight();
            if (randomIndex < currentIndex) {
                method.getMethod().run(); // 调用选中的方法
                break;
            }
        }
    }
    private static class WeightedMethod {
        private final Runnable method;
        private final int weight;

        public WeightedMethod(Runnable method, int weight) {
            this.method = method;
            this.weight = weight;
        }

        public Runnable getMethod() {
            return method;
        }

        public int getWeight() {
            return weight;
        }
    }
}
