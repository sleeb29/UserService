package com.news.article.user.service.userservice.model;

import javax.persistence.*;

@Entity
@Table(name = "user_topic")
public class UserTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_topic_id")
    long userServiceId;

    @Column(name = "user_id")
    long userId;

    @Column(name = "topic_id")
    long topidId;

    public long getUserServiceId() {
        return userServiceId;
    }

    public void setUserServiceId(long userServiceId) {
        this.userServiceId = userServiceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTopidId() {
        return topidId;
    }

    public void setTopidId(long topidId) {
        this.topidId = topidId;
    }
}
