package eshopify.eshopifybackend.controller;

import eshopify.eshopifybackend.Model.ResponseDTO;
import eshopify.eshopifybackend.Model.SignupRequest;
import eshopify.eshopifybackend.services.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class AuthController {

    private static final Logger log= LogManager.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping(value="/signup",produces={"application/json"})
    public ResponseDTO<String> onSignup(@RequestBody SignupRequest signupRequest) {
         log.info("Starting Controller for method {onSignup} Request is :{}",signupRequest);
         return authService.submitSignupData(signupRequest);
    }



}
