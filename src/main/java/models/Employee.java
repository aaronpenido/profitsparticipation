package models;

public abstract class Employee {

    private int annualPerformanceValue;

    public Employee(final int annualPerformanceValue) {
        this.annualPerformanceValue = annualPerformanceValue;
    }

    abstract int jobTitleMultiplier();

    public int getAnnualPerformanceValue() {
        return annualPerformanceValue;
    }
}
