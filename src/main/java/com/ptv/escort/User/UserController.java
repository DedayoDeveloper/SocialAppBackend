package com.ptv.escort.User;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptv.escort.Category.Category;
import com.ptv.escort.Category.CategoryName;
import com.ptv.escort.Category.CategoryRepository;
import com.ptv.escort.Config.JwtResponse;
import com.ptv.escort.Config.JwtUtil;
import com.ptv.escort.Config.UserVerification;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;



//@CrossOrigin(origins = "*")
@RestController
@CrossOrigin(origins = "http://ptvescort.com", maxAge = 3600)
public class UserController {


    @Autowired
    private AuthenticationManager authenticationmanager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserVerification myUserDetailsService;

    @Autowired
    private JwtUtil jwttokenutil;

    @Autowired
    private CategoryRepository categoryRepository;



    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        if (userDetails == null) {
            throw new RuntimeException("User does not exist.");
        }
        User user = userService.login(authenticationRequest);
        user.setPassword(null);
        Category category = categoryRepository.findByCategoryName(user.getCategory());
//        try{
//            ObjectMapper mapper = new ObjectMapper();
//            logger.info(mapper.writeValueAsString(user));
//        }catch (Exception e){
//
//        }
        logger.info("user details {}", user);
        final String jwt = jwttokenutil.generateToken(userDetails);
        logger.info(jwt);
        return ResponseEntity.ok(new JwtResponse(jwt,user,category));
    }



    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }



    @GetMapping("/allusers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getListOfAllUsers());
    }

//    @CrossOrigin(origins = "http://ptvescort.com", maxAge = 3600)
//    @PostMapping("/user/by/category")
//    public ResponseEntity<?> getAllEscortsByUserChosenCategory(@RequestBody String category){
//        return ResponseEntity.ok(userService.findAllUsersByCategory(category));
//    }


    @GetMapping("/listallcategory")
    public ResponseEntity<?> listAllCategories(){
        logger.info("WE GOT HERE!!!!");
        return ResponseEntity.ok(CategoryName.values());
    }


    @PostMapping("/update/registration/payment/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable("id") long id){
        return ResponseEntity.ok(userService.updateRegistrationPayment(id));
    }

}
