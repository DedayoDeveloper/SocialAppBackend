package com.ptv.escort.Admin;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ptv.escort.Category.Category;
import com.ptv.escort.Category.CategoryName;
import com.ptv.escort.Category.CategoryService;
import com.ptv.escort.Config.JwtResponse;
import com.ptv.escort.Config.JwtUtil;
import com.ptv.escort.Config.UserVerification;
import com.ptv.escort.Response.ImageFileResponse;
import com.ptv.escort.User.User;
import com.ptv.escort.User.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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


    @Autowired
    private FileStorageService fileStorageService;




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


    @GetMapping("/getallescort")
    public ResponseEntity<?> getListOfAllEscort(){
        return ResponseEntity.ok(adminService.getListOfAllEscorts());
    }



    @PostMapping("/EscortList/WithCategory")
    public ResponseEntity<?> getListOfEscortsWithCategory(@RequestBody Category category){
        return ResponseEntity.ok(adminService.getListOfEscortsWithCategory(category.getCategoryName()));
    }



    @PostMapping(value = "/createescort")
    public ImageFileResponse createEscort(@RequestParam String name,
                                          @RequestParam String location,
                                          @RequestParam String phoneNumber,
                                          @RequestParam String email,
                                          @RequestParam CategoryName category,
                                          @RequestParam String description,
                                          @RequestParam("image") MultipartFile image) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        EscortDetails escortDetails = adminService.createNewEscort(name,location,phoneNumber,email,category,description,fileName);

         fileStorageService.storeFile(image, escortDetails.getId());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/" + escortDetails.getId() + "/")
                .path(fileName)
                .toUriString();

        adminService.saveImagePathForUser(fileDownloadUri,escortDetails.getId());

        return new ImageFileResponse(fileDownloadUri,
                image.getContentType(), image.getSize(), escortDetails);
    }


    @GetMapping("/downloadFile/{id}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, @PathVariable("id") long id,  HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName,id);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }



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



//    @PutMapping(value = "/update/escort/availablity/{id}")
//    public ResponseEntity<?> updateEscortAvailablity(@PathVariable("id") long id, @RequestBody EscortDetails value){
//        return ResponseEntity.ok(adminService.toggleEscortAvailablity(id,value.isAvailable()));
//    }











}
