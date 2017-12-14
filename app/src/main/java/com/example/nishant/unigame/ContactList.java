package com.example.nishant.unigame;

/**
 * Created by nishant on 15/12/17.
 */

public class ContactList {

    private String contact_name,contact_count;

    public ContactList(){

    }

    public ContactList(String contact_count ,String contact_name){
        this.contact_count=contact_count;
        this.contact_name=contact_name;
    }
    public void setContact_name(String contact_name){this.contact_name=contact_name;}
    public void setContact_count(String contact_count){this.contact_count=contact_count;}

    public String getContact_name(){return contact_name;}
    public String getContact_count(){return contact_count;}
}
