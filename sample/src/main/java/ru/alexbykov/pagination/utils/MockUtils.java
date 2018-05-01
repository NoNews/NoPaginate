package ru.alexbykov.pagination.utils;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Bykov on 14.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public class MockUtils {

    private MockUtils() {
    }

    public static List<Integer> getMockItems() {
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        return list;
    }


    public static void mockHttpRequest(final NetworkCallback networkCallback) {
        new Handler().postDelayed(() -> {
            if (RandomUtils.getRandomBoolean()) {
                networkCallback.onSuccess();
            }
            else networkCallback.onError();
        }, 2000);
    }


    public interface NetworkCallback {
        void onSuccess();

        void onError();
    }
}
