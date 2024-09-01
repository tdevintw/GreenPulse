package Auth;
import Database.Database;
import  User.User;
public class login {

    public boolean nameExist(String name){
        Database database = Database.getDatabase();
        for(User user : database.getUsers()){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public User checkPassword(String name , String password){
        Database database = Database.getDatabase();
        for(User user : database.getUsers()){
            if(user.getName().equals(name)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

}
