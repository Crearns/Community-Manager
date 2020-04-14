package com.cms.gateway.controller;

import com.cms.common.common.CredentialType;
import com.cms.common.common.SystemClientInfo;
import com.cms.gateway.feign.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.PostMapping;
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
}
