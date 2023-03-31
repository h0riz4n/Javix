package com.Javix.JavixTg.service.impl;

import com.Javix.JavixTg.service.KeyboardService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardServiceImpl implements KeyboardService {

    @Override
    public InlineKeyboardMarkup webAppBoard(String url, String name, String chatID) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> Buttons = new ArrayList<InlineKeyboardButton>();
        InlineKeyboardButton webApp = new InlineKeyboardButton(name);
        String mainURL = url + "/" + chatID;
        WebAppInfo webAppInfo = new WebAppInfo(mainURL);
        webApp.setWebApp(webAppInfo);
        Buttons.add(webApp);
        keyboard.add(Buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup gameBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> Buttons = new ArrayList<InlineKeyboardButton>();

        InlineKeyboardButton ticTacToe = new InlineKeyboardButton("Крестики-нолики");
        ticTacToe.setCallbackData("TicTacToeGame");
        Buttons.add(ticTacToe);

        InlineKeyboardButton numbers = new InlineKeyboardButton("2048");
        numbers.setCallbackData("NumbersGame");
        Buttons.add(numbers);

        keyboard.add(Buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}
