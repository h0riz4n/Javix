package com.Javix.JavixTg.backend;

import lombok.Getter;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientJavix {

    @Getter
    private HttpClient httpClient;

    public HttpClientJavix() {
        httpClient = HttpClients.custom().build();
    }
}