package models;

public class Trainee extends Employee {

    public Trainee(final int annualPerformanceValue) {
        super(annualPerformanceValue);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }
}
