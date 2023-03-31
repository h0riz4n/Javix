package com.Javix.JavixTg.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
public interface KeyboardService {

    InlineKeyboardMarkup webAppBoard(String url, String name, String chatID);

    InlineKeyboardMarkup gameBoard();
}
