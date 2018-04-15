package models;

public class Manager extends Employee {

    public Manager(final int annualPerformanceValue) {
        super(annualPerformanceValue);
    }

    @Override
    public int jobTitleMultiplier() {
        return 3;
    }
}
