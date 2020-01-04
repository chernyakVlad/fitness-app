package com.training.fitnessappserver.services;

import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.entity.authentication.JwtToken;
import com.training.fitnessappserver.entity.authentication.LoginUser;
import com.training.fitnessappserver.entity.authentication.RegistrationUser;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationSerivce {
    public User registration(RegistrationUser registrationUser);
    public JwtToken login(LoginUser loginUserForm) throws AuthenticationException, ExpiredJwtException;
    public JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException;
    public void resetPassword(String login, String password, String newPassword);
    public void logout(String accessToken);
}
