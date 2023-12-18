package com.test.roc.core.util;

import java.util.Random;

/**
 * @ClassName RandomGenerator
 * @Description TODO
 * @Author cosmo
 * @DATE 2023-10-26 10:01
 * @Version 1.0
 */
public class RandomGenerator {
    public static double generateRandomDouble(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Invalid range: min must be less than max.");
        }

        Random random = new Random();
        double randomValue = min + (max - min) * random.nextDouble();

        // 保留两位小数
        return Math.round(randomValue * 100.0) / 100.0;
    }

    public static boolean generateRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static int generateRandomInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Invalid range: min must be less than or equal to max.");
        }

        Random random = new Random();
        int randomValue = min + random.nextInt(max - min + 1);

        return randomValue;
    }

}
