package models;

public class Trainee extends Employee {

    public Trainee(final int annualPerformanceValue) {
        super(annualPerformanceValue);
    }

    @Override
    public int jobTitleMultiplicator() {
        return 1;
    }
}
