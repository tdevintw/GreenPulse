package User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.time.temporal.ChronoUnit;

public class Report {
    private int id ;
    private long totalOfDays;
    private float totalOfCarbon;
    private User user;
    private LocalDateTime created_at;
    private double dayAverage;
    private double monthAverage;
    private double yearAverage;

    public static long CalculateTotalOfDays(User user){
        long TotalDays = 0;

            for(Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
                Consumption consumption = consumptionEntry.getValue();
                long daysBetween = ChronoUnit.DAYS.between(consumption.getStart_date(), consumption.getEnd_date());
                TotalDays += daysBetween;
            }
            return TotalDays;


    }

    public static float CalculateTotalOfCarbon(User user){
        float TotalCarbon = 0;

            for(Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()){
                Consumption consumption = consumptionEntry.getValue();
                TotalCarbon += consumption.getCarbonQuantity();
            }
            return TotalCarbon;


    }
    public Report(User user) {
        this.id = (int)(Math.random()*10000000);
        this.totalOfDays = CalculateTotalOfDays(user);
        this.totalOfCarbon = CalculateTotalOfCarbon(user);
        this.user = user;
        this.created_at = LocalDateTime.now();
    }

    public void PrintReport(){

        double dayQuantityCheck = this.totalOfDays < 1 ? 0 :  this.totalOfCarbon/this.totalOfDays;
        this.dayAverage = dayQuantityCheck;
        System.out.println("Daily consumption of carbon is : "+dayQuantityCheck+ "Kg");
        double monthQuantityCheck = this.totalOfDays < 30 ? this.totalOfCarbon : this.totalOfCarbon/(this.totalOfDays/30);
        this.monthAverage = monthQuantityCheck;
        System.out.println("Monthly consumption of carbon is : "+ monthQuantityCheck + "KG");
        double yearQuantityCheck = this.totalOfDays < 365 ? this.totalOfCarbon : this.totalOfCarbon/(this.totalOfDays/365);
        this.yearAverage = yearQuantityCheck;
        System.out.println("Daily consumption of carbon is : "+yearQuantityCheck + "KG");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTotalOfDays() {
        return totalOfDays;
    }

    public void setTotalOfDays(long totalOfDays) {
        this.totalOfDays = totalOfDays;
    }

    public float getTotalOfCarbon() {
        return totalOfCarbon;
    }

    public void setTotalOfCarbon(float totalOfCarbon) {
        this.totalOfCarbon = totalOfCarbon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public double getDayAverage() {
        return dayAverage;
    }

    public double getMonthAverage() {
        return monthAverage;
    }

    public double getYearAverage() {
        return yearAverage;
    }
}
