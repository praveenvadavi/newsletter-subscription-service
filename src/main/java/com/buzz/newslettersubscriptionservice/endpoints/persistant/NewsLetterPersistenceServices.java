package com.buzz.newslettersubscriptionservice.endpoints.persistant;


import com.buzz.newslettersubscriptionservice.models.Subscription;
import com.buzz.newslettersubscriptionservice.models.UserInfo;
import com.buzz.newslettersubscriptionservice.processors.NewsLetterProcessor;
import com.buzz.newslettersubscriptionservice.processors.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buzz/v1/newsletter")
public class NewsLetterPersistenceServices {

    @Autowired
    NewsLetterProcessor newsLetterProcessor;

    @Autowired
    UserProcessor userProcessor;


    /*ToDO: Delete below endpoints*/
    @GetMapping("/loadusersdata")
    @ResponseBody
    public List<UserInfo> loadUsersData() {
        return userProcessor.loadUserData();
    }

    @GetMapping("/loadsubscriptionsdata")
    @ResponseBody
    public List<Subscription> loadSubscriptionData() {
        return userProcessor.loadSubscriptionData();
    }


}
