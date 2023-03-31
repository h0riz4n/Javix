package com.Javix.JavixWeb.service;

import com.Javix.JavixWeb.models.Player;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PlayerService {

    Map getPlayer(Player player);

    boolean deleteUser(long tgId);

    boolean userValidData(String login, String password);
}
