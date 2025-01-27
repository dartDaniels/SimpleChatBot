package com.example.SpringDemoBot.service;

import com.example.SpringDemoBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

//@Slf4j
@Component

public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    private static final Logger logger = Logger.getLogger(TelegramBot.class.getName());

    public TelegramBot(BotConfig config) {
        this.config = config;
    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (message) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;

                default:
                    sendBotMessage(String.valueOf(chatId), "Sorry, I cant do it right now");
            }
        }



    }
    private void startCommandReceived(long chatId, String userName) {
        String answer = "Hi " + userName + "!" + "How are you?";

        sendBotMessage(String.valueOf(chatId), answer);

        logger.info("Replied to user: {}");
        //log.info("Replied to user: {}", userName);

    }

    private void sendBotMessage(String chatId, String botText) {
        SendMessage botMessage = new SendMessage();
        botMessage.setChatId(String.valueOf(chatId));
        botMessage.setText(botText);

        try {
            execute(botMessage);
        } catch (TelegramApiException e) {

            //log.error("error occurred: {}", e.getMessage());
        }
    }
}
