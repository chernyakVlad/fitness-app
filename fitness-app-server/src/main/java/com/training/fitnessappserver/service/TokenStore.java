package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.JwtToken;

public interface TokenStore {
    void storeToken(JwtToken token);

    void removeToken(String accessToken);

    JwtToken checkToken(String accessToken);
}
