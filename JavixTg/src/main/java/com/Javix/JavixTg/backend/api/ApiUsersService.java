package com.Javix.JavixTg.backend.api;

import com.Javix.JavixTg.modelsJSON.ProfileModel;
import com.Javix.JavixTg.modelsJSON.StatsModel;
import org.springframework.stereotype.Service;

@Service
public interface ApiUsersService {

    boolean isUser(String chatID);

    boolean deleteUser(String chatID);

    ProfileModel getUser(String chatID);

    StatsModel getGameStat(String chatID, String game);

    String getURL();
}
