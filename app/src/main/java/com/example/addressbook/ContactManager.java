package com.example.addressbook;
import java.util.ArrayList;
import java.util.List;
public class ContactManager {
    private static ContactManager contact;
    private List <String> contactsList;

    private ContactManager(){
        contactsList = new ArrayList<>();
        contactsList.add("Testing test");
    }

    //get class instance
    public  static ContactManager getContact(){
        if(contact == null){
            contact = new ContactManager();
        }
        return contact;
    }

    public List<String> getContactsList() {
        return contactsList;
    }

    public void addContact(String contact){
        contactsList.add(contact);
    }


}
