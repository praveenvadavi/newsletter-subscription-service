package com.buzz.newslettersubscriptionservice.processors;

import com.buzz.newslettersubscriptionservice.request.UserRequest;
import com.buzz.newslettersubscriptionservice.response.ErrorResponse;
import com.buzz.newslettersubscriptionservice.response.SubmitResponse;
import com.buzz.newslettersubscriptionservice.response.UserNewsLetterEmailResponse;
import com.buzz.newslettersubscriptionservice.response.UserSubscriptionResponse;
import com.buzz.newslettersubscriptionservice.dao.SubscriptionRepository;
import com.buzz.newslettersubscriptionservice.dao.UserRepository;
import com.buzz.newslettersubscriptionservice.models.Subscription;
import com.buzz.newslettersubscriptionservice.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProcessor {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public UserInfo addNewUser(UserRequest userRequest){
        Optional<UserInfo> user = userRepository.findByEmailId(userRequest.getEmailId());
            if(user.isPresent()){
                throw new RuntimeException("The EmailId already exists: "+userRequest.getEmailId());
            }
            UserInfo userInfo = UserInfo.builder()
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .emailId(userRequest.getEmailId())
                    .userSubscribedToNewsLetter(userRequest.getUserSubscribedToNewsLetter())
                    .build();

            if(userRequest.getSubscriptionId()!=null){
                Optional<Subscription> subscription = subscriptionRepository.findById(userRequest.getSubscriptionId());
                if(subscription.isPresent()){
                    userInfo.setSubscription(subscription.get());
                }
                else{
                    throw new RuntimeException("Please enter valid subscriptionId. The Subscription Id doesn't exist: "+userInfo.getUserEnteredSubId());
                }
            }
        return userRepository.save(userInfo);
    }

    public ResponseEntity<SubmitResponse>  findTheUserNewsLetterStatus(Long userId) {
        Optional<UserInfo> user = userRepository.findById(userId);
        try{
            if(user.isPresent()) {
                UserNewsLetterEmailResponse response = UserNewsLetterEmailResponse.builder()
                        .userId(userId)
                        .newsLetterSubscribedStatus(user.get().getUserSubscribedToNewsLetter())
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ErrorResponse("Given user doesn't exists")
                                            , HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse("Internal Server Error: "+e)
                            ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<SubmitResponse> findTheUserSubscribed(Long userId){
        Optional<UserInfo> user = userRepository.findById(userId);
        try{
            if(user.isPresent()){
                UserSubscriptionResponse response = UserSubscriptionResponse.builder()
                        .userId(userId)
                        .subscription(user.get().getSubscription())
                        .build();
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            return new ResponseEntity<>(new ErrorResponse("Given user doesn't exists")
                    , HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse("Internal Server Error: "+e)
                    ,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /*TODO: Delete below implementations*/
    public List<UserInfo> loadUserData(){

        List<UserInfo> users = new ArrayList<>();
        users.add(UserInfo.builder().id(Long.parseLong("1001")).firstName("Praveen").lastName("Vadavi").emailId("pvadavi@gmail.com").userSubscribedToNewsLetter(true).build());
        users.add(UserInfo.builder().id(Long.parseLong("1002")).firstName("Vikram").lastName("Ghatikar").emailId("vghati@gmail.com").userSubscribedToNewsLetter(true).build());
        users.add(UserInfo.builder().id(Long.parseLong("1003")).firstName("Dattaprasad").lastName("Nandanikar").emailId("dnanda@gmail.com").userSubscribedToNewsLetter(false).build());
        users.add(UserInfo.builder().id(Long.parseLong("1004")).firstName("Aditya").lastName("Ghatikar").emailId("aghati@gmail.com").userSubscribedToNewsLetter(false).build());
        users.add(UserInfo.builder().id(Long.parseLong("1005")).firstName("Dattaprasad").lastName("Nandanikar").emailId("dnanda@gmail.com").userSubscribedToNewsLetter(false).build());

        return userRepository.saveAll(users);
    }

    public List<Subscription> loadSubscriptionData(){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(Subscription.builder().id(Long.parseLong("1")).plan("Platinum_1Year").price(300).duration(365).build());
        subscriptions.add(Subscription.builder().id(Long.parseLong("2")).plan("Gold_1Year").price(150).duration(365).build());
        subscriptions.add(Subscription.builder().id(Long.parseLong("3")).plan("Silver_1Year").price(75).duration(365).build());

        return subscriptionRepository.saveAll(subscriptions);
    }

}
