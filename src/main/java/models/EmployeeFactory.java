package models;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.enums.JobTitle;
import models.io.IOReader;
import models.io.IOWriter;

public class EmployeeFactory {

    private IOReader ioReader;
    private IOWriter ioWriter;

    public EmployeeFactory(IOReader ioReader, IOWriter ioWriter) {
        this.ioReader = ioReader;
        this.ioWriter = ioWriter;
    }

    public Employee getEmployee() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        writeJobTitleMessage();
        JobTitle jobTitle = readJobTitle();

        return employeeFromJobTitle(jobTitle);
    }

    private Employee employeeFromJobTitle(final JobTitle jobTitle)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        switch (jobTitle) {
            case ANALYST:
                return new Analyst(ioReader, ioWriter);
            case MANAGER:
                return new Manager(ioReader, ioWriter);
            case TRAINEE:
                return new Trainee(ioReader, ioWriter);
            case INTERN:
                return new Intern(ioReader, ioWriter);
            default:
                throw new InvalidJobTitleException();
        }
    }

    private JobTitle readJobTitle() throws InvalidJobTitleException {
        return jobTitleFromString(ioReader.read());
    }

    private JobTitle jobTitleFromString(String jobTitleString) throws InvalidJobTitleException {

        try {
            return JobTitle.valueOf(jobTitleString.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidJobTitleException();
        }
    }

    private void writeJobTitleMessage() {
        ioWriter.write("Please inform the job title:");
    }
}
