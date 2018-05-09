package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.enums.JobTitle;

public interface EmployeeParametersReader {

    Integer readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException;
    JobTitle readJobTitle() throws InvalidJobTitleException;
}
