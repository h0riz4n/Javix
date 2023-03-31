package com.Javix.JavixTg.modelsJSON;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Data
public class CommandModel {

    private MessageModel startCommand;
    private MessageModel playCommand;
    private MessageModel helpCommand;
    private MessageModel statsCommand;
    private MessageModel profileCommand;
    private MessageModel deleteCommand;
    private MessageModel anyContentType;

}
