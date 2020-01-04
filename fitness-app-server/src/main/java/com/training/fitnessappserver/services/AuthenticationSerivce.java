package com.training.fitnessappserver.services;

import com.training.fitnessappserver.dto.LoginRequestModel;
import com.training.fitnessappserver.dto.RegisterRequestModel;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.entity.JwtToken;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationSerivce {
    public User registration(RegisterRequestModel registerRequestModel);
    public JwtToken login(LoginRequestModel loginRequestModel) throws AuthenticationException, ExpiredJwtException;
    public JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException;
    public void resetPassword(String login, String password, String newPassword);
    public void logout(String accessToken);
}
