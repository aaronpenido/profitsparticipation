package models;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.enums.JobTitle;

public interface EmployeeParameters {

    Integer readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException;
    JobTitle readJobTitle() throws InvalidJobTitleException;
}
