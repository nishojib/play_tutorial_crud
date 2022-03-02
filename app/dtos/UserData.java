package dtos;

import models.User;

import play.data.validation.Constraints;

public class UserData {
    @Constraints.Required
    public Integer id;

    @Constraints.Required
    public String firstName;

    @Constraints.Required
    public String lastName;

    @Constraints.Required
    public int age;

    public UserData() {}

    public UserData(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User toUser() {
        return new User(this.id, this.firstName, this.lastName, this.age);
    }

    public static UserData fromUser(User user) {
        return new UserData(user.id, user.firstName, user.lastName, user.age);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
