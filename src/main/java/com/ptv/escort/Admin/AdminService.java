package com.ptv.escort.Admin;


import com.ptv.escort.User.User;
import com.ptv.escort.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordencoder;

    @Autowired
    private EscortReposiroty escortReposiroty;


    public User loginAdmin(User user){
        User userbyemail = userRepository.findByEmail(user.getEmail());
        if (!userbyemail.getUserRole().equals("ROLE_ADMIN")) {
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


    public EscortDetails createNewEscort(EscortDetails escortDetails){
        EscortDetails ed = new EscortDetails();
        ed.setEmail(escortDetails.getEmail());
        ed.setCategory(escortDetails.getCategory());
        ed.setPhoneNumber(escortDetails.getPhoneNumber());
        ed.setCategory(escortDetails.getCategory());
        ed.setInstagram(escortDetails.getInstagram());
        ed.setNickName(escortDetails.getNickName());
        ed.setTwitter(escortDetails.getTwitter());
        ed.setLocation(escortDetails.getLocation());
        escortReposiroty.save(ed);
        return ed;
    }

   public List<EscortDetails> getListOfAllEscorts(){
        return escortReposiroty.findAll();
   }
}
