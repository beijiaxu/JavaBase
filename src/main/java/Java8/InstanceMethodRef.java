package Java8;

import java.util.ArrayList;

public class InstanceMethodRef {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "billy" + Integer.toString(i)));
        }
        users.stream().map(User::getName).forEach(System.out::println);
    }

    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
