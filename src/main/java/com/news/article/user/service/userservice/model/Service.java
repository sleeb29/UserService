package com.news.article.user.service.userservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service")
public class Service implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    private long serviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "userDataElement")
    private String userDataElement;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_service",
            joinColumns =
            @JoinColumn(name = "service_id", referencedColumnName = "service_id"),
            inverseJoinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private List<User> userList;

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserDataElement() {
        return userDataElement;
    }

    public void setUserDataElement(String userDataElement) {
        this.userDataElement = userDataElement;
    }
}
