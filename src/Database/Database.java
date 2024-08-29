package Database;
import  User.User;
import java.util.ArrayList;

public class Database {
    private static Database database;
    private ArrayList<User> users;
    private Database(){};

    public  static Database getDatabase() {
       if(database ==null){
          database = new Database();
          database.users = new ArrayList<>();
       }
       return database;
    }

    public  ArrayList<User> getUsers () {
       return  database.users;
    }

    public void addUser(User user){
        users.add(user);
    }

}
