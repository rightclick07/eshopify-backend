package eshopify.eshopifybackend.services.impl;

import eshopify.eshopifybackend.Entity.UserEntity;
import eshopify.eshopifybackend.Model.ResponseDTO;
import eshopify.eshopifybackend.Model.SignupRequest;
import eshopify.eshopifybackend.constants.AppConstants;
import eshopify.eshopifybackend.controller.AuthController;
import eshopify.eshopifybackend.repository.UserRepository;
import eshopify.eshopifybackend.services.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log= LogManager.getLogger(AuthController.class);

    @Autowired
    UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    AppConstants appConstants;



    @Override
    public ResponseDTO<String> submitSignupData(SignupRequest signupRequest) {
        log.info("Starting Service for method {submitSignupData} Request is :{}",signupRequest);

        ResponseDTO<String> responseDTO=new ResponseDTO<>();
        UserEntity userEntity = new UserEntity();
        try{
         if(signupRequest.getUserId()!=null) {
             String rawPassword = signupRequest.getPassword()!=null?signupRequest.getPassword():"";
             String hashedPassword = passwordEncoder.encode(rawPassword);

             userEntity.setUserId(signupRequest.getUserId());
             userEntity.setUsername(signupRequest.getUsername()!=null?signupRequest.getUsername():"");
             userEntity.setFullName(signupRequest.getFullName()!=null?signupRequest.getFullName():"");
             userEntity.setEmail(signupRequest.getEmail()!=null?signupRequest.getEmail():"");
             userEntity.setPassword(hashedPassword!=null?hashedPassword:"");
             userEntity.setAddress(signupRequest.getAddress()!=null?signupRequest.getAddress():"");
             userEntity.setMobileNumber(signupRequest.getMobileNumber()!=null?signupRequest.getMobileNumber():"");
             userEntity.setIsActive(signupRequest.getActive() != null ? signupRequest.getActive() : true);
             userEntity.setUserRole(signupRequest.getUserRole());
             userEntity.setCreatedAt(signupRequest.getCreatedAt()!=null?signupRequest.getCreatedAt():  new Timestamp(System.currentTimeMillis()));
             userEntity.setUpdatedAt(signupRequest.getUpdatedAt()!=null?signupRequest.getUpdatedAt():new Timestamp(System.currentTimeMillis()));
         }
          userRepository.save(userEntity);

            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setMessage(appConstants.successMsg);
            responseDTO.setPayload(appConstants.signup_success);

        } catch (Exception e){
            e.printStackTrace();
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(appConstants.failureMsg);
            responseDTO.setPayload(null);
        }
        return responseDTO;
    }
}
