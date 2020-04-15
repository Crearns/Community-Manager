package com.cms.auth.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Creams
 */
public class PermitAllUrl {

    private static final String[] ENDPOINTS = {"/**", "/uaa/removeToken"};


    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS;
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }
}
