package models;

public class Analyst extends Employee {

    public Analyst(final int annualPerformanceValue) {
        super(annualPerformanceValue);
    }

    @Override
    public int jobTitleMultiplicator() {
        return 2;
    }
}
