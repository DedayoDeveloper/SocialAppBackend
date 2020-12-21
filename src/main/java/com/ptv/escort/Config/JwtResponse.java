package com.ptv.escort.Config;

import com.ptv.escort.User.User;

public class JwtResponse {


    private User userDetail;
    private final String jwt;

    public JwtResponse(String jwt, User user) {
        this.jwt = jwt;
        this.userDetail = userDetail;
    }



    public String getJwt() {
        return jwt;
    }

    public User getUserDetail() {
        return userDetail;
    }
}
