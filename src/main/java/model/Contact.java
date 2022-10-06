package model;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class Contact {
    private String firstName;
    private String number;
    private model.numberType numberType;

    public Contact(String firstName, String number, model.numberType numberType) {
        this.firstName = firstName;
        this.number = number;
        this.numberType = numberType;
    }
}
