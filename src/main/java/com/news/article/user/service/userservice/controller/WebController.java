package com.news.article.user.service.userservice.controller;

import com.news.article.user.service.userservice.model.ClientService;
import com.news.article.user.service.userservice.model.Topic;
import com.news.article.user.service.userservice.model.User;
import com.news.article.user.service.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

@RestController
public class WebController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public ResponseEntity<Set<Topic>> getAllTopics() {

        Set<Topic> topicSet = userService.findAllTopics();
        if(topicSet.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(topicSet, HttpStatus.OK);

    }

    @RequestMapping(value = "/serviceData", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, HashMap<String, Set<String>>>> getTopicServiceData(@RequestBody String topicName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        HashMap<String, Set<String>> serviceData = userService.getServiceDataForTopic(topicName);

        if(serviceData.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        HashMap<String, HashMap<String, Set<String>>> serviceDataMap = new HashMap<>();
        serviceDataMap.put("serviceData", serviceData);

        return new ResponseEntity<>(serviceDataMap, HttpStatus.OK);

    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> postUser(@RequestBody User user) {

        if (userService.isUser(user)) {
            System.out.println("A User with name " + user.getFirstName() + " " + user.getLastName() + " already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.createUser(user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<Void> putUser(@RequestBody User user) {

        if (!userService.isUser(user)) {
            System.out.println("This user does not exist " + user.getFirstName() + " " + user.getLastName() + ".");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.updateUser(user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        System.out.println("Fetching & Deleting User with name " + user.getFirstName() + " " + user.getLastName());

        if (!userService.isUser(user)) {
            System.out.println("Unable to delete. User with name " + user.getFirstName() + " " + user.getLastName());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}