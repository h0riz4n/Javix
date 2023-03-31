package com.Javix.JavixTg.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Getter @Setter
public class BotConfig {
    private String botUserName;
    private String botToken;
    private String webHookPath;

    public BotConfig() {
        botUserName = System.getenv("USER_NAME");
        botToken = System.getenv("TOKEN");
        webHookPath = "https://3810-5-3-213-252.eu.ngrok.io";
    }

    public void setWebHook() {
        String url = "https://api.telegram.org/bot"+ botToken +"/setWebhook?url=" + webHookPath;
        HttpGet get = new HttpGet(url);

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(get);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
