package com.ptv.escort.Admin;


import com.ptv.escort.Config.JwtResponse;
import com.ptv.escort.Config.JwtUtil;
import com.ptv.escort.Config.UserVerification;
import com.ptv.escort.User.User;
import com.ptv.escort.User.UserController;
import com.ptv.escort.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {



    @Autowired
    private AuthenticationManager authenticationmanager;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserVerification myUserDetailsService;

    @Autowired
    private JwtUtil jwttokenutil;



    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/admin/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        if (userDetails == null) {
            throw new RuntimeException("User does not exist.");
        }
        User user = adminService.loginAdmin(authenticationRequest);

        final String jwt = jwttokenutil.generateToken("test");
        logger.info(jwt);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @PostMapping("/createescort")
    public ResponseEntity<?> createEscort(@RequestBody EscortDetails escortDetails){
        return ResponseEntity.ok(adminService.createNewEscort(escortDetails));
    }

    @GetMapping("/getallescort")
    public ResponseEntity<?> getListOfAllEscort(){
        return ResponseEntity.ok(adminService.getListOfAllEscorts());
    }


    @GetMapping("/EscortList/WithCategory")
    public ResponseEntity<?> getListOfEscortsWithCategory(@RequestBody String category){
        return ResponseEntity.ok(adminService.getListOfEscortsWithCategory(category));
    }






}
