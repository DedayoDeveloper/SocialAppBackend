package com.ptv.escort.User;


import com.ptv.escort.Admin.EscortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordencoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EscortRepository escortReposiroty;




    public User createUser(User user){
        User createUser = new User();
        createUser.setFirstname(user.getFirstname());
        createUser.setLastname(user.getLastname());
        createUser.setEmail(user.getEmail());
        createUser.setUsername(user.getUsername());
        createUser.setCategory(user.getCategory());
        createUser.setPassword(passwordencoder.encode(user.getPassword()));
        createUser.setUserRole(user.getUserRole());
        createUser.setSex(user.getSex());
        userRepository.save(createUser);
        return createUser;
    }



    public User login(User user){
        User userbyemail = userRepository.findByEmail(user.getEmail());
        if (userbyemail == null) {
            throw new RuntimeException("User does not exist.");
        }
        boolean passwordcheck = passwordencoder.matches(user.getPassword(), userbyemail.getPassword());
        if (passwordcheck == false) {
            throw new RuntimeException("Password mismatch.");
        }
//        int authorized = userbyemail.getEnabled();
//        if (authorized == 0 || authorized == 2) {
//            throw new RuntimeException("Please contact your supervisor to authorize your profile.");
//        }
//        List<MapUserLocation> getUserLocationDetails = mapUserRepo.findByEmail(email);
        return userbyemail;
    }


    public List<User> getListOfAllUsers() {
        return userRepository.findAll();
    }

//    public List<User> findAllUsersByCategory(String category) {
//        return escortReposiroty.findAllByCategory(category);
//    }
}
