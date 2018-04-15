package models;

import exceptions.InvalidJobTitleException;

public abstract class Employee {

    private int annualPerformanceValue;

    public Employee(final int annualPerformanceValue) {
        this.annualPerformanceValue = annualPerformanceValue;
    }

    abstract int jobTitleMultiplicator();

    public int getAnnualPerformanceValue() {
        return annualPerformanceValue;
    }

    public static final Employee employeeFromJobTitle(
            final JobTitle jobTitle,
            final int annualPerformanceValue) throws InvalidJobTitleException {

        switch (jobTitle) {
            case ANALYST:
                return new Analyst(annualPerformanceValue);
            case MANAGER:
                return new Manager(annualPerformanceValue);
            case TRAINEE:
                return new Trainee(annualPerformanceValue);
            default:
                throw new InvalidJobTitleException();
        }
    }
}
