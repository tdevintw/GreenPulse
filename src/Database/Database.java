package Database;

import User.User;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database database;
    private List<User> users;

    private Database() {};

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
            database.users = new ArrayList<>();
        }
        return database;
    }

    public List<User> getUsers() {
        return database.users;
    }

    public void addUser(User user) {
        users.add(user);
    }

}
