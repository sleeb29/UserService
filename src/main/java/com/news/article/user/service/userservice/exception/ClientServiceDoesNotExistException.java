package com.news.article.user.service.userservice.exception;

public class ClientServiceDoesNotExistException extends Exception {

    public ClientServiceDoesNotExistException() {}

    public ClientServiceDoesNotExistException(String service)
    {
        super("Service(s) do not exist:" + service);
    }

}