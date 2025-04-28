package edu.snhu.bmullins.contact;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class ContactServiceTest {

    private ContactService contactService;

    @BeforeEach
    public void setup() {
        contactService = new ContactService();
    }

    @Test
    public void testAdd_withNewContact_successfullyAdds() {
        Contact contact = new Contact("1", "Jake", "Long", "1234567890", "5 Dragon St");
        contactService.add(contact);
        Contact result = contactService.get("1");
        Assertions.assertEquals(contact.getId(), result.getId());
        Assertions.assertEquals(contact.getFirstName(), result.getFirstName());
        Assertions.assertEquals(contact.getLastName(), result.getLastName());
        Assertions.assertEquals(contact.getPhone(), result.getPhone());
        Assertions.assertEquals(contact.getAddress(), result.getAddress());

    }

    @Test
    public void testAdd_withDuplicateContactId_throwsIllegalArgumentException() {
        Contact contact1 = new Contact("1", "Jake", "Long", "1234567890", "5 Dragon St");
        Contact contact2 = new Contact("1", "Rose", "Brown", "0987654321", "9 Rose Ave");
        contactService.add(contact1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.add(contact2));
    }

    @Test
    public void testDelete_existingContactId_removesContact() {
        Contact contact = new Contact("2", "Tim", "Turner", "2223334444", "12 Dimmsdale");
        contactService.add(contact);
        contactService.delete("2");
        Assertions.assertThrows(NoSuchElementException.class, () -> contactService.get("2"));
    }

    @Test
    public void testDelete_invalidContactId_throwsNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> contactService.delete("notFound"));
    }

    @Test
    public void testUpdate_withExistingContact_updatesValues() {
        Contact contact = new Contact("3", "Amy", "Stone", "9991110000", "45 Brick Rd");
        contactService.add(contact);
        contactService.update("3", "Amelia", "Strong", "1119990000", "88 Castle Dr");
        Contact updated = contactService.get("3");
        Assertions.assertEquals("Amelia", updated.getFirstName());
        Assertions.assertEquals("Strong", updated.getLastName());
        Assertions.assertEquals("1119990000", updated.getPhone());
        Assertions.assertEquals("88 Castle Dr", updated.getAddress());
    }

    @Test
    public void testUpdate_invalidContactId_throwsNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () ->
                contactService.update("404", "Tom", "Guy", "1010101010", "404 Nowhere"));
    }
}
