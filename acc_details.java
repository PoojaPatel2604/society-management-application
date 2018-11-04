package com.example.dell.miniproject;

public class acc_details {

    public String ownersname;
    public String ownnersflatno;
    public String ownerscontact;
    public String ownersamount;
    public String ownerschequeno;
    public String ownersdate;


    public acc_details(){

    }

    public acc_details(int usergreen){

    }

    public acc_details(String ownersname, String ownnersflatno, String ownerscontact, String ownersamount, String ownerschequeno, String ownersdate) {
        this.ownersname = ownersname;
        this.ownnersflatno = ownnersflatno;
        this.ownerscontact = ownerscontact;
        this.ownersamount = ownersamount;
        this.ownerschequeno = ownerschequeno;
        this.ownersdate = ownersdate;
    }


    public String getOwnersname() {
        return ownersname;
    }

    public void setOwnersname(String ownersname) {
        this.ownersname = ownersname;
    }

    public String getOwnnersflatno() {
        return ownnersflatno;
    }

    public void setOwnnersflatno(String ownnersflatno) {
        this.ownnersflatno = ownnersflatno;
    }

    public String getOwnerscontact() {
        return ownerscontact;
    }

    public void setOwnerscontact(String ownerscontact) {
        this.ownerscontact = ownerscontact;
    }

    public String getOwnersamount() {
        return ownersamount;
    }

    public void setOwnersamount(String ownersamount) {
        this.ownersamount = ownersamount;
    }

    public String getOwnerschequeno() {
        return ownerschequeno;
    }

    public void setOwnerschequeno(String ownerschequeno) {
        this.ownerschequeno = ownerschequeno;
    }

    public String getOwnersdate() {
        return ownersdate;
    }

    public void setOwnersdate(String ownersdate) {
        this.ownersdate = ownersdate;
    }
}
