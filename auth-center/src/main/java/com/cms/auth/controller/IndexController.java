package com.cms.auth.controller;

import com.cms.auth.feign.UserClient;
import com.cms.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Creams
 */

@RestController
public class IndexController {
    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        User user = userClient.findByUsername("aa");
        return user.toString();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "OK";
    }
}
