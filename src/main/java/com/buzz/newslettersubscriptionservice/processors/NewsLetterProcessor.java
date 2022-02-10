package com.buzz.newslettersubscriptionservice.processors;

import com.buzz.newslettersubscriptionservice.dao.SubscriptionRepository;
import com.buzz.newslettersubscriptionservice.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsLetterProcessor {

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    public Subscription addNewSubscription(Subscription subscription){
       return subscriptionRepository.save(subscription);
    }

}
