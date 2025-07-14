package com.hojunnnnn.kafka_practice.common.utils;

public final class DelayUtils {
    private DelayUtils() {}

    public static void randomDelay() {
        final long delay = 50 + (long) (Math.random() * 51); // 50ms ~ 100ms 랜덤 지연
        try {
            Thread.sleep(delay);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
