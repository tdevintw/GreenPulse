package Auth;
import Database.Database;
import User.User;

public class Register {
    public User create(int age , String name , String password){
        User newUser = new User(age , name , password);
        Database database = Database.getDatabase();
        database.addUser(newUser);
        return newUser;
    }

}
