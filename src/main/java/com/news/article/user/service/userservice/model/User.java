package com.news.article.user.service.userservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "service_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email_address")
    String emailAddress;

    @Column(name = "language")
    String language;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_client_service",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns =
            @JoinColumn(name = "client_service_id", referencedColumnName = "client_service_id"))
    private Set<ClientService> clientServiceSet;

    @OneToMany(mappedBy="user")
    private Collection<UserClientService> userClientServiceCollection;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = UserTopic.class)
    @JoinColumn(name="user_id", referencedColumnName = "user_id", nullable=false)
    private Set<UserTopic> userTopicSet;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLanguage() {
        return language;
    }

    public void setLangauge(String language) {
        this.language = language;
    }

    public Set<ClientService> getClientServiceSet() {
        return clientServiceSet;
    }

    public void setClientServiceSet(Set<ClientService> clientServiceSet) {
        this.clientServiceSet = clientServiceSet;
    }

    public Collection<UserClientService> getUserClientServiceCollection() {
        return userClientServiceCollection;
    }

    public void setUserClientServiceCollection(Collection<UserClientService> userClientServiceCollection) {
        this.userClientServiceCollection = userClientServiceCollection;
    }

    public Set<UserTopic> getUserTopicSet() {
        return userTopicSet;
    }

    public void setUserTopicSet(Set<UserTopic> userTopicSet) {
        this.userTopicSet = userTopicSet;
    }
}
