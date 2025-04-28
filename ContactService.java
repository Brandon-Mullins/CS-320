package edu.snhu.bmullins.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ContactService {

    private final List<Contact> contacts;

    public ContactService() {
        this.contacts = new ArrayList<>();
    }

    public void add(Contact contact) {
        for (Contact c : contacts) {
            if (c.getId().equals(contact.getId())) {
                throw new IllegalArgumentException("Contact ID already exists: " + contact.getId());
            }
        }
        contacts.add(contact);
    }

    public void delete(String id) {
        boolean removed = contacts.removeIf(c -> c.getId().equals(id));
        if (!removed) {
            throw new NoSuchElementException("No contact found with ID: " + id);
        }
    }

    public void update(String id, String firstName, String lastName, String phone, String address) {
        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                c.setFirstName(firstName);
                c.setLastName(lastName);
                c.setPhone(phone);
                c.setAddress(address);
                return;
            }
        }
        throw new NoSuchElementException("Contact with ID " + id + " not found.");
    }

    public Contact get(String id) {
        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                return new Contact(c);
            }
        }
        throw new NoSuchElementException("Contact with ID " + id + " not found.");
    }
}
