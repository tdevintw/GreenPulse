import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import Auth.*;
import User.*;

public class Main {
    private static User currentUser;

    public static void main(String[] args) {
        while (currentUser == null) {
            notLoggingMenu();
        }
    }

    public static void notLoggingMenu() {
        logo();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Back to GreenPulse , ");
        System.out.println("Login to you account or create an account now to start the journey with us .");
        System.out.println("1-login");
        System.out.println("2-Register");
        int choice = input.nextInt();
        if (choice != 1 && choice != 2) {
            System.out.println("Please Enter a valid Number");
        } else {
            loggingMenu(choice);
        }
    }

    public static void loggingMenu(int choice) {
        logo();
        Scanner input = new Scanner(System.in);
        if (choice == 1) {
            Login newLogin = new Login();
            System.out.println("Enter Your name , (to exit type exit)");
            String name = input.nextLine();
            if(name.equals("exit")){
               notLoggingMenu();
            }
            else if (newLogin.nameExist(name)) {
                System.out.println("Enter Your password");
                String password = input.nextLine();
                currentUser = newLogin.checkPassword(name, password);
                if (currentUser != null) {
                    userMenu(currentUser);
                } else {
                    System.out.println("password is incorrect try again");
                    loggingMenu(1);
                }
            } else {
                System.out.println("Name doesnt exist");
            }

        } else {
            System.out.println("Enter Your name (exit)");
            String name = input.nextLine();
            if(name.equals("exit")){
                notLoggingMenu();
            }
            while(name.length() <3){
                System.out.println("name must be at least 3 character , try again");
                name = input.nextLine();
            }

            System.out.println("Enter your password (exit)");
            String password = input.nextLine();
            if(password.equals("exit")){
                notLoggingMenu();
            }
            while(password.length() <6){
                System.out.println("password must be at least 6 character , try again");
                password = input.nextLine();
            }
            System.out.println("Enter your age (0 to exit)");
            int age = input.nextInt();
            if(age ==0){
                notLoggingMenu();
            }
            while (age<0){
                System.out.println("age cant be less then 1 , try again");
                age = input.nextInt();
            }
            Register newAccount = new Register();
            User user = newAccount.create(age, name, password);
            currentUser = user;
            userMenu(user);
        }
    }


    public static void userMenu(User user) {
        logo();
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
        System.out.println("9-Advanced Reports");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Name: " + user.getName());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Age: " + user.getAge());
                if (user.getConsumptions() != null) {
                    size = user.getConsumptions().size();
                } else {
                    size = 0;
                }
                System.out.println("Number of consumptions is : " + size);
                if (user.getReports() != null) {
                    size = user.getReports().size();
                } else {
                    size = 0;
                }
                System.out.println("Number of Reports is : " + size);
                userMenu(user);
                break;
            case 2:
                update(user);
                break;
            case 3:
                manageConsumptions(user);
                break;
            case 4:

                if (user.getConsumptions() != null) {
                    Report report = new Report(user);
                    user.addReport(report);
                    report.printReport();
                } else {
                    System.out.println("There are no Consumptions yet");
                }
                userMenu(user);
                break;
            case 5:
                User.delete(currentUser.getId());
                currentUser = null;
                break;
            case 6:
                currentUser = null;
                break;
            case 7:
                allConsumption(user);
                break;
            case 8:
                allReports(user);
                break;
            case 9:
                advancedReport(user);
        }
    }

    public static void update(User user) {
        logo();
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

        userMenu(user);
    }

    public static void manageConsumptions(User user) {
        logo();
        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner StringInput = new Scanner(System.in);
        System.out.println("Enter the value of the carbon (CO2 in KG) , (0 to exit)");
        float quantity = input.nextFloat();
        if(quantity ==0){
            userMenu(user);
        }
        while (quantity<0){
            System.out.println("Quantity cant be negative");
            quantity = input.nextFloat();
        }
        System.out.println("Enter the start date:(yyyy-MM-dd) ,  exit");
        String start_date = StringInput.nextLine();

        if(start_date.equals("exit")){
            userMenu(user);
        }

        LocalDate start_date_formatted = LocalDate.parse(start_date, formatter);
        System.out.println("Enter the end date:(yyyy-MM-dd) ,  exit");
        String end_date = StringInput.nextLine();
        if(end_date.equals("exit")){
            userMenu(user);
        }
        LocalDate end_date_formatted = LocalDate.parse(end_date, formatter);
        Consumption newConsumption = new Consumption(quantity, start_date_formatted, end_date_formatted, user);
        user.addConsumption(newConsumption);
        userMenu(user);
    }

    public static void logo() {
        System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |    ______    | || |   ______     | || |              | || |     ______   | || |     ____     | || |    _____     | |\n" +
                "| |  .' ___  |   | || |  |_   __ \\   | || |              | || |   .' ___  |  | || |   .'    `.   | || |   / ___ `.   | |\n" +
                "| | / .'   \\_|   | || |    | |__) |  | || |    ______    | || |  / .'   \\_|  | || |  /  .--.  \\  | || |  |_/___) |   | |\n" +
                "| | | |    ____  | || |    |  ___/   | || |   |______|   | || |  | |         | || |  | |    | |  | || |   .'____.'   | |\n" +
                "| | \\ `.___]  _| | || |   _| |_      | || |              | || |  \\ `.___.'\\  | || |  \\  `--'  /  | || |  / /____     | |\n" +
                "| |  `._____.'   | || |  |_____|     | || |              | || |   `._____.'  | || |   `.____.'   | || |  |_______|   | |\n" +
                "| |              | || |              | || |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
    }

    public static void allConsumption(User user) {
        if (user.getConsumptions() != null) {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd HH:mm:ss");
            int i = 1;
            logo();
            for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {

                Consumption consumption = consumptionEntry.getValue();
                String formattedDateTime = consumption.getCreated_date().format(outputFormatter);
                System.out.println(i + "-Quantity is :" + consumption.getCarbonQuantity() + " between " + consumption.getStart_date() + " and " + consumption.getEnd_date() + " Created At : " + formattedDateTime);
                i++;
            }
        } else {
            System.out.println("There is no Consumptions Added Yet !");
        }
        userMenu(user);

    }

    public static void allReports(User user) {

        if (user.getReports() != null) {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd HH:mm:ss");

            int i = 1;
            logo();
            for (Report report : user.getReports()) {
                String formattedDateTime = report.getCreated_at().format(outputFormatter);

                System.out.println("The " + i + " Report : ");
                System.out.println("Average Day of Carbon : " + report.getDayAverage() + "KG");
                System.out.println("Average Month of Carbon : " + report.getMonthAverage() + "KG");
                System.out.println("Average Year of Carbon : " + report.getYearAverage() + "KG");
                System.out.println("Created at : " + formattedDateTime);
                System.out.println();
                System.out.println();
                i++;
            }
        } else {
            System.out.println("There is no Reports Yet ! ");
        }
        userMenu(user);
    }

    public static void advancedReport(User user) {
        Scanner input = new Scanner(System.in);
        logo();
        if (user.getConsumptions() == null) {
            System.out.println("No Data added yet !");

        } else {
            System.out.println("filter by 1-Days 2-Weeks 3-Months");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    Report.filterByDays(user);
                    break;
                case 2:
                    Report.filterByWeeks(user);
                    break;
                case 3:
                    Report.filterByMonths(user);
                    break;
            }
        }

        userMenu(user);
    }

}