package com.news.article.user.service.userservice.model;

import javax.persistence.*;

@Entity
@Table(name = "user_client_service")
public class UserClientService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_client_service_id")
    long userClientServiceId;

    @Column(name = "user_id")
    long userId;

    @Column(name = "client_service_id")
    long clientServiceId;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    public long getUserClientServiceId() {
        return userClientServiceId;
    }

    public void setUserClientServiceId(long userClientServiceId) {
        this.userClientServiceId = userClientServiceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getClientServiceId() {
        return clientServiceId;
    }

    public void setClientServiceId(long clientServiceId) {
        this.clientServiceId = clientServiceId;
    }
}
