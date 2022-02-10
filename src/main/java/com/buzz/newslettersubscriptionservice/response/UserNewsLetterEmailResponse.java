package com.buzz.newslettersubscriptionservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserNewsLetterEmailResponse implements SubmitResponse{
    Long userId;
    Boolean newsLetterSubscribedStatus;

}
