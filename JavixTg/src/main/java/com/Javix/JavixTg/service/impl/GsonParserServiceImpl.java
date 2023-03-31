package com.Javix.JavixTg.service.impl;

import com.Javix.JavixTg.modelsJSON.CommandModel;
import com.Javix.JavixTg.service.GsonParserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileReader;

@Service
public class GsonParserServiceImpl implements GsonParserService {

    @Override
    public CommandModel parse() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("./src/main/java/com/Javix/JavixTg/dataJSON/commands.json")) {
            CommandModel sticker = gson.fromJson(reader, CommandModel.class);
            return sticker;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

