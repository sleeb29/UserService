package com.news.article.user.service.userservice.service;

import com.news.article.user.service.userservice.exception.ClientServiceDoesNotExistException;
import com.news.article.user.service.userservice.model.*;
import com.news.article.user.service.userservice.repository.ClientServiceRepository;
import com.news.article.user.service.userservice.repository.UserRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientServiceRepository clientServiceRepository;

    @Transactional(rollbackFor = Exception.class)
    public Boolean createUser(User user) throws ClientServiceDoesNotExistException {

        User existingUser = getExistingUser(user);
        if(existingUser != null){
            return false;
        }

        updateUserClientServiceIds(user);

        userRepository.save(user);
        return true;

    }

    public User updateUser(User user) throws ClientServiceDoesNotExistException {
        User existingUser = getExistingUser(user);

        if(existingUser == null){
            return null;
        }

        HashMap<String, UserTopic> existingUserTopicHashMap = new HashMap<>();
        for(UserTopic userTopic : existingUser.getUserTopicSet()){
            existingUserTopicHashMap.put(userTopic.getName(), userTopic);
        }

        for(UserTopic userTopic : user.getUserTopicSet()){

            if(existingUserTopicHashMap.containsKey(userTopic.getName())){
                userTopic.setTopicId(existingUserTopicHashMap.get(userTopic.getName()).getTopicId());
            }

        }

        updateUserClientServiceIds(user);
        user.setUserId(existingUser.getUserId());
        userRepository.save(user);

        return user;

    }

    public void updateUserClientServiceIds(User user) throws ClientServiceDoesNotExistException {

        Iterable<ClientService> clientServiceIterable = clientServiceRepository.findAll();

        Boolean runStreamInParallel = true;
        Stream<ClientService> clientServiceStream = StreamSupport.stream(clientServiceIterable.spliterator(), runStreamInParallel);

        HashMap<String, List<ClientService>> existingClientServiceHashmap = (HashMap<String, List<ClientService>>)clientServiceStream
                .collect(Collectors.groupingBy(ClientService::getName));

        HashMap<String, ClientService> clientServiceMap = new HashMap<>();
        for(ClientService clientService : user.getClientServiceSet()){
            clientServiceMap.put(clientService.getName(), clientService);
        }

        Set<String> newClientServices  =  user.getClientServiceSet().parallelStream()
                .filter(clientService -> !existingClientServiceHashmap.containsKey(clientService.getName()))
                .map(clientService -> clientService.getName())
                .collect(Collectors.toSet());

        if(newClientServices.size() > 0){
            throw new ClientServiceDoesNotExistException(String.join(",", newClientServices));
        }

        user.setUserClientServiceCollection(new HashSet<>());

        for(Map.Entry<String, List<ClientService>> entry : existingClientServiceHashmap.entrySet()){

            if(clientServiceMap.containsKey(entry.getKey())){
                clientServiceMap.get(entry.getKey()).setServiceId(entry.getValue().get(0).getServiceId());
                clientServiceMap.get(entry.getKey()).setUserDataElement(entry.getValue().get(0).getUserDataElement());
            }

        }

    }

    public User deleteUser(User user){

        User existingUser = getExistingUser(user);

        if(existingUser == null){
            return null;
        }

        userRepository.deleteById(existingUser.getUserId());
        return existingUser;

    }

    public Set<UserTopic> findAllTopics(){

        Iterable<User> userIterable = userRepository.findAll();
        Set<UserTopic> topicSet = new HashSet<>();

        for(User user : userIterable){
            topicSet.addAll(user.getUserTopicSet());
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
            if(persistedUser.getEmailAddress() != null && persistedUser.getEmailAddress().equals(user.getEmailAddress())){
                return persistedUser;
            }
        }

        return null;
    }

    public HashMap<String, HashMap<String, Set<String>>> getServiceDataForTopic(String topicName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        HashMap<String, HashMap<String, Set<String>>> serviceData = new HashMap<>();

        Iterable<User> userIterable = userRepository.findAll();
        for(User user : userIterable){

            Set<ClientService> clientServiceSet = user.getClientServiceSet();

            for(ClientService clientService : clientServiceSet){

                if(!serviceData.containsKey(clientService.getName())){
                    serviceData.put(clientService.getName(), new HashMap<>());
                }

                if(!serviceData.get(clientService.getName()).containsKey(user.getLanguage())){
                    serviceData.get(clientService.getName()).put(user.getLanguage(), new HashSet<>());
                }

                String dataElementName = clientService.getUserDataElement();
                String dataElementValue = PropertyUtils.getSimpleProperty(user, dataElementName).toString();

                Set<String> serviceUsers = serviceData.get(clientService.getName()).get(user.getLanguage());
                serviceUsers.add(dataElementValue);

            }

        }

        return serviceData;

    }

}
