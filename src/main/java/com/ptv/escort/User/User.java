package com.ptv.escort.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptv.escort.Admin.EscortPaymentDetails;
import com.ptv.escort.Category.CategoryName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "showGender")
    private boolean showGender;


    @ElementCollection
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "interest")
    private List<String> interests;

    @Column(name = "hobbies")
    private String hobbies;

    @JsonIgnoreProperties
    @Column(name = "password")
    private String password;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "category")
//    private CategoryName category;

//    @Column(name = "user_role")
//    private String userRole;

//    @Column(name = "sex")
//    private String sex;
//
//    @Column(name = "registration_payment")
//    private boolean registrationPayment;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Photos> photos;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public CategoryName getCategory() {
//        return category;
//    }
//
//    public void setCategory(CategoryName category) {
//        this.category = category;
//    }

//    public String getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(String userRole) {
//        this.userRole = userRole;
//    }
//
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public List<String> getInterests() {return interests;}
    public void setInterests(List<String> interests) {this.interests = interests;}

    public String getHobbies() {return hobbies;}
    public void setHobbies(String hobbies) {this.hobbies = hobbies;}

    public String getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public boolean isShowGender() {return showGender;}
    public void setShowGender(boolean showGender) {this.showGender = showGender;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}


//    public boolean isRegistrationPayment() {
//        return registrationPayment;
//    }

//   // public void setRegistrationPayment(boolean registrationPayment) {
//        this.registrationPayment = registrationPayment;
//    }
}
