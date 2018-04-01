package com.news.article.user.service.userservice.service;

import com.news.article.user.service.userservice.model.ClientService;
import com.news.article.user.service.userservice.model.Topic;
import com.news.article.user.service.userservice.model.User;
import com.news.article.user.service.userservice.repository.UserRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

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
            topicSet.addAll(user.getTopicSet());
        }

        return topicSet;

    }

    public Set<ClientService> findAllClientServices(){

        Iterable<User> userIterable = userRepository.findAll();
        Set<ClientService> clientServiceSet = new HashSet<>();

        for(User user : userIterable){
            clientServiceSet.addAll(user.getClientServiceSet());
        }

        return clientServiceSet;

    }

    public boolean isUser(User user){

        Iterator<User> persistedUsers = userRepository.findAll().iterator();
        while(persistedUsers.hasNext()){
            if(persistedUsers.next().getEmailAddress().equals(user.getEmailAddress())){
                return true;
            }
        }

        return false;
    }

    public HashMap<String, Set<String>> getServiceDataForTopic(String topicName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        HashMap<String, Set<String>> serviceData = new HashMap<>();

        Set<String> userIds = new HashSet<>();

        Iterable<User> userIterable = userRepository.findAll();
        for(User user : userIterable){

            Set<ClientService> clientServiceSet = user.getClientServiceSet();

            for(ClientService clientService : clientServiceSet){

                if(!serviceData.containsKey(clientService.getName())){
                    serviceData.put(clientService.getName(), new HashSet<>());
                }

                String dataElementName = clientService.getUserDataElement();
                String dataElementValue = PropertyUtils.getSimpleProperty(user, dataElementName).toString();

                Set<String> serviceUsers = serviceData.get(clientService.getName());
                serviceUsers.add(dataElementValue);

            }

        }

        return serviceData;

    }

    public Set<String> getAllUserIdsSubscribedToTopic(String topicName){

        Set<String> userIds = new HashSet<>();

        Iterable<User> userIterable = userRepository.findAll();

        for(User user : userIterable){
            Set<Topic> topicSet = user.getTopicSet();
            Set<String> topicSetNames = topicSet.stream().map(topic -> topic.getName()).collect(Collectors.toSet());
            if(topicSetNames.contains(topicName)){
                userIds.add(user.getEmailAddress());
            }
        }

        return userIds;

    }

}
