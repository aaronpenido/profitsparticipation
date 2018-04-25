package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.ProfitParticipationIOManager;

public abstract class Employee {

    private int annualPerformanceValue;

    public Employee(ProfitParticipationIOManager profitParticipationIOManager) throws InvalidAnnualPerformanceValueException {
        this.annualPerformanceValue = profitParticipationIOManager.readAnnualPerformanceValue();
    }

    abstract int jobTitleMultiplier();

    public int getAnnualPerformanceValue() {
        return annualPerformanceValue;
    }
}
