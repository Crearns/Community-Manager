package com.cms.gateway.config;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @author Creams
 */
public class RefererRedirectionAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {


    public RefererRedirectionAuthenticationSuccessHandler() {
        super();
        setUseReferer(true);
    }
}
