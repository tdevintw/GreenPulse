package User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Report {
    private int id;
    private long totalOfDays;
    private float totalOfCarbon;
    private User user;
    private final LocalDateTime created_at;
    private double dayAverage;
    private double monthAverage;
    private double yearAverage;

    public static long CalculateTotalOfDays(User user) {
        long TotalDays = 0;

        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            long daysBetween = ChronoUnit.DAYS.between(consumption.getStart_date(), consumption.getEnd_date());
            TotalDays += daysBetween;
        }
        return TotalDays;


    }

    public static float CalculateTotalOfCarbon(User user) {
        float TotalCarbon = 0;

        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            TotalCarbon += consumption.getCarbonQuantity();
        }
        return TotalCarbon;


    }

    public Report(User user) {
        this.id = (int) (Math.random() * 10000000);
        this.totalOfDays = CalculateTotalOfDays(user);
        this.totalOfCarbon = CalculateTotalOfCarbon(user);
        this.user = user;
        this.created_at = LocalDateTime.now();
    }

    public void PrintReport() {

        double dayQuantityCheck = this.totalOfDays < 1 ? 0 : this.totalOfCarbon / this.totalOfDays;
        this.dayAverage = dayQuantityCheck;
        System.out.println("Daily consumption of carbon is : " + dayQuantityCheck + "Kg");
        double monthQuantityCheck = this.totalOfDays < 30 ? this.totalOfCarbon : this.totalOfCarbon / (this.totalOfDays / 30);
        this.monthAverage = monthQuantityCheck;
        System.out.println("Monthly consumption of carbon is : " + monthQuantityCheck + "KG");
        double yearQuantityCheck = this.totalOfDays < 365 ? this.totalOfCarbon : this.totalOfCarbon / (this.totalOfDays / 365);
        this.yearAverage = yearQuantityCheck;
        System.out.println("Daily consumption of carbon is : " + yearQuantityCheck + "KG");

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

    public static void filterByDays(User user) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM");
        double totalCarbon;
        long totalDays;
        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            totalCarbon = consumption.getCarbonQuantity();
            totalDays = ChronoUnit.DAYS.between(consumption.getStart_date(), consumption.getEnd_date());
            totalDays++;
            double average = totalCarbon / totalDays;
            int i = 0;
            LocalDate start = consumption.getStart_date();
            while (i < totalDays) {
                String formattedDay = start.format(formatter);
                System.out.println(formattedDay + " " + average);
                start = start.plusDays(1);
                i++;
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void filterByWeeks(User user) {
        double totalCarbon;

        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            totalCarbon = consumption.getCarbonQuantity();
            int weekNumberStart = consumption.getStart_date().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            int weekNumberEnd = consumption.getEnd_date().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

            double average = totalCarbon / (weekNumberEnd - weekNumberStart + 1);
            int i = weekNumberStart;
            while (i <= weekNumberEnd) {
                System.out.println("week " + i + " :" + " " + average);
                i++;
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void filterByMonths(User user) {
        double totalCarbon;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            totalCarbon = consumption.getCarbonQuantity();
            long monthsBetween = ChronoUnit.MONTHS.between(consumption.getStart_date(), consumption.getEnd_date());

            double average = totalCarbon / monthsBetween;
            LocalDate start = consumption.getStart_date();
            int i = 1;
            while ( i<= monthsBetween) {
                String formattedMonth = start.format(formatter);
                System.out.println(formattedMonth + " " + average);
                start = start.minusMonths(1);
                i++;
            }
            System.out.println();
            System.out.println();
        }
    }
}
