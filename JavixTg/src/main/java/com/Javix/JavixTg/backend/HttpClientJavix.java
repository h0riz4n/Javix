package com.Javix.JavixTg.backend;

import lombok.Getter;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientJavix {

    @Getter
    private CloseableHttpClient httpClient;

    public HttpClientJavix() {
        httpClient = HttpClients.custom().build();
    }

}
