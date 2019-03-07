package com.example.kcb;

import java.io.Serializable;

public class User implements Serializable {
    private String account;
    private String password;
    private String superpassword;

    public User(String account,String password,String superpassword){
        this.account = account;
        this.password = password;
        this.superpassword = superpassword;
    }

    public User(){

    }

    public String getAccount(){return account;}

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword(){return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuperpassword() {return superpassword;}

    public void setSuperpassword(String superpassword) {
        this.superpassword = superpassword;
    }
}
