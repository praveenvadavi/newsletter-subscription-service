package com.buzz.newslettersubscriptionservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.buzz.newslettersubscriptionservice.dao.SubscriptionRepository;
import com.buzz.newslettersubscriptionservice.dao.UserRepository;
import com.buzz.newslettersubscriptionservice.endpoints.NewsLetterSubscriptionV1;
import com.buzz.newslettersubscriptionservice.processors.UserProcessor;
import com.buzz.newslettersubscriptionservice.response.SubmitResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class NewsletterSubscriptionServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserProcessor userProcessor;

	@MockBean
	SubmitResponse response;

	@MockBean
	UserRepository userRepository;

	@MockBean
	SubscriptionRepository subscriptionRepository;

	@Test
	public void testUserSubscribedToNewsLetter() throws Exception{

		ResponseEntity<SubmitResponse> responseEntity= new ResponseEntity<>(response, HttpStatus.OK);

		Mockito.when(userProcessor.findTheUserNewsLetterStatus(1L)).thenReturn(responseEntity);

		mockMvc.perform(get("/buzz/v1/newsletter/subscriptions/1/email"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

}
