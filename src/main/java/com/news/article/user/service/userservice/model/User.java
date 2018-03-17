package com.news.article.user.service.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_client_service",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns =
            @JoinColumn(name = "client_service_id", referencedColumnName = "client_service_id"))
    private Set<ClientService> clientServiceSet;

    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_topic",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns =
            @JoinColumn(name = "topic_id", referencedColumnName = "topic_id"))
    private Set<Topic> topicSet;

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

    public Set<ClientService> getClientServiceSet() {
        return clientServiceSet;
    }

    public void setClientServiceSet(Set<ClientService> clientServiceSet) {
        this.clientServiceSet = clientServiceSet;
    }

    public Set<Topic> getTopicSet() {
        return topicSet;
    }

    public void setTopicSet(Set<Topic> topicSet) {
        this.topicSet = topicSet;
    }
}
