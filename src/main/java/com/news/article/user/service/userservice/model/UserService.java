package com.news.article.user.service.userservice.model;

import javax.persistence.*;

@Entity
@Table(name = "user_service")
public class UserService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_service_id")
    long userServiceId;

    @Column(name = "user_id")
    long userId;

    @Column(name = "service_id")
    long serviceId;

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

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
}
