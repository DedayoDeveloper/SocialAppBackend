package com.ptv.escort.User;

import java.util.List;

public class UserDto {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String birthDay;
    public List<String> interests;
    public List<String> hobbies;
    public String phone;
    public String gender;
    public boolean showGender;
    public List<Photos> photos;
    public String username;

    public UserDto(String firstName, String lastName, String email, String password, String birthDay, List<String> interests, List<String> hobbies, String phone, String gender, boolean showGender, List<Photos> photos, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.interests = interests;
        this.hobbies = hobbies;
        this.phone = phone;
        this.gender = gender;
        this.showGender = showGender;
        this.photos = photos;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isShowGender() {
        return showGender;
    }

    public void setShowGender(boolean showGender) {
        this.showGender = showGender;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", interests=" + interests +
                ", hobbies=" + hobbies +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", showGender=" + showGender +
                ", photos=" + photos +
                ", username='" + username + '\'' +
                '}';
    }
}
