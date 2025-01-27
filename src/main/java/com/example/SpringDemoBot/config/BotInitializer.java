package com.example.SpringDemoBot.config;

import com.example.SpringDemoBot.service.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.logging.Level;
import java.util.logging.Logger;

//@Slf4j
@Component
public class BotInitializer {

    private static final Logger logger = Logger.getLogger(BotInitializer.class.getName());

    @Autowired
    TelegramBot newBot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(newBot);
        } catch (TelegramApiException e) {
            logger.log(Level.parse("error occurred: {}"), e.getMessage());
            //log.error("error occurred: {}", e.getMessage());
        }
    }
}
