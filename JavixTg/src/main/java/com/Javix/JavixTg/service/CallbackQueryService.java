package com.Javix.JavixTg.service;


import com.Javix.JavixTg.botApi.TelegramBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface CallbackQueryService {

    void init(TelegramBot bot);

    void callbackQuery(Update update);

    void sendGame(Update update, String gameName);

    void callBackGame(Update update, String game);

    void handleGame(Update update, String game);
}
