package User;

import Database.Database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class User {
    private final int id;
    private String name;
    private int age;
    private String password;
    private HashMap<Integer, Consumption> consumptions;
    private List<Report> reports;
    private final LocalDateTime created_at;

    public User(int age, String name, String password) {
        id = (int) (Math.random() * 1000000000);
        this.name = name;
        this.age = age;
        this.password = password;
        this.created_at = LocalDateTime.now();

    }

    public int getId() {
        return id;
    }


    public void setName(String name) {
        Scanner input = new Scanner(System.in);
        while (name.length() < 3) {
            System.out.println("Name must be at least 3 character");
            name = input.nextLine();
        }
        this.name = name;
    }

    public void setPassword(String password) {
        Scanner input = new Scanner(System.in);
        while (password.length() < 6) {
            System.out.println("Password must be at least 6 character");
            password = input.nextLine();
        }
        this.password = password;
    }

    public void setAge(int age) {
        Scanner input = new Scanner(System.in);
        while (age < 1) {
            System.out.println("Age Can't be less then 1 ,try again");
            age = input.nextInt();
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }


    public HashMap<Integer, Consumption> getConsumptions() {
        return consumptions;
    }

    public void addConsumption(Consumption consumption) {
//        System.out.println("id is "+ consumption.getId() +  "quantity is " + +consumption.getCarbonQuantity() + "user name is"+consumption.getUser().getName());
        if (this.consumptions == null) {
            consumptions = new HashMap<Integer, Consumption>();
        }
        consumptions.put(consumption.getId(), consumption);
    }

    public void addReport(Report report) {
        if (this.reports == null) {
            reports = new ArrayList<>();
        }
        reports.add(report);
    }


    public List<Report> getReports() {
        return reports;
    }


    public static void delete(int Id) {
        Database database = Database.getDatabase();
        List<User> users = database.getUsers();
        for (User user : users) {
            if (user.getId() == Id) {
                users.remove(user);
                break;
            }
        }
    }


}
