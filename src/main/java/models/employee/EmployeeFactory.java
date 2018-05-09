package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.enums.JobTitle;

public class EmployeeFactory {

    private EmployeeParametersReader employeeParameters;

    public EmployeeFactory(EmployeeParametersReader employeeParameters) {
        this.employeeParameters = employeeParameters;
    }

    public Employee getEmployee() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        JobTitle jobTitle = employeeParameters.readJobTitle();

        return employeeFromJobTitle(jobTitle);
    }

    private Employee employeeFromJobTitle(final JobTitle jobTitle)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        switch (jobTitle) {
            case ANALYST:
                return new Analyst(employeeParameters);
            case MANAGER:
                return new Manager(employeeParameters);
            case TRAINEE:
                return new Trainee(employeeParameters);
            case INTERN:
                return new Intern(employeeParameters);
            default:
                throw new InvalidJobTitleException();
        }
    }
}
