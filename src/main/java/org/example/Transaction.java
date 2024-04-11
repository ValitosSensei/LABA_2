package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {


    private String date;
    private double amount;
    private String description;
    private String category;



    public Transaction(String date, double amount, String description) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date parsedDate = dateFormat.parse(date);
            this.date = dateFormat.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        this.amount = amount;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // Доданий новий метод для отримання категорії


    public String getCategory() {
        return category;
    }

    public String getMonthYear() {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return localDate.getMonthValue() + "-" + localDate.getYear();
    }



    @Override
public String toString() {
    return "Transaction{" +
            "date='" + date + '\'' +
            ", amount=" + amount +
            ", description='" + description + '\'' +
            '}';
}
}

