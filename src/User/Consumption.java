package User;

import Database.Database;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Consumption {
    private int id;
    private float carbonQuantity;
    private LocalDate start_date;
    private LocalDate end_date;
    private User user;
    private final LocalDateTime created_date;

    public Consumption(float carbonQuantity, LocalDate start_date, LocalDate end_date, User user) {
        this.id = (int) (Math.random() * 1000000000);
        this.carbonQuantity = carbonQuantity;
        this.start_date = start_date;
        this.end_date = end_date;
        this.user = user;
        this.created_date = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCarbonQuantity() {
        return carbonQuantity;
    }

    public void setCarbonQuantity(float carbonQuantity) {
        this.carbonQuantity = carbonQuantity;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }


}
