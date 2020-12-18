package com.ptv.escort.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordencoder;

    @Autowired
    private UserRepository userRepository;




    public User createUser(User user){
        User createUser = new User();
        createUser.setFirstname(user.getFirstname());
        createUser.setLastname(user.getLastname());
        createUser.setEmail(user.getEmail());
        createUser.setUsername(user.getUsername());
        createUser.setPassword(passwordencoder.encode(user.getPassword()));
        userRepository.save(createUser);
        return createUser;
    }









}
