package User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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

    public static long calculateTotalOfDays(User user) {
        long TotalDays = 0;

        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            long daysBetween = ChronoUnit.DAYS.between(consumption.getStart_date(), consumption.getEnd_date());
            TotalDays += daysBetween;
        }
        return TotalDays;


    }

    public static float calculateTotalOfCarbon(User user) {
        float TotalCarbon = 0;

        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            TotalCarbon += consumption.getCarbonQuantity();
        }
        return TotalCarbon;


    }

    public Report(User user) {
        this.id = (int) (Math.random() * 10000000);
        this.totalOfDays = calculateTotalOfDays(user);
        this.totalOfCarbon = calculateTotalOfCarbon(user);
        this.user = user;
        this.created_at = LocalDateTime.now();
    }

    public void printReport() {
        System.out.println("Creating Your Rapport... : ");


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
            LocalDate current = consumption.getStart_date();
            while (i < totalDays) {
                String formattedDay = current.format(formatter);
                System.out.println("For " + formattedDay + " " + current.getYear() + " :" + average);
                current = current.plusDays(1);
                i++;
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void filterByWeeks(User user) {
        double totalCarbon;
        long totalDays;

        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            totalCarbon = consumption.getCarbonQuantity();
            totalDays = ChronoUnit.DAYS.between(consumption.getStart_date(), consumption.getEnd_date());
            int weekNumberStart = getWeekNumber(consumption.getStart_date());
            int weekNumberEnd = getWeekNumber(consumption.getEnd_date());
            LocalDate startOfYear = LocalDate.of(consumption.getStart_date().getYear(), 1, 1);
            totalDays++;
            long daysBetween = ChronoUnit.DAYS.between(startOfYear, consumption.getStart_date()) + 1;
            double average = totalCarbon / totalDays;
            long  leftDays = 7- (daysBetween%7)+1;
            long checkDays = totalDays % 7 -leftDays;
            LocalDate current = consumption.getStart_date();
            for (int i = weekNumberStart; i <= weekNumberEnd; i++) {
                if (i == weekNumberEnd && checkDays != 0) {
                    System.out.println("Week " + i + "  " + current.getYear() + " : Quantity of carbon is " + checkDays * average);
                    break;
                } else if (i == weekNumberStart && daysBetween % 7 != 1) {
                    System.out.println("Week " + i + "  " + current.getYear() + " : Quantity of carbon is " + leftDays * average);
                    continue;
                }

                System.out.println("Week " + i + "  " + current.getYear() + " : Quantity of carbon is " + average * 7);
                current = current.plusDays(7);
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void filterByMonths(User user) {
        double totalCarbon;
        long totalDays;
        double average;
        for (Map.Entry<Integer, Consumption> consumptionEntry : user.getConsumptions().entrySet()) {
            Consumption consumption = consumptionEntry.getValue();
            totalCarbon = consumption.getCarbonQuantity();
            totalDays = ChronoUnit.DAYS.between(consumption.getStart_date(), consumption.getEnd_date());
            totalDays++;
            average = totalCarbon / totalDays;
            int monthLength;
            YearMonth yearMonth;
            LocalDate current = consumption.getStart_date();
            while (!current.isAfter(consumption.getEnd_date())) {
                yearMonth = YearMonth.of(current.getYear(), current.getMonth());
                monthLength = yearMonth.lengthOfMonth();
                if (current.getMonth().equals(consumption.getEnd_date().getMonth()) && current.getYear() == consumption.getEnd_date().getYear()) {
                    System.out.println("Quantity of carbon for : " + current.getMonth() + " " + current.getYear() + " is : " + average * (current.getDayOfMonth()));
                } else if (current.getMonth().equals(consumption.getStart_date().getMonth()) && current.getYear() == consumption.getStart_date().getYear()) {
                    System.out.println("Quantity of carbon for : " + current.getMonth() + " " + current.getYear() + " is : " + average * (monthLength - current.getDayOfMonth() + 1));

                } else {
                    System.out.println("Quantity of carbon for : " + current.getMonth() + " " + current.getYear() + " is : " + average * (monthLength));

                }
                current = current.plusMonths(1).withDayOfMonth(1);
            }
            System.out.println();
            System.out.println();
        }
    }

    public static int getWeekNumber(LocalDate date) {
        int dateDayOfYear = date.getDayOfYear();
        return (dateDayOfYear - 1) / 7 + 1;
    }

    public static int getMonthNumber(LocalDate date) {
        int dateDayOfYear = date.getDayOfYear();
        return (dateDayOfYear - 1) / 30 + 1;
    }


}

