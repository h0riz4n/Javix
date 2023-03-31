package com.Javix.JavixTg.service;

import com.Javix.JavixTg.botApi.TelegramBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface MessageHandlerService {

    void init(TelegramBot bot);

    void handleUpdate(Update update);

    void startCommand(Update update);

    void helpCommand(Update update);

    void playCommand(Update update);

    void anyContentTypeHandler(Update update);

    void deleteCommand(Update update);

    void profileCommand(Update update);

    void statsCommand(Update update);

    void botSend(SendMessage sendMessage, SendSticker sendSticker);
}
