package com.buzz.newslettersubscriptionservice.dao;

import com.buzz.newslettersubscriptionservice.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription,String> {

    Optional<Subscription> findById(Long id);

}
