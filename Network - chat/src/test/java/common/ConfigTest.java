package common;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigTest {

    @Test
    void shouldReadPort() {

        Config config = new Config("settings.txt");

        assertEquals(8080, config.getPort());

    }

    @Test
    void shouldReadHost() {

        Config config =
                new Config("settings.txt");

        assertEquals(
                "localhost",
                config.getHost());

    }



}