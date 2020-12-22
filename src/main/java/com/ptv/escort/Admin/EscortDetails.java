package com.ptv.escort.Admin;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause ="del_flag='N'")
@Table(name = "escort_details")
public class EscortDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "phone_no")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "category")
    private String category;

    @Column(name = "photos")
    private String photos;

    @Column(name = "description")
    private String description;

    @Column(name = "del_Flag")
    private String delFlag;

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null) return null;

        return "/user-photos/" + id + "/" + photos;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
