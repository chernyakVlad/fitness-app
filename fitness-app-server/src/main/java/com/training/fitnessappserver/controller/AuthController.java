package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.entity.authentication.JwtToken;
import com.training.fitnessappserver.entity.authentication.LoginUser;
import com.training.fitnessappserver.entity.authentication.RegistrationUser;
import com.training.fitnessappserver.exception.UserValidationException;
import com.training.fitnessappserver.services.AuthenticationSerivce;
import com.training.fitnessappserver.services.TokenStore;
import com.training.fitnessappserver.services.impl.AuthenticationServiceImpl;
import com.training.fitnessappserver.services.impl.TokenStoreImpl;
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
    public ResponseEntity<User> signUp(@RequestBody RegistrationUser rUser, BindingResult bindingResult) {
        regUserValidator.validate(rUser, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authService.registration(rUser));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody LoginUser lUser, BindingResult bindingResult) {
        loginUserValidator.validate(lUser, bindingResult);
        if(bindingResult.hasErrors()) {
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authService.login(lUser));
    }

    @PostMapping(value = "/changePassword")
    public ResponseEntity<?> resetPassword(@RequestParam String password, @RequestParam String newPassword) {
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
