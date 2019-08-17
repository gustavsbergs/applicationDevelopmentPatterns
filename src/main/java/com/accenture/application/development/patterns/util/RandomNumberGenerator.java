package main.java.com.accenture.application.development.patterns.util;

import java.util.Random;

public class RandomNumberGenerator {

    public Integer generate(final Integer range) {
        final Random randomizer = new Random();
        return randomizer.nextInt(range) + 1;
    }
}
