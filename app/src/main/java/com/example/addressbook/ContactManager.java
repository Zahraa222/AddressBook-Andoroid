package com.example.addressbook;
import java.util.ArrayList;
import java.util.List;
public class ContactManager {
    private static ContactManager contact;
    private List <String> contactsList;
    private  List <String> favoritesList;

    private ContactManager(){
        contactsList = new ArrayList<>();
        favoritesList = new ArrayList<>();
        contactsList.add("Testing  \n 1234567890");
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

    public  List<String> getFavoritesList(){
        return favoritesList;
    }

    public void addContact(String contact){
        contactsList.add(contact);
    }


}
