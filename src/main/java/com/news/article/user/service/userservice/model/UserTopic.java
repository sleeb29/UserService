package com.news.article.user.service.userservice.model;

import javax.persistence.*;

@Entity
@Table(name = "user_topic")
public class UserTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    long topicId;


    @Column(name = "name")
    String name;

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
