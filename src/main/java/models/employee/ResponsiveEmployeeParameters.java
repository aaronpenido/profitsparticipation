package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.employee.EmployeeParameters;
import models.enums.JobTitle;
import models.io.IOReader;
import models.io.IOWriter;

public class ResponsiveEmployeeParameters implements EmployeeParameters {

    private IOWriter ioWriter;
    private IOReader ioReader;

    public ResponsiveEmployeeParameters(IOWriter ioWriter, IOReader ioReader) {
        this.ioWriter = ioWriter;
        this.ioReader = ioReader;
    }

    @Override
    public Integer readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException {
        try {
            writeAnnualPerformanceValueMessage();
            return Integer.parseInt(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    @Override
    public JobTitle readJobTitle() throws InvalidJobTitleException {
        writeJobTitleMessage();
        return jobTitleFromString(ioReader.read());
    }

    private void writeAnnualPerformanceValueMessage() {
        ioWriter.write("Please inform the employee's annual performance value:");
    }

    private void writeJobTitleMessage() {
        ioWriter.write("Please inform the job title:");
    }

    private JobTitle jobTitleFromString(String jobTitleString) throws InvalidJobTitleException {

        try {
            return JobTitle.valueOf(jobTitleString.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidJobTitleException();
        }
    }
}
