package com.example.officetask;

public class ContactObject {

    String nameContact;
    String numberContact;
    String emailContact;

    public ContactObject(String nameContact, String numberContact, String emailContact) {
        this.nameContact = nameContact;
        this.numberContact = numberContact;
        this.emailContact = emailContact;
    }

    public String getNameContact() {
        return nameContact;
    }
    public String getNumberContact() {
        return numberContact;
    }
    public String getEmailContact() {return emailContact;}

}
