package com.news.article.user.service.userservice.service;

import com.news.article.user.service.userservice.model.ClientService;
import com.news.article.user.service.userservice.model.Topic;
import com.news.article.user.service.userservice.model.User;
import com.news.article.user.service.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if(optionalUser.isPresent()){
            userRepository.deleteById(user.getUserId());
            userRepository.save(user);
        }
    }

    public void deleteUser(User user){
        userRepository.deleteById(user.getUserId());
    }

    public Set<Topic> findAllTopics(){

        Iterable<User> userIterable = userRepository.findAll();
        Set<Topic> topicSet = new HashSet<>();

        for(User user : userIterable){
            topicSet.addAll(user.getTopicList());
        }

        return topicSet;

    }

    public Set<ClientService> findAllClientServices(){

        Iterable<User> userIterable = userRepository.findAll();
        Set<ClientService> clientServiceSet = new HashSet<>();

        for(User user : userIterable){
            clientServiceSet.addAll(user.getClientServiceList());
        }

        return clientServiceSet;

    }

    public boolean isUser(User user){
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        return optionalUser.isPresent();
    }

}
