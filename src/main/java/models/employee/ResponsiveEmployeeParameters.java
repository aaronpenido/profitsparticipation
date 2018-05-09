package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.enums.JobTitle;
import models.io.IOReader;
import models.io.IOWriter;

public class ResponsiveEmployeeParameters implements EmployeeParameters {

    private IOReader ioReader;
    private EmployeeParametersMessagesWriter employeeParametersMessagesWriter;

    public ResponsiveEmployeeParameters(IOWriter ioWriter, IOReader ioReader) {
        this.employeeParametersMessagesWriter = new EmployeeParametersMessagesWriter(ioWriter);
        this.ioReader = ioReader;
    }

    @Override
    public Integer readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException {
        try {
            employeeParametersMessagesWriter.writeAnnualPerformanceValueMessage();
            return Integer.parseInt(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    @Override
    public JobTitle readJobTitle() throws InvalidJobTitleException {
        employeeParametersMessagesWriter.writeJobTitleMessage();
        return jobTitleFromString(ioReader.read());
    }

    private JobTitle jobTitleFromString(String jobTitleString) throws InvalidJobTitleException {

        try {
            return JobTitle.valueOf(jobTitleString.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidJobTitleException();
        }
    }
}
