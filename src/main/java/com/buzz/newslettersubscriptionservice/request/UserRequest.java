package com.buzz.newslettersubscriptionservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String emailId;

    @NotNull
    private Boolean userSubscribedToNewsLetter;
    private Long subscriptionId;

}
