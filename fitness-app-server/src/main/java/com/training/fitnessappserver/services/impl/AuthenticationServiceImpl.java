package com.training.fitnessappserver.services.impl;

import com.training.fitnessappserver.dto.LoginRequestModel;
import com.training.fitnessappserver.dto.RegisterRequestModel;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.entity.JwtToken;
import com.training.fitnessappserver.security.JwtTokenProvider;
import com.training.fitnessappserver.services.AuthenticationSerivce;
import com.training.fitnessappserver.services.TokenStore;
import com.training.fitnessappserver.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationSerivce {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private UserDetailsService userDetailsService;
    private TokenStore tokenSotre;
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationServiceImpl(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
                                     AuthenticationManager authenticationManager,
                                     BCryptPasswordEncoder bCryptPasswordEncoder,
                                     JwtTokenProvider jwtTokenProvider,
                                     UserServiceImpl userServiceImpl,
                                     TokenStoreImpl tokenStore) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = jwtTokenProvider;
        this.userService = userServiceImpl;
        this.tokenSotre = tokenStore;
    }

    @Override
    public User registration(RegisterRequestModel registerRequestModel) {
        User newUser = new User();
        User defaultUser = userService.findByLogin(User.DEFAULT_USER_LOGIN);
        newUser.setLogin(registerRequestModel.getLogin());
        newUser.setPassword(registerRequestModel.getPassword());
        return userService.save(newUser);
    }

    @Override
    public JwtToken login(LoginRequestModel loginRequest) throws AuthenticationException, ExpiredJwtException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        final String refreshToken = tokenProvider.generateRefreshToken(authentication);
        JwtToken jwtToken = new JwtToken(token, refreshToken);
        tokenSotre.storeToken(jwtToken);
        return jwtToken;
    }

    @Override
    public JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException {
        String username = tokenProvider.getUsernameFromToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (tokenProvider.validateToken(refreshToken, userDetails)) {
            final Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            final String newRefreshToken = tokenProvider.generateRefreshToken(authentication);
            return new JwtToken(token, newRefreshToken);
        }
        return null;
    }

    @Override
    public void resetPassword(String login, String password, String newPassword) {
        User user = userService.findByLogin(login);
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            userService.resetPassword(user, bCryptPasswordEncoder.encode(newPassword));
        }
    }

    @Override
    public void logout(String accessToken) {
        tokenSotre.removeToken(accessToken);
    }
}

