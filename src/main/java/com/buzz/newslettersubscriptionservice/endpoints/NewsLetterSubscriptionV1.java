package com.buzz.newslettersubscriptionservice.endpoints;

import com.buzz.newslettersubscriptionservice.request.UserRequest;
import com.buzz.newslettersubscriptionservice.response.SubmitResponse;
import com.buzz.newslettersubscriptionservice.models.Subscription;
import com.buzz.newslettersubscriptionservice.models.UserInfo;
import com.buzz.newslettersubscriptionservice.processors.NewsLetterProcessor;
import com.buzz.newslettersubscriptionservice.processors.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/buzz/v1/newsletter")
public class NewsLetterSubscriptionV1 {

    @Autowired
    NewsLetterProcessor newsLetterProcessor;

    @Autowired
    UserProcessor userProcessor;

    @GetMapping("/info")
    public String apiInfo() {
        return "Version: 1.0.1";
    }

    @PostMapping("/users")
    public UserInfo addUser(@RequestBody @NotNull UserRequest request) {
        return userProcessor.addNewUser(request);
    }

    @PostMapping("/subscriptions")
    public Subscription addSubscription(@RequestBody @NotNull Subscription subscription) {
        return newsLetterProcessor.addNewSubscription(subscription);
    }

    @GetMapping("/subscriptions/{userId}/email")
    public ResponseEntity<SubmitResponse>  userSubscribedToNewsLetter(@PathVariable("userId") Long userId) {
        return userProcessor.findTheUserNewsLetterStatus(userId);

    }

    @GetMapping("/users/{userId}/subscription")
    public ResponseEntity<SubmitResponse> userSubscriptionStatus(@PathVariable("userId")Long userId) {
        return userProcessor.findTheUserSubscribed(userId);
    }

}

