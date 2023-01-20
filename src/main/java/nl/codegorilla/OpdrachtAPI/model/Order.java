package nl.codegorilla.OpdrachtAPI.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Order {

    @Id
    private int id;
    private String title;
    private double amount;
    private LocalDateTime dateOrdered;
    private String description;

    // client FK


    public Order(String title, double amount, String description) {
        this.title = title;
        this.amount = amount;
        this.dateOrdered = LocalDateTime.now();
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(LocalDateTime dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", dateOrdered=" + dateOrdered +
                ", description='" + description + '\'' +
                '}';
    }
}
