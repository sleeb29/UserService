package com.news.article.user.service.userservice.controller;

import com.news.article.user.service.userservice.model.Topic;
import com.news.article.user.service.userservice.model.User;
import com.news.article.user.service.userservice.repository.UserRepository;
import com.news.article.user.service.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class WebController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/topic/", method = RequestMethod.GET)
    public ResponseEntity<List<Topic>> getAllTopics() {
        return null;
        /*if(topicList.isEmpty()){
            return new ResponseEntity<List<Topic>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Topic>>(topicList, HttpStatus.OK);*/
    }

    @RequestMapping(value = "/service/", method = RequestMethod.GET)
    public ResponseEntity<List<Service>> getAllServices() {
        return null;
        /*if(serviceList.isEmpty()){
            return new ResponseEntity<List<Service>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Service>>(serviceList, HttpStatus.OK);*/
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> postUser(@RequestBody User user) {
        return null;
        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);*/
    }

    @RequestMapping(value = "/user/", method = RequestMethod.PUT)
    public ResponseEntity<Void> putUser(@RequestBody User user) {
        return null;
        /*if (!userService.isUserExist(user)) {
            System.out.println("This user does not exist " + user.getName() + ".");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);*/
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestBody User user) {
        return null;
        /*System.out.println("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);*/
    }

}