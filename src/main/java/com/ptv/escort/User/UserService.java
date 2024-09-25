package com.ptv.escort.User;


import com.ptv.escort.Admin.EscortRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordencoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EscortRepository escortReposiroty;


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public User createUser(UserDto user){

        User checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser != null){
            throw new RuntimeException("Email registered to an existing user");
        }
        User checkUsername = userRepository.findByUsername(user.getUsername());
        if (checkUsername != null){
            throw new RuntimeException("Username not available");
        }
            User createUser = new User();
            createUser.setFirstname(user.getFirstName());
            createUser.setLastname(user.getLastName());
            createUser.setEmail(user.getEmail());
            createUser.setUsername(user.getUsername());
//            createUser.setCategory(user.getCategory());
            createUser.setPassword(passwordencoder.encode(user.getPassword()));
//            createUser.setUserRole(user.getUserRole());
//            createUser.setSex(user.getSex());
//            createUser.setRegistrationPayment(false);
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

    public  Optional<User> updateRegistrationPayment(long id) {
        int updateRegistrationPayment = userRepository.updateRegistrationPayment(id);
        Optional<User> user = userRepository.findById(id);
        if (updateRegistrationPayment < 0){
            throw new RuntimeException("Registration payment failed!");
        }
        if (!user.isPresent()){
            throw new RuntimeException("User not available");
        }
        return user;
    }

//    public List<User> findAllUsersByCategory(String category) {
//        return escortReposiroty.findAllByCategory(category);
//    }
}
