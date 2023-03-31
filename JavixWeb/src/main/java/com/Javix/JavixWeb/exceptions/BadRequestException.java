package com.Javix.JavixWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BadRequestException extends RuntimeException {

    public String badRequestPage(Model model) {
        model.addAttribute("error", "404");
        return "error";
    }
}
