package com.manager.information.webData;

import com.manager.information.domain.TitleType;

public class UserRegister {
    private String names;
    private TitleType personType;
    private String userName;
    private String email;
    private String password;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public TitleType getPersonType() {
        return personType;
    }

    public void setPersonType(TitleType personType) {
        this.personType = personType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}

