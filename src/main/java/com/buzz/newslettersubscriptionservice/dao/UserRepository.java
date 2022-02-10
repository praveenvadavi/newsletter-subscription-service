package com.buzz.newslettersubscriptionservice.dao;

import com.buzz.newslettersubscriptionservice.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo , Long> {

    Optional<UserInfo> findById(Long id);

    Optional<UserInfo> findByEmailId(String emailId);

    List<UserInfo> findAll();
}
