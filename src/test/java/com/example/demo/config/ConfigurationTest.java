package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = ConfigurationTest.Config.class)
@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class}
)
public class ConfigurationTest {
    @Autowired
    private Configuration configuration;

    @Test
    void shouldBeAbleToGetConfigurationProperties() {
        assertNotNull(configuration);
        assertNotNull(configuration.api());
        assertNotNull(configuration.api().client());
        assertNotNull(configuration.api().client().sendgrid());

        assertEquals(5, configuration.api().client().retry());
        assertEquals(30000L, configuration.api().client().timeout());
        assertEquals("WefOYY2332NDJDI", configuration.api().client().sendgrid().key());
    }

    @EnableConfigurationProperties(Configuration.class)
    static class Config {
    }

}
