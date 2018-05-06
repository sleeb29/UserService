package com.news.article.user.service.userservice.repository;

import com.news.article.user.service.userservice.model.ClientService;
import com.news.article.user.service.userservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServiceRepository extends CrudRepository<ClientService, Long> {

}
