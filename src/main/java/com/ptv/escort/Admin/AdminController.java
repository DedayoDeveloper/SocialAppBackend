package com.ptv.escort.Admin;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ptv.escort.Category.Category;
import com.ptv.escort.Category.CategoryName;
import com.ptv.escort.Category.CategoryService;
import com.ptv.escort.Config.JwtResponse;
import com.ptv.escort.Config.JwtUtil;
import com.ptv.escort.Config.UserVerification;
import com.ptv.escort.User.User;
import com.ptv.escort.User.UserController;
import com.ptv.escort.Utils.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;

//import org.omg.CORBA.portable.InputStream;


@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AdminController {


//    @Value("${uploadDir}")
//    private String uploadDir;

    @Autowired
    private AuthenticationManager authenticationmanager;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserVerification myUserDetailsService;

    @Autowired
    private JwtUtil jwttokenutil;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    ServletContext servletContext;



    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    @RequestMapping(value = "/admin/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        if (userDetails == null) {
            throw new RuntimeException("User does not exist.");
        }
        User user = adminService.loginAdmin(authenticationRequest);

        Category category = null;

        final String jwt = jwttokenutil.generateToken(userDetails);
        logger.info(jwt);
        return ResponseEntity.ok(new JwtResponse(jwt,user,category));
    }


//    @CrossOrigin(origins = "http://ptvescort.com", maxAge = 3600)
//    @PostMapping("/createescort")
//    public ResponseEntity<?> createEscort(@RequestBody EscortDetails escortDetails){
//        return ResponseEntity.ok(adminService.createNewEscort(escortDetails));
//    }



    @GetMapping("/getallescort")
    public ResponseEntity<?> getListOfAllEscort(){
        return ResponseEntity.ok(adminService.getListOfAllEscorts());
    }



    @PostMapping("/EscortList/WithCategory")
    public ResponseEntity<?> getListOfEscortsWithCategory(@RequestBody Category category){
        return ResponseEntity.ok(adminService.getListOfEscortsWithCategory(category.getCategoryName()));
    }


    @RequestMapping(value = "/createescort", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public EscortDetails saveUser(@RequestParam String name,
                                  @RequestParam String location,
                                  @RequestParam String phoneNumber,
                                  @RequestParam String email,
                                  @RequestParam CategoryName category,
                                  @RequestParam String description,
                                  @RequestParam("image") MultipartFile multipartFile, HttpSession session) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());



        EscortDetails savedUser = adminService.createNewEscort(name,location,phoneNumber,email,category,description,fileName);

//        logger.info("upload directory {}", uploadDir);

        String uploadDir = "./src/main/resources/user-photos/" + savedUser.getId();

//        String uploadDir = new ClassPathResource("/user-photos/" + savedUser.getId()).toString();
        logger.info("classpath {}", uploadDir);

        FileUploadUtil.saveFile(uploadDir , fileName, multipartFile);

        return savedUser;
    }

//    @CrossOrigin(origins = "http://ptvescort.com", maxAge = 3600)
//    @GetMapping("/list/all/category/foradmin")
//    public ResponseEntity<?> listAllCategories(){
//        return ResponseEntity.ok(Category.values());
//    }




    @DeleteMapping("/delete/escort/{id}")
    public int deleteEscort(@PathVariable("id") long id){
        return adminService.deleteEscort(id);
    }


    @PostMapping("/get/escort/details/{id}")
    public ResponseEntity<?> getEscortDetails(@PathVariable("id") long id){
        return ResponseEntity.ok(adminService.getEscortDetails(id));

    }



    @PostMapping("/confirm/paymentdetails/for/escort")
    public ResponseEntity<?> getPaymentDetails(@RequestBody ObjectNode user){
        logger.info("WE GOT HERE!!!");
        JsonNode userId = user.get("user");
        long idOfUser = userId.asLong();

        JsonNode escortId = user.get("escort");
        long idOfEscort = escortId.asLong();
     return ResponseEntity.ok(adminService.confirmUserEscortPaymentDetails(idOfUser,idOfEscort));

    }


    @PostMapping("/update/escort/payment")
    public ResponseEntity<?> updatePaymentDetails(@RequestBody ObjectNode user){
        logger.info("WE GOT HERE!!!");
        JsonNode userId = user.get("user");
        long idOfUser = userId.asLong();

        JsonNode escortId = user.get("escort");
        long idOfEscort = escortId.asLong();
        return ResponseEntity.ok(adminService.updateEscortPayment(idOfUser,idOfEscort));
    }

    @PostMapping("/add/categories")
    public ResponseEntity<?> addCategory(){
        return ResponseEntity.ok(categoryService.categories());
    }


    @PostMapping(value = "/get/category/details")
    public ResponseEntity<?> getCategoryDetails(@RequestBody Category categoryName){
        return ResponseEntity.ok(categoryService.getCategoryDetails(categoryName.getCategoryName()));
    }

//

    @RequestMapping(value = "/user-photos/{id}/{photos}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id, @PathVariable("photos") String photos) throws IOException {

        InputStream in = servletContext.getResourceAsStream("/user-photos/" + id + "/" + photos);
        logger.info("in {}", in);
        ClassPathResource imgFile = new ClassPathResource("/user-photos/" + id + "/" + photos);
//        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        byte[] bytes = IOUtils.toByteArray(imgFile.getInputStream());


        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

}
