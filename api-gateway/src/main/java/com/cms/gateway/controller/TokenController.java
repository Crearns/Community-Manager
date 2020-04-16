package com.cms.gateway.controller;

import com.cms.common.common.SystemClientInfo;
import com.cms.gateway.feign.AuthClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Creams
 */

@RestController
public class TokenController {

    @Autowired
    private AuthClient authClient;

    @PostMapping("/sys/login")
    public Map<String, Object> login(String username, String password) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "password");
        parameters.put("client_id", SystemClientInfo.CLIENT_ID);
        parameters.put("client_secret", SystemClientInfo.CLIENT_SECRET);
        parameters.put("scope", SystemClientInfo.CLIENT_SCOPE);
        parameters.put("username", username);
        parameters.put("password", password);
        return authClient.postAccessToken(parameters);
    }

    @PostMapping("/sys/refresh_token")
    public Map<String, Object> refresh_token(String refresh_token) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "password");
        parameters.put("client_id", SystemClientInfo.CLIENT_ID);
        parameters.put("client_secret", SystemClientInfo.CLIENT_SECRET);
        parameters.put("scope", SystemClientInfo.CLIENT_SCOPE);
        parameters.put("refresh_token", refresh_token);

        return authClient.postAccessToken(parameters);
    }

    @PostMapping("/sys/logout")
    public void logout(String accessToken, @RequestHeader(required = false, value = "Authorization") String token) {
        if (StringUtils.isBlank(accessToken)) {
            if (StringUtils.isNoneBlank(token)) {
                accessToken = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
            }
        }

        System.out.println(accessToken);
        authClient.removeToken(accessToken);
    }
}
