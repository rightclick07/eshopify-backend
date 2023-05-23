package eshopify.eshopifybackend.services;


import eshopify.eshopifybackend.Model.ResponseDTO;
import eshopify.eshopifybackend.Model.SignupRequest;

public interface AuthService {

    public ResponseDTO<String> submitSignupData(SignupRequest signupRequest);

}
