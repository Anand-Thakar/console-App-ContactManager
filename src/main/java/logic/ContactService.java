package logic;

import lombok.Data;
import model.Contact;
import model.numberType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class ContactService {


    private List<Contact> contactList = new ArrayList<>();


    private boolean validateContact(Contact contact) {

        if (contact.getFirstName()==null || contact.getFirstName()==null ) {
            throw new RuntimeException("No null value allowed.");
        }
        return true;
    }

    public void addContact(Contact contact) throws RuntimeException {

        boolean b = validateContact(contact);
        if (contact.getNumberType()!=numberType.Personal){
            throw new RuntimeException("Mobile type isn't personal");
        }

        if (b) {
            if (contact.getFirstName() == null) {
                throw new RuntimeException("First name can't Null");
            }

            if (contact.getFirstName().equals("") || StringUtils.isEmpty(contact.getFirstName())) {
                throw new RuntimeException("First name can't be blank");
            }
            if (!StringUtils.isAlphanumeric(contact.getFirstName())) {
                throw new RuntimeException("First name is invalid");
            }
            if (!StringUtils.isNumeric(contact.getNumber())) {
                throw new RuntimeException("Number shouldn't be anything other than digits");
            }
            int index = checkIfExist(contact.getNumber());
            if (index < 0) {
                contactList.add(contact);
            } else {
                contactList.get(index).setNumber(contact.getNumber());
                contactList.get(index).setFirstName(contact.getFirstName());
            }
        }
    }

    public void updateContact(String phone) {

        int i = checkIfExist(phone);
        if (i >= 0) {

            System.out.println("Enter 0 :  To update firstName ");
            System.out.println("Enter 1 :  To update contact number");
            System.out.println("Enter 2 :  To delete contact number");
            System.out.println("Please enter input: ");
            Scanner scanner = new Scanner(System.in);
            int i1 = scanner.nextInt();

            switch (i1) {
                case 1 -> {
                    System.out.println("Enter new number");
                    String next = scanner.next();
                    if (!StringUtils.isNumeric(next)) {
                        throw new RuntimeException("please enter numeric value");
                    } else {
                        contactList.get(i).setNumber(next);
                        System.out.println("Contact updated succesfully with new number " + next);
                    }
                }
                case 0 -> {
                    System.out.println("Enter new first name");
                    String next2 = scanner.next();

                    if (!StringUtils.isAlphanumeric(next2)) {
                        throw new RuntimeException("please enter firstname in alpha-numeric value only");
                    }
                    if (StringUtils.isBlank(next2) || StringUtils.isEmpty(next2)) {
                        throw new RuntimeException("First name cant be set null or blank");
                    } else {
                        contactList.get(i).setFirstName(next2);
                        System.out.println("Contact updated succesfully with new first name " + next2);
                    }
                }
                case 2 -> {
                    contactList.remove(i);
                    System.out.println("contact removed sucessfully");
                }

                default -> throw new RuntimeException("Please press valid Input");

            }
        } else {
            throw new RuntimeException("No records found for the given phone number " + phone + " to do changes.");
        }
    }

    private int checkIfExist(String newNumber) {
        for (int i = 0; i < contactList.size(); i++) {
            boolean exist = contactList.get(i).getNumber().equals(newNumber);
            if (exist) {
                return i;
            }
        }
        return -1;
    }
}
