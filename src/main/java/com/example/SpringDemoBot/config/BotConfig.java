package com.example.SpringDemoBot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application-dev.properties")

public class BotConfig {
    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String botToken;

    public String getBotName() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }
}
