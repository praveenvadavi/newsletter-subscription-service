package com.buzz.newslettersubscriptionservice;

import com.buzz.newslettersubscriptionservice.dao.UserRepository;
import com.buzz.newslettersubscriptionservice.endpoints.NewsLetterSubscriptionV1;
import com.buzz.newslettersubscriptionservice.models.Subscription;
import com.buzz.newslettersubscriptionservice.models.UserInfo;
import com.buzz.newslettersubscriptionservice.response.ErrorResponse;
import com.buzz.newslettersubscriptionservice.response.SubmitResponse;
import com.buzz.newslettersubscriptionservice.response.UserNewsLetterEmailResponse;
import com.buzz.newslettersubscriptionservice.response.UserSubscriptionResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@AutoConfigureMockMvc
class NewsletterSubscriptionServiceApplicationTests {

    @MockBean
    UserRepository userRepository;

    @Autowired
    NewsLetterSubscriptionV1 newsLetterSubscriptionV1;

    @Test
    public void testForUserSubscribedToNewsLetter() {

        Optional<UserInfo> userInfo = Optional.of(UserInfo.builder().firstName("User1FirstName").lastName("User1LastName").userSubscribedToNewsLetter(true).build());
        SubmitResponse response = new UserNewsLetterEmailResponse(1L, true);
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.OK);
        Mockito.when(userRepository.findById(anyLong())).thenReturn(userInfo);

        ResponseEntity<SubmitResponse> actualResponseEntity = newsLetterSubscriptionV1.userSubscribedToNewsLetter(1L);
        assertNotNull(actualResponseEntity);
        assertEquals(responseEntity, actualResponseEntity);

    }

    @Test
    public void testUserSubscribedToNewsLetterBadRequest() {

        Optional<UserInfo> userInfo = Optional.empty();
        SubmitResponse response = new ErrorResponse("Given user doesn't exists");
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        Mockito.when(userRepository.findById(anyLong())).thenReturn(userInfo);

        ResponseEntity<SubmitResponse> actualResponseEntity = newsLetterSubscriptionV1.userSubscribedToNewsLetter(1L);
        assertNotNull(actualResponseEntity);
        assertEquals(responseEntity, actualResponseEntity);

    }

    @Test
    public void testForuserSubscriptionStatus() {
        Optional<UserInfo> userInfo = Optional.of(UserInfo.builder().firstName("User1FirstName").lastName("User1LastName").userSubscribedToNewsLetter(true).subscription(new Subscription(4L, "Platinum", 1, 1)).build());
        Optional<UserSubscriptionResponse> subInfo = Optional.of(UserSubscriptionResponse.builder().userId(1L).subscription(userInfo.get().getSubscription()).build());
        SubmitResponse response = new UserSubscriptionResponse(1L, subInfo.get().getSubscription());
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.OK);
        Mockito.when(userRepository.findById(anyLong())).thenReturn(userInfo);

        ResponseEntity<SubmitResponse> actualResponseEntity = newsLetterSubscriptionV1.userSubscriptionStatus(1L);
        assertNotNull(actualResponseEntity);
        assertEquals(responseEntity, actualResponseEntity);

    }

    @Test
    public void testuserSubscriptionStatusBadRequest() {

        Optional<UserInfo> userInfo = Optional.empty();
        SubmitResponse response = new ErrorResponse("Given user doesn't exists");
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        Mockito.when(userRepository.findById(anyLong())).thenReturn(userInfo);

        ResponseEntity<SubmitResponse> actualResponseEntity = newsLetterSubscriptionV1.userSubscriptionStatus(1L);
        assertNotNull(actualResponseEntity);
        assertEquals(responseEntity, actualResponseEntity);

    }

}
