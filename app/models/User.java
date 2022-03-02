package models;

import java.util.HashSet;
import java.util.Set;

public class User {
    public Integer id;
    public String firstName;
    public String lastName;
    public int age;

    public User() {}

    public User(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    private static Set<User> users;

    static {
        users = new HashSet<>();
        users.add(new User(1, "John", "Doe", 36));
        users.add(new User(2, "Jane", "Smith", 28));
        users.add(new User(3, "Jake", "Doe", 27));
    }

    public static Set<User> allUsers() {
        return users;
    }

    public static User findById(Integer id) {
        for (User user: users) {
            if (id.equals(user.id)) {
                return user;
            }
        }
        return null;
    }

    public static void add(User user) {
        users.add(user);
    }

    public static boolean remove(User user) {
        return users.remove(user);
    }
}
