package ru.alexbykov.pagination.utils;

import java.util.Random;

/**
 * Created by Alex Bykov on 14.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public class RandomUtils {
    private RandomUtils() {
    }
    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
