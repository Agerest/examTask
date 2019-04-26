package data.model;

import java.sql.Date;

public class Manager {

    private int id;
    private int income;
    private int consumption;
    private Date date;
    private String description;

    public Manager() {
    }

    public Manager(int income, int consumption, Date date, String description) {
        this.income = income;
        this.consumption = consumption;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
