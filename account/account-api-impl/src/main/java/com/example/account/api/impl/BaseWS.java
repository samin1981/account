package com.example.account.api.impl;

import com.example.account.comon.AccountException;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseWS {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AccountException.class)
    @ResponseBody
    public void handleAccountError(AccountException ex, ServletResponse servletResponse) throws IOException {

        Gson gson = new Gson();
        servletResponse.setContentType(MediaType.APPLICATION_JSON.toString());
        servletResponse.setCharacterEncoding("UTF-8");
        ((HttpServletResponse) servletResponse).setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        servletResponse.getWriter().write(gson.toJson(ex.getAccountFault()));
    }

}
