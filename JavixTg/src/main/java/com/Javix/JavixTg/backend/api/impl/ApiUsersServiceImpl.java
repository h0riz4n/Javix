package com.Javix.JavixTg.backend.api.impl;

import com.Javix.JavixTg.backend.api.ApiUsersService;
import com.Javix.JavixTg.backend.HttpClientJavix;
import com.Javix.JavixTg.config.BotConfig;
import com.Javix.JavixTg.modelsJSON.ProfileModel;
import com.Javix.JavixTg.modelsJSON.StatsModel;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiUsersServiceImpl implements ApiUsersService {

    private String URL = "https://76af-5-3-213-252.eu.ngrok.io";
    @Autowired
    private HttpClientJavix httpClientJavix;

    @Autowired
    private BotConfig botConfig;

    @Override
    public boolean isUser(String chatID) {
        String url = URL + "/api/users/isUser?id=" + chatID;
        HttpGet get = new HttpGet(url);

        try {
            CloseableHttpResponse response = httpClientJavix.getHttpClient().execute(get);

            if (response.getStatusLine().getStatusCode() == 200)
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(String chatID) {
        String url = URL + "/api/users/deleteUser";
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("id", chatID));
        urlParameters.add(new BasicNameValuePair("TOKEN", botConfig.getBotToken()));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpClientJavix.getHttpClient().execute(post);

            if (response.getStatusLine().getStatusCode() == 200)
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ProfileModel getUser(String chatID) {
        String url = URL + "/api/users/myProfile";
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("id", chatID));
        urlParameters.add(new BasicNameValuePair("TOKEN", botConfig.getBotToken()));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpClientJavix.getHttpClient().execute(post);

            String json = EntityUtils.toString(response.getEntity(), "UTF-8");

            Gson gson = new Gson();
            ProfileModel request  = gson.fromJson(json, ProfileModel.class);

            if (response.getStatusLine().getStatusCode() == 200) {
                return request ;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public StatsModel getGameStat(String chatID, String game) {
        String url = URL + "/api/stats/getStats/" + game + "?id=" + chatID;
        HttpGet get = new HttpGet(url);

        try {
            CloseableHttpResponse response = httpClientJavix.getHttpClient().execute(get);

            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            Gson gson = new Gson();
            StatsModel request = gson.fromJson(json, StatsModel.class);

            if (response.getStatusLine().getStatusCode() == 200) {
                return request;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getURL() {
        return URL;
    }
}
