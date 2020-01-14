package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.dto.LoginRequestModel;
import com.training.fitnessappserver.dto.RegisterRequestModel;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.entity.JwtToken;
import com.training.fitnessappserver.exception.UserValidationException;
import com.training.fitnessappserver.service.AuthenticationSerivce;
import com.training.fitnessappserver.service.TokenStore;
import com.training.fitnessappserver.service.impl.AuthenticationServiceImpl;
import com.training.fitnessappserver.service.impl.TokenStoreImpl;
import com.training.fitnessappserver.validators.LoginUserValidator;
import com.training.fitnessappserver.validators.RegistrationUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private AuthenticationSerivce authService;
    private RegistrationUserValidator regUserValidator;
    private LoginUserValidator loginUserValidator;
    private TokenStore tokenStore;

    @Autowired
    public AuthController(AuthenticationServiceImpl authenticationSerivce,
                          RegistrationUserValidator regUserValidator,
                          LoginUserValidator loginUserValidator,
                          TokenStoreImpl tokenStore) {
        this.regUserValidator = regUserValidator;
        this.loginUserValidator = loginUserValidator;
        this.authService = authenticationSerivce;
        this.tokenStore = tokenStore;
    }

    @PostMapping(value="/register")
    public ResponseEntity<User> signUp(@RequestBody RegisterRequestModel registerRequestModel, BindingResult bindingResult) {
        regUserValidator.validate(registerRequestModel, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authService.registration(registerRequestModel));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody LoginRequestModel lRequest, BindingResult bindingResult) {
        loginUserValidator.validate(lRequest, bindingResult);
        if(bindingResult.hasErrors()) {
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authService.login(lRequest));
    }

    @PostMapping(value = "/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String password, @RequestParam String newPassword) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        authService.resetPassword(login, password, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<JwtToken> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

    @PostMapping(value = "/logout")
    public void logout(@RequestBody JwtToken accessToken) {
        tokenStore.removeToken(accessToken.getAccessToken());
    }
}