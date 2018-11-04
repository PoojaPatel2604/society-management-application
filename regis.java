package com.example.dell.miniproject;

public class regis {

    public String name;
    public String flatno;
    public String email;
    public String password;
    public String contact;
    public String details;

    public regis(){

    }
public regis(int usergreen) {

    }

    public regis(String name, String flatno, String email, String password, String contact, String details) {
        this.name = name;
        this.flatno = flatno;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.details = details;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getflatno() {
        return flatno;
    }

    public void setflatno(String flatno) {
        this.flatno = flatno;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getcontact() {
        return contact;
    }

    public void setcontact(String contact) {
        this.contact = contact;
    }

    public String getdetails() {
        return details;
    }

    public void setdetails(String details) {
        this.details = details;
    }
}


