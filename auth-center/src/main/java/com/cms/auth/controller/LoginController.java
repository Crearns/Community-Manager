package com.cms.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Creams
 */

@Controller
public class LoginController {
    @GetMapping(value = {"/login"})
    public String getLoginPage(){
        return "login";
    }


    @GetMapping(value = "/")
    public String getDefaultPage(){
        return "index";
    }
}
