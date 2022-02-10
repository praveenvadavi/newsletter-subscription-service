package com.buzz.newslettersubscriptionservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
//@Table(name ="users")
public class UserInfo  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "user_id")
    private Long id;

    private String firstName;
    private String lastName;

    @Email
    @NotNull
    private String emailId;

    @NotNull
    private Boolean userSubscribedToNewsLetter;
    private Long userEnteredSubId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_subscription",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "subscription_id", referencedColumnName = "id") })
    private Subscription subscription;
}
