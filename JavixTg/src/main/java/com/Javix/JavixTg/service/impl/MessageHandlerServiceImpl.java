package com.Javix.JavixTg.service.impl;

import com.Javix.JavixTg.backend.api.ApiUsersService;
import com.Javix.JavixTg.botApi.TelegramBot;
import com.Javix.JavixTg.modelsJSON.CommandModel;
import com.Javix.JavixTg.modelsJSON.MessageModel;
import com.Javix.JavixTg.modelsJSON.ProfileModel;
import com.Javix.JavixTg.modelsJSON.StatsModel;
import com.Javix.JavixTg.service.MessageHandlerService;
import com.Javix.JavixTg.service.KeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {

    private TelegramBot bot;

    @Autowired
    private CommandModel commandModel;

    @Autowired
    private KeyboardService keyboardService;

    @Autowired
    private ApiUsersService apiUsersService;

    @Override
    public void init(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void handleUpdate(Update update) {
        String message = update.getMessage().getText();
        switch (message) {
            case "/start":
                startCommand(update);
                break;
            case "/help":
                helpCommand(update);
                break;
            case "/play":
                playCommand(update);
                break;
            case "/delete":
                deleteCommand(update);
                break;
            case "/profile":
                profileCommand(update);
                break;
            case "/stats":
                statsCommand(update);
                break;
            default:
                anyContentTypeHandler(update);
                break;
        }
    }

    @Override
    public void botSend(SendMessage sendMessage, SendSticker sendSticker) {
        try {
            bot.executeAsync(sendSticker);
            bot.executeAsync(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startCommand(Update update) {
        MessageModel sample = commandModel.getStartCommand();
        String chatID = update.getMessage().getChatId().toString();

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage(
                chatID,
                sample.getMessage()
        );
        sendMessage.setParseMode("html");

        botSend(sendMessage, sendSticker);
    }

    @Override
    public void helpCommand(Update update) {
        MessageModel sample = commandModel.getHelpCommand();
        String chatID = update.getMessage().getChatId().toString();

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage(
                chatID,
                sample.getMessage()
        );
        sendMessage.setParseMode("html");

        botSend(sendMessage, sendSticker);
    }

    @Override
    public void playCommand(Update update) {
        MessageModel sample = commandModel.getPlayCommand();
        InlineKeyboardMarkup gameBoard;
        String chatID = update.getMessage().getChatId().toString();

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setParseMode("html");

        if (apiUsersService.isUser(chatID)) {
            sendMessage.setText(sample.getExistent());
            gameBoard = keyboardService.gameBoard();
        } else {
            sendMessage.setText(sample.getNotExistent());
            gameBoard = keyboardService
                    .webAppBoard(
                            apiUsersService.getURL(),
                            "Javix",
                            chatID
                    );
        }

        sendMessage.setReplyMarkup(gameBoard);
        botSend(sendMessage, sendSticker);
    }

    @Override
    public void deleteCommand(Update update) {
        MessageModel sample = commandModel.getDeleteCommand();
        String chatID = update.getMessage().getChatId().toString();

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setParseMode("html");

        if (apiUsersService.deleteUser(chatID)) {
            sendMessage.setText(sample.getMessage());
        } else {
            sendMessage.setText(sample.getNotExistent());
        }

        botSend(sendMessage, sendSticker);
    }

    @Override
    public void profileCommand(Update update) {
        String chatID = update.getMessage().getChatId().toString();
        ProfileModel profileModel = apiUsersService.getUser(chatID);
        String message;
        MessageModel sample = commandModel.getProfileCommand();

        if (profileModel != null) {
            message = "<b>Ваши данные:\nЛогин: " + profileModel.getLogin() + "\nПароль: " + profileModel.getPassword() + "</b>";
        } else {
            message = sample.getNotExistent();
        }

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage(
                chatID,
                message
        );
        sendMessage.setParseMode("html");

        botSend(sendMessage, sendSticker);
    }

    @Override
    public void statsCommand(Update update) {
        MessageModel sample = commandModel.getStatsCommand();
        String chatID = update.getMessage().getChatId().toString();
        String message = sample.getMessage();

        int ticTacToeScore = 0;
        int numbersScore = 0;

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setParseMode("html");

        StatsModel ticTacToeModel = apiUsersService.getGameStat(chatID, "tic_tac_toe");
        StatsModel numbersModel = apiUsersService.getGameStat(chatID, "2048");

        if (ticTacToeModel != null)
            ticTacToeScore = ticTacToeModel.getScore();

        if (numbersModel != null)
            numbersScore = numbersModel.getScore();

        message += "<b>Крестики-нолики: " + ticTacToeScore + "\n2048: " + numbersScore + "</b>";
        sendMessage.setText(message);

        botSend(sendMessage, sendSticker);
    }

    @Override
    public void anyContentTypeHandler(Update update) {
        MessageModel sample = commandModel.getAnyContentType();
        String chatID = update.getMessage().getChatId().toString();

        SendSticker sendSticker = new SendSticker(
                chatID,
                new InputFile(sample.getSticker())
        );

        SendMessage sendMessage = new SendMessage(chatID, sample.getMessage());
        sendMessage.setChatId(chatID);
        sendMessage.setParseMode("html");

        botSend(sendMessage, sendSticker);
    }
}
