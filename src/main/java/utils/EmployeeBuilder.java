package utils;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.*;

public class EmployeeBuilder {

    private ProfitParticipationIOManager profitParticipationIOManager;

    public EmployeeBuilder(ProfitParticipationIOManager profitParticipationIOManager) {
        this.profitParticipationIOManager = profitParticipationIOManager;
    }

    public Employee build() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        final JobTitle jobTitle = profitParticipationIOManager.readJobTitle();
        final int annualPerformanceValue = profitParticipationIOManager.readAnnualPerformanceValue();

        return employeeFromJobTitle(jobTitle, annualPerformanceValue);
    }

    private Employee employeeFromJobTitle(
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
