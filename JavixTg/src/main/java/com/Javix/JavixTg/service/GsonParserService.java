package com.Javix.JavixTg.service;

import com.Javix.JavixTg.modelsJSON.CommandModel;
import org.springframework.stereotype.Service;

@Service
public interface GsonParserService {
    CommandModel parse();
}
