package org.cdut.tzg.model;

import org.apache.ibatis.annotations.Insert;

import java.io.Serializable;

/**
 * @author anlan
 * @date 2019/6/25 8:34
 */
public class User implements Serializable {
    private Long id;
    private String schoolNumber;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String email;
    private Integer isFrozen;
    private Integer totalSold;
    private Integer grade;
    private String avatar;
    private String moneyCode;
    private Integer role;

    public User(Long id, String schoolNumber, String username, String password, String phoneNumber,
         String address, String email,Integer isForzen, Integer totalSold, Integer grade,
         String avatar, String moneyCode, Integer role){
        this.id=id;
        this.schoolNumber=schoolNumber;
        this.username=username;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.email=email;
        this.isFrozen=isForzen;
        this.totalSold=totalSold;
        this.grade=grade;
        this.avatar=avatar;
        this.moneyCode=moneyCode;
        this.role=role;
    }
    public User(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Integer isFrozen) {
        this.isFrozen = isFrozen;
    }

    public Integer getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(Integer totalSold) {
        this.totalSold = totalSold;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMoneyCode() {
        return moneyCode;
    }

    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", number='" + schoolNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", isFrozen=" + isFrozen +
                ", totalSold=" + totalSold +
                ", grade=" + grade +
                ", avatar='" + avatar + '\'' +
                ", moneyCode='" + moneyCode + '\'' +
                ", role=" + role +
                '}';
    }
}
