package com.Javix.JavixWeb.service.impl;

import com.Javix.JavixWeb.models.Player;
import com.Javix.JavixWeb.repo.PlayerRepo;
import com.Javix.JavixWeb.security.AES;
import com.Javix.JavixWeb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Override
    public Map getPlayer(Player player) {
        HashMap<String, String> map = new HashMap<>(2);
        map.put("login", player.getLogin());
        try {
            map.put("password", AES.decrypt(player.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean deleteUser(long tgId) {
        if (playerRepo.existsByTgId(tgId)) {
            playerRepo.deleteByTgId(tgId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean userValidData(String login, String password) {
        boolean cirilicState = Pattern.matches(".*\\p{InCyrillic}.*", password);

        if (login != "" && password != "" && login.length() < 25 && !cirilicState)
            return true;
        return false;
    }
}
