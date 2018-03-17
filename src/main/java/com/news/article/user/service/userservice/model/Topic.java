package com.news.article.user.service.userservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id")
    long topidId;

    @Column(name = "name")
    String name;

    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_topic",
            joinColumns =
            @JoinColumn(name = "topic_id", referencedColumnName = "topic_id"),
            inverseJoinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    List<User> userList;

    public long getTopidId() {
        return topidId;
    }

    public void setTopidId(long topidId) {
        this.topidId = topidId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
