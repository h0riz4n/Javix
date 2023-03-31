package com.Javix.JavixTg.service.impl;

import com.Javix.JavixTg.botApi.TelegramBot;
import com.Javix.JavixTg.modelsJSON.CommandModel;
import com.Javix.JavixTg.modelsJSON.MessageModel;
import com.Javix.JavixTg.service.CallbackQueryService;
import com.Javix.JavixTg.service.MessageHandlerService;
import com.Javix.JavixTg.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class HandlerServiceImpl implements HandlerService {

    private TelegramBot bot;

    @Autowired
    private CommandModel commandModel;

    @Autowired
    private MessageHandlerService messageHandlerService;

    @Autowired
    private CallbackQueryService callbackQueryService;

    @Override
    public void init(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void getMessage(Update update) {
        messageHandlerService.init(bot);
        callbackQueryService.init(bot);

        if (update.hasMessage() && update.getMessage().hasText())
            messageHandlerService.handleUpdate(update);

        else if (update.hasCallbackQuery() && update.getCallbackQuery().getGameShortName() != null && update.getCallbackQuery().getGameShortName().equals("tic_tac_toe"))
            callbackQueryService.callBackGame(update, "tic_tac_toe");

        else if (update.hasCallbackQuery() && update.getCallbackQuery().getGameShortName() != null && update.getCallbackQuery().getGameShortName().equals("number_game"))
            callbackQueryService.callBackGame(update, "number_game");

        else if (update.hasCallbackQuery())
            callbackQueryService.callbackQuery(update);

        else
            anyContentHandler(update);
    }

    @Override
    public void anyContentHandler(Update update) {
        MessageModel sample = commandModel.getAnyContentType();
        String chatID = update.getMessage().getChatId().toString();

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage(chatID, sample.getMessage());
        sendMessage.setChatId(chatID);
        sendMessage.setParseMode("html");

        try {
            bot.executeAsync(sendSticker);
            bot.executeAsync(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
