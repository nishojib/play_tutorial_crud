package dtos;

import models.User;

public class EditUserData {
    public String firstName;

    public EditUserData() {}

    public EditUserData(String firstName) {
        this.firstName = firstName;
    }

    public static EditUserData fromUser(User user) {
        return new EditUserData(user.firstName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
