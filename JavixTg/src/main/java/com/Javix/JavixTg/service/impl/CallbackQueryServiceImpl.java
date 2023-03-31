package com.Javix.JavixTg.service.impl;

import com.Javix.JavixTg.backend.api.ApiUsersService;
import com.Javix.JavixTg.botApi.TelegramBot;
import com.Javix.JavixTg.service.CallbackQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendGame;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class CallbackQueryServiceImpl implements CallbackQueryService {

    private TelegramBot bot;

    @Autowired
    private ApiUsersService apiUsersService;

    @Override
    public void init(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void callbackQuery(Update update) {
        String game = update.getCallbackQuery().getData();

        switch (game) {
            case "TicTacToeGame":
                sendGame(update, "tic_tac_toe");
                break;
            case "NumbersGame":
                sendGame(update, "number_game");
                break;
            default:
                break;
        }
    }

    @Override
    public void handleGame(Update update, String game) {
        if (game.equals("tic_tac_toe"))
            callBackGame(update, "tic_tac_toe");

        if (game.equals("number_game"))
            callBackGame(update, "number_game");
    }

    @Override
    public void callBackGame(Update update, String game) {
        String chatID = update.getCallbackQuery().getFrom().getId().toString();
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();

        answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getId());
        answerCallbackQuery.setUrl(apiUsersService.getURL() + "/" + game + "?id="+chatID);

        try {
            bot.executeAsync(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendGame(Update update, String gameName) {
        String chatID = update.getCallbackQuery().getFrom().getId().toString();
        SendGame game = new SendGame(chatID, gameName);

        try {
            bot.executeAsync(game);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
