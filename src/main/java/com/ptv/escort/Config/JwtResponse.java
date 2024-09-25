package com.ptv.escort.Config;

import com.ptv.escort.Category.Category;
import com.ptv.escort.User.User;

public class JwtResponse {


    private User user;
    private Category category;
    private final String jwt;

    public JwtResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
//        this.category = category;
    }



    public String getJwt() {
        return jwt;
    }

    public User getUser() {
        return user;
    }

//    public Category getCategory() {
//        return category;
//    }
}
