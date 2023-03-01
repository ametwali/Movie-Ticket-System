package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisteredUser extends OrdinaryUser{
    String userName;
    String pwrd;
    String address;
    String email;
    int ccId;

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "userName='" + userName + '\'' +
                ", pwrd='" + pwrd + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", ccId=" + ccId +
                '}';
    }

    public RegisteredUser(String userName, String pwrd, String address, String email, int ccId) {
        this.userName = userName;
        this.pwrd = pwrd;
        this.address = address;
        this.email = email;
        this.ccId = ccId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwrd() {
        return pwrd;
    }

    public void setPwrd(String pwrd) {
        this.pwrd = pwrd;
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

    public int getCcId() {
        return ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }




}
