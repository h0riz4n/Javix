package com.Javix.JavixTg.modelsJSON;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class MessageModel {

    private String message;
    private String sticker;
    private String existent;
    private String notExistent;
}
