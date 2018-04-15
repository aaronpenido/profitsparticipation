package models;

public class Company {

    private int numberOfEmployees;
    private double profitMargin;

    public Company(int numberOfEmployees, double profitMargin) {
        this.numberOfEmployees = numberOfEmployees;
        this.profitMargin = profitMargin;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public double getProfitMargin() {
        return profitMargin;
    }
}
