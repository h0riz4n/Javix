package com.Javix.JavixTg.controller;

import com.Javix.JavixTg.botApi.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {

    @Autowired
    private TelegramBot telegramBot;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateRecieved(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
}
