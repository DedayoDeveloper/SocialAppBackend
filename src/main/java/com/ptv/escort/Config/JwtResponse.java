package com.ptv.escort.Config;

import com.ptv.escort.User.User;

public class JwtResponse {


    private User user;
    private final String jwt;

    public JwtResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }



    public String getJwt() {
        return jwt;
    }

    public User getUser() {
        return user;
    }
}
