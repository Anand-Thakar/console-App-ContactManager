import logic.ContactService;
import model.Contact;
import model.numberType;

import java.util.Scanner;

public class Runner {
    Contact contact;

    public static void main(String[] args) {
        ContactService contactService = new ContactService();
        int exitApp = 1;

        System.out.println("This is contact Managemnet Application");
        System.out.println(" Press L: list of contacts");
        System.out.println(" Press A: To add a new contact to the list of contacts");
        System.out.println(" Press U: To update,delete and edit contact");
        System.out.println(" Press X: To exit from the app ");
        do {
            System.out.println("Please press your option:");
            Scanner sc1 = new Scanner(System.in);
            String option = sc1.next();

            switch (option) {
                case "L":
                    if (contactService.getContactList().size() == 0) {
                        System.out.println("contact list is empty");
                    }
                    System.out.println("Contact List = " + contactService.getContactList());
                    break;

                case "U":
                    try {
                        System.out.println("Enter the number you want to modify");
                        String next = sc1.next();
                        contactService.updateContact(next);
                    } catch (Exception e) {
                        System.out.println("Number can't be updated because " + e.getMessage());
                    }
                    break;

                case "X":
                    exitApp = 0;
                    break;

                case "A":

                    try {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Enter the First name :");
                        String name = sc.next();
                        System.out.println("Enter the phone Number :");
                        String number = sc.next();

                        Contact contact = Contact.builder().firstName(name)
                                .number(number)
                                .numberType(numberType.Personal)
                                .build();
                        contactService.addContact(contact);

                    } catch (Exception e) {

                        System.out.println("Contact Not added because "+e.getMessage());
                    }
                    break;


                default:
                    System.out.println("Please choose a valid menu option...");
                    break;

            }
        } while (exitApp > 0);
    }
}
