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

        return employeeFromJobTitle(jobTitle, profitParticipationIOManager);
    }

    private Employee employeeFromJobTitle(final JobTitle jobTitle,
                                          final ProfitParticipationIOManager profitParticipationIOManager)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        switch (jobTitle) {
            case ANALYST:
                return new Analyst(profitParticipationIOManager);
            case MANAGER:
                return new Manager(profitParticipationIOManager);
            case TRAINEE:
                return new Trainee(profitParticipationIOManager);
            default:
                throw new InvalidJobTitleException();
        }
    }
}
