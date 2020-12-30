package com.ptv.escort.Admin;


import com.ptv.escort.Category.CategoryName;
import com.ptv.escort.Response.PaymentResponse;
import com.ptv.escort.User.User;
import com.ptv.escort.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordencoder;

    @Autowired
    private EscortRepository escortReposiroty;

    @Autowired
    private EPDRepository epdRepository;


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


    public EscortDetails createNewEscort(String name, String location, String phoneNumber, String email
            , CategoryName category, String description, String fileName){
        EscortDetails ed = new EscortDetails();
        ed.setEmail(email);
        ed.setCategory(category);
        ed.setPhoneNumber(phoneNumber);
        ed.setName(name);
        ed.setPhotos(fileName);
        ed.setDelFlag("N");
        ed.setLocation(location);
        ed.setDescription(description);
        escortReposiroty.save(ed);
        return ed;
    }

   public List<EscortDetails> getListOfAllEscorts(){
        return escortReposiroty.findAll();
   }

    public List<EscortDetails> getListOfEscortsWithCategory(CategoryName category) {
        List<EscortDetails> escorts = escortReposiroty.findAllByCategory(category);
        escorts.forEach(escortDetails -> {
             escortDetails.setPhoneNumber(null);
             escortDetails.setEmail(null);
             escortDetails.setName(null);
        });
        return escorts;
    }

    public int deleteEscort(long id) {
        int deleteEscort = escortReposiroty.softDelete(id);
        if (deleteEscort < 1){
            throw new RuntimeException("Unable to delete user");
        }
        return deleteEscort;
    }

    public EscortDetails getEscortDetails(long id) {
        return escortReposiroty.findEscortDetailsById(id);
    }

    public PaymentResponse confirmUserEscortPaymentDetails(long userId,long escortId) {
        EscortPaymentDetails escortPaymentDetails = new EscortPaymentDetails();
        EscortDetails getEscortDetails = escortReposiroty.findEscortDetailsById(escortId);
        EscortPaymentDetails getDetails = epdRepository.findByUserAndEscort(userId, escortId);
        if (getDetails == null){
            escortPaymentDetails.setUser(userId);
            escortPaymentDetails.setEscort(escortId);
            escortPaymentDetails.setPaymentConfirmed(false);
            epdRepository.save(escortPaymentDetails);
        }
        if (getDetails.isPaymentConfirmed() == false){
            getEscortDetails.setEmail(null);
            getEscortDetails.setPhoneNumber(null);
            return new PaymentResponse(getEscortDetails, getDetails);
        } else

        return new PaymentResponse(getEscortDetails, getDetails);
    }

    public String updateEscortPayment(long idOfUser, long idOfEscort) {
        int update = epdRepository.updateEscortPayment(idOfUser,idOfEscort);
        if (update < 1){
            throw new RuntimeException("Payment update failed, Please try again later!");
        }
        return "Payment update successfull!";
    }
}
