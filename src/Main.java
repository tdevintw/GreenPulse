import java.util.Scanner;
import Auth.login;
import Auth.Register;
import User.User;

public class Main {
    private static User currentUser;

    public static void main(String[] args) {
        while (currentUser == null) {
            notLoggingMenu();
        }
    }

    public static void notLoggingMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Back to GreenPulse , ");
        System.out.println("Login to you account or create an account now to start the journey with us .");
        System.out.println("1-login");
        System.out.println("2-Register");
        int choice = input.nextInt();
        if (choice != 1 && choice != 2) {
            System.out.println("Please Enter a valid Number");
        } else {
            LoggingMenu(choice);
        }
    }

    public static void LoggingMenu(int choice) {
        Scanner input = new Scanner(System.in);
        if (choice == 1) {
            login newLogin = new login();
            System.out.println("Enter Your name");
            String name = input.nextLine();
            if(newLogin.nameExist(name)){
                System.out.println("Enter Your password");
                String password = input.nextLine();
                currentUser = newLogin.checkPassword(name , password);
                if(currentUser !=null){
                    UserMenu(currentUser);
                }else{
                    System.out.println("password is incorrect try again");
                    LoggingMenu(1);
                }
            }else{
                System.out.println("Name doesnt exist");
            }

        } else {
            System.out.println("Enter Your name");
            String name = input.nextLine();
            System.out.println("Enter your password");
            String password = input.nextLine();
            System.out.println("Enter your age");
            int age = input.nextInt();
            Register newAccount = new Register();
            User user = newAccount.create(age, name, password);
            currentUser = user;
            UserMenu(user);
        }
    }

    public static void UserMenu(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Back " + user.getName());
        System.out.println("1-See Personal information");
        System.out.println("2-Edit Personal information");
        System.out.println("3-Add consumption");
        System.out.println("4-Create a rapport");
        System.out.println("5-Delete Account");
        System.out.println("6-logout");
        int choice = input.nextInt();
        if(choice ==1){
            System.out.println("Name: " + user.getName());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Age: "+ user.getAge());
            UserMenu(user);

        }else if(choice ==2){
            update(user);
        }
        else if (choice == 5) {
            User.delete(currentUser.getId());
            currentUser = null;
        }else if(choice ==6){
            currentUser = null;
        }
    }

    public static void update(User user){
        Scanner input = new Scanner(System.in);
        Scanner StringInput = new Scanner(System.in);
        System.out.println("What do you want to change");
        System.out.println("1-Name");
        System.out.println("2-Password");
        System.out.println("3-Age");
        int choice = input.nextInt();
        if(choice ==1){
            System.out.println("Enter Your new Name");
            String newName = StringInput.nextLine();
            user.setName(newName);
        } else if (choice ==2) {
            System.out.println("Enter Your new password");
            String newPassword = StringInput.nextLine();
            user.setPassword(newPassword);
        } else if (choice ==3) {
            System.out.println("Enter Your new Name");
            int newAge = input.nextInt();
            user.setAge(newAge);
        }
        UserMenu(user);
    }
}