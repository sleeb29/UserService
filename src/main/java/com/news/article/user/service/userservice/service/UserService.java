package com.news.article.user.service.userservice.service;

import com.news.article.user.service.userservice.model.ClientService;
import com.news.article.user.service.userservice.model.Topic;
import com.news.article.user.service.userservice.model.User;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

public interface UserService {

    Boolean createUser(User user);

    User updateUser(User user);

    User deleteUser(User user);

    Set<Topic> findAllTopics();
    Set<ClientService> findAllClientServices();
    HashMap<String, HashMap<String, Set<String>>> getServiceDataForTopic(String topicName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    User getExistingUser(User user);

}
