package com.buzz.newslettersubscriptionservice.response;

import com.buzz.newslettersubscriptionservice.models.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSubscriptionResponse implements SubmitResponse {
    Long userId;
    Subscription subscription;
}
