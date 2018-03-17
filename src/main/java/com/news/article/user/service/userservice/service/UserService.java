package com.news.article.user.service.userservice.service;

import com.news.article.user.service.userservice.model.ClientService;
import com.news.article.user.service.userservice.model.Topic;
import com.news.article.user.service.userservice.model.User;

import java.util.Set;

public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    Set<Topic> findAllTopics();
    Set<ClientService> findAllClientServices();

    boolean isUser(User user);

}
