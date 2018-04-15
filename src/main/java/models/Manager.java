package models;

public class Manager extends Employee {

    public Manager(final int annualPerformanceValue) {
        super(annualPerformanceValue);
    }

    @Override
    public int jobTitleMultiplicator() {
        return 3;
    }
}
