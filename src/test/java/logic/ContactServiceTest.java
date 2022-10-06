package logic;

import model.Contact;
import model.numberType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactServiceTest {


    @Test
    public void addValidContact() {
        ContactService service = new ContactService();
        service.addContact(new Contact("Anand", "7854", numberType.Personal));
        Assert.assertFalse(service.getContactList().isEmpty());
        Assert.assertEquals(1, service.getContactList().size());
    }

    @Test
    public void invalidContactFirstNameIsNull() {
        ContactService service = new ContactService();
        Assert.assertThrows(RuntimeException.class, () -> {
            service.addContact(new Contact("Anand", null, numberType.Personal));
        });
        Assert.assertEquals(0, service.getContactList().size());
    }

    @Test
    public void validnumberTypeCantBeAnythingOtherThanPersonal() {
        ContactService service = new ContactService();
        Assert.assertThrows(RuntimeException.class, () -> {
            service.addContact(new Contact("Anand", "45415", numberType.Mobile));
        });
        Assert.assertEquals(0, service.getContactList().size());

    }

    @Test
    public void removeNonExistingContact() {
        ContactService service = new ContactService();

    }
}