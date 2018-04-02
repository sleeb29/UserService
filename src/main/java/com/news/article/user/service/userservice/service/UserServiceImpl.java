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

    public Boolean createUser(User user){

        User existingUser = getExistingUser(user);
        if(existingUser != null){
            return false;
        }

        userRepository.save(user);
        return true;

    }

    public User updateUser(User user){
        User existingUser = getExistingUser(user);

        if(existingUser == null){
            return null;
        }

        long existingUserID = existingUser.getUserId();
        user.setUserId(existingUserID);
        userRepository.save(user);

        return user;

    }

    public User deleteUser(User user){

        User existingUser = getExistingUser(user);

        if(existingUser == null){
            return null;
        }

        userRepository.deleteById(existingUser.getUserId());
        return existingUser;

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

    public User getExistingUser(User user){

        Iterator<User> persistedUsers = userRepository.findAll().iterator();
        while(persistedUsers.hasNext()){
            User persistedUser = persistedUsers.next();
            if(persistedUser.getEmailAddress().equals(user.getEmailAddress())){
                return persistedUser;
            }
        }

        return null;
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

}
