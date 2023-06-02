package com.Javix.JavixTg.botApi;

import com.Javix.JavixTg.config.BotConfig;
import com.Javix.JavixTg.service.HandlerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Getter @Setter
public class TelegramBot extends TelegramLongPollingBot {

    private String botUserName;
    private String botToken;
    private String webHookPath;

    @Autowired
    private HandlerService handlerService;

    public TelegramBot(BotConfig botConfig) {
        botUserName = botConfig.getBotUserName();
        botToken = botConfig.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        handlerService.init(TelegramBot.this);
        handlerService.getMessage(update);
    }
}

