package com.middleware.libertymutualmiddlewarechallenge.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorUtilsTest {

    @Test
    void testGenerateRandomString() {
        String randomString = GeneratorUtils.generateRandomString();
        assertNotNull(randomString);
        assertEquals(GeneratorUtils.DEFAULT_LENGTH, randomString.length());

        String longerRandomString = GeneratorUtils.generateRandomString(30);
        assertNotNull(longerRandomString);
        assertEquals(30, longerRandomString.length());

        String shorterRandomString = GeneratorUtils.generateRandomString(10);
        assertNotNull(shorterRandomString);
        assertEquals(10, shorterRandomString.length());
    }

    @Test
    void testGenerateRandomStringWithInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> GeneratorUtils.generateRandomString(-1));
    }
}
