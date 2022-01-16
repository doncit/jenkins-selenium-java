package com.donata.utils.wait;

import org.testng.Assert;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

public class Wait {

    public static void Until(Integer seconds, BooleanSupplier condition) {
        Instant deadline = Instant.now().plusSeconds(seconds);

        do {
            if (condition.getAsBoolean()) {
                return;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        } while (Instant.now().isBefore(deadline));

        Assert.assertTrue(condition.getAsBoolean());
    }
}
