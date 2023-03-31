package com.Javix.JavixTg.service;

import com.Javix.JavixTg.botApi.TelegramBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface HandlerService {

    void init(TelegramBot bot);

    void getMessage(Update update);

    void anyContentHandler(Update update);
}
