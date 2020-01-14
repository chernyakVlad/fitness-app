package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.JwtToken;
import com.training.fitnessappserver.repository.TokenRepository;
import com.training.fitnessappserver.service.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Service;

@Service
public class TokenStoreImpl implements TokenStore {

    private TokenRepository tokenRepository;

    @Autowired
    public TokenStoreImpl(TokenRepository tokenRepository)  {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void storeToken(JwtToken token) {
        tokenRepository.save(token);
    }

    @Override
    public void removeToken(String accessToken) {
        tokenRepository.deleteById(accessToken);
    }

    @Override
    public JwtToken checkToken(String accessToken) {
        return tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new InvalidTokenException("Token has been invalid"));
    }
}
