package com.Javix.JavixTg.botApi;

import com.Javix.JavixTg.service.HandlerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter @Setter
public class TelegramBot extends TelegramWebhookBot {

    private String botUserName;
    private String botToken;
    private String webHookPath;

    @Autowired
    private HandlerService handlerService;

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update != null) {
            handlerService.init(TelegramBot.this);
            handlerService.getMessage(update);
        }
        return null;
    }
}

