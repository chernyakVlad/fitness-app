package com.training.fitnessappserver.service;

import com.training.fitnessappserver.dto.LoginRequestModel;
import com.training.fitnessappserver.dto.RegisterRequestModel;
import com.training.fitnessappserver.entity.user.User;
import com.training.fitnessappserver.entity.JwtToken;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationSerivce {
    User registration(RegisterRequestModel registerRequestModel);

    JwtToken login(LoginRequestModel loginRequestModel) throws AuthenticationException, ExpiredJwtException;

    JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException;

    void resetPassword(String login, String password, String newPassword);

    void logout(String accessToken);
}
