import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import Auth.*;
import  User.*;
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
            if (newLogin.nameExist(name)) {
                System.out.println("Enter Your password");
                String password = input.nextLine();
                currentUser = newLogin.checkPassword(name, password);
                if (currentUser != null) {
                    UserMenu(currentUser);
                } else {
                    System.out.println("password is incorrect try again");
                    LoggingMenu(1);
                }
            } else {
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
        int size;
        System.out.println("Welcome Back " + user.getName());
        System.out.println("1-See Personal information");
        System.out.println("2-Edit Personal information");
        System.out.println("3-Add consumption");
        System.out.println("4-Create a rapport");
        System.out.println("5-Delete Account");
        System.out.println("6-logout");
        System.out.println("7-My Consumptions");
        System.out.println("8-My Reports");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Name: " + user.getName());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Age: " + user.getAge());
                if(user.getConsumptions() != null){
                    size = user.getConsumptions().size();
                }else{
                    size = 0 ;
                }
                System.out.println("Number of consumptions is : " + size);
                UserMenu(user);
                break;
            case 2:
                update(user);
                break;
            case 3:
                ManageConsumptions(user);
                break;
            case 4:
                Report report = new Report(user);
                report.PrintReport();
                UserMenu(user);
                break;
            case 5:
                User.delete(currentUser.getId());
                currentUser = null;
                break;
            case 6:
                currentUser = null;
                break;

        }
    }

    public static void update(User user) {
        Scanner input = new Scanner(System.in);
        Scanner StringInput = new Scanner(System.in);
        System.out.println("What do you want to change");
        System.out.println("1-Name");
        System.out.println("2-Password");
        System.out.println("3-Age");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter Your new Name");
                String newName = StringInput.nextLine();
                user.setName(newName);
                break;
            case 2:
                System.out.println("Enter Your new password");
                String newPassword = StringInput.nextLine();
                user.setPassword(newPassword);
                break;
            case 3:
                System.out.println("Enter Your new Name");
                int newAge = input.nextInt();
                user.setAge(newAge);
                break;
        }

        UserMenu(user);
    }

    public static void ManageConsumptions(User user){
        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner StringInput = new Scanner(System.in);
        System.out.println("Enter the value of the carbon (CO2 in KG)");
        float quantity = input.nextFloat();
        System.out.println("Enter the start date");
        String start_date = StringInput.nextLine();
        LocalDate start_date_formatted = LocalDate.parse(start_date , formatter);
        System.out.println("Enter the end date");
        String end_date = StringInput.nextLine();
        LocalDate end_date_formatted = LocalDate.parse(end_date , formatter);
        Consumption newConsumption =  new Consumption(quantity , start_date_formatted , end_date_formatted, user);
        user.addConsumption(newConsumption);
        UserMenu(user);
    }

}