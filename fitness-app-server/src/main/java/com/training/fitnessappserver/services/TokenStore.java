package com.training.fitnessappserver.services;

import com.training.fitnessappserver.entity.JwtToken;

public interface TokenStore {
    public void storeToken(JwtToken token);
    public void removeToken(String accessToken);
    public JwtToken checkToken(String accessToken);
}
