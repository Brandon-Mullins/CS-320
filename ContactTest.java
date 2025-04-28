package edu.snhu.bmullins.contact;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactTest {

    private final String validId = "123456";
    private final String validFirstName = "John";
    private final String validLastName = "Doe";
    private final String validPhone = "1234567890";
    private final String validAddress = "123 Main St";

    @Test
    public void testValidContact_creation_successful() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        Assertions.assertEquals(validId, contact.getId());
        Assertions.assertEquals(validFirstName, contact.getFirstName());
        Assertions.assertEquals(validLastName, contact.getLastName());
        Assertions.assertEquals(validPhone, contact.getPhone());
        Assertions.assertEquals(validAddress, contact.getAddress());
    }

    @Test
    public void testId_null_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, validFirstName, validLastName, validPhone, validAddress));
    }

    @Test
    public void testId_tooLong_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345678901", validFirstName, validLastName, validPhone, validAddress));
    }

    @Test
    public void testFirstName_null_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, null, validLastName, validPhone, validAddress));
    }

    @Test
    public void testFirstName_tooLong_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, "ThisIsWayTooLong", validLastName, validPhone, validAddress));
    }

    @Test
    public void testLastName_null_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, validFirstName, null, validPhone, validAddress));
    }

    @Test
    public void testLastName_tooLong_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, validFirstName, "ThisIsAlsoTooLong", validPhone, validAddress));
    }

    @Test
    public void testPhone_invalidLength_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, validFirstName, validLastName, "1234", validAddress));
    }

    @Test
    public void testPhone_null_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, validFirstName, validLastName, null, validAddress));
    }

    @Test
    public void testPhone_notNumeric_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, validFirstName, validLastName, "abc123efgh", validAddress));
    }

    @Test
    public void testAddress_null_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, validFirstName, validLastName, validPhone, null));
    }

    @Test
    public void testAddress_tooLong_throwsException() {
        String longAddress = "1234567890 1234567890 1234567890 123456";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(validId, validFirstName, validLastName, validPhone, longAddress));
    }
}
