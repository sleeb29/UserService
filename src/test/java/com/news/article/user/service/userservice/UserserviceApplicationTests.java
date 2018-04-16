package com.news.article.user.service.userservice;

import com.news.article.user.service.userservice.model.ClientService;
import com.news.article.user.service.userservice.model.Topic;
import com.news.article.user.service.userservice.model.User;
import com.news.article.user.service.userservice.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserserviceApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testAddingOneUser(){

		User user = getDummyUser();
		//Boolean isUser = userService.isUser(user);

		//Assert.assertEquals(true, isUser);

		userService.deleteUser(user);

		//Boolean userIsStillUser = userService.isUser(user);
		//Assert.assertEquals(false, userIsStillUser);

	}

	private User getDummyUser() {

		User user = new User();
		user.setEmailAddress("test@email.com");
		user.setLastName("Last");
		user.setFirstName("First");

		Set<ClientService> clientServiceSet = new HashSet<>();
		user.setClientServiceSet(clientServiceSet);
		Set<Topic> topicSet = new HashSet<>();
		user.setTopicSet(topicSet);

		return user;

	}


}
