package com.ptv.escort.Config;


import com.ptv.escort.User.User;
import com.ptv.escort.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserVerification implements UserDetailsService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        User user = userRepository.findByUsername(username);

//        if(user == null )
//            throw new UsernameNotFoundException("User Not Found " + username);
        return new org.springframework.security.core.userdetails.User("test", "test",
                new ArrayList<>());
//    }
}
}
