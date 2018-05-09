package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import exceptions.InvalidValuesException;
import models.enums.JobTitle;
import models.io.IOReader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BatchEmployeeParametersReader implements EmployeeParametersReader {

    private IOReader ioReader;
    private List<String> values;

    public BatchEmployeeParametersReader(IOReader ioReader) throws InvalidValuesException {
        this.ioReader = ioReader;
        this.values = getValues();
    }

    @Override
    public Integer readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException {
        final String key = "annualPerformanceValue";

        String annualPerformanceLine = values.stream()
                .filter(value -> value.toUpperCase().contains(key.toUpperCase()))
                .findFirst()
                .orElseThrow(InvalidAnnualPerformanceValueException::new);
        try {
            return Integer.parseInt(annualPerformanceLine.replaceAll("\\D+",""));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    @Override
    public JobTitle readJobTitle() throws InvalidJobTitleException {
        final String key = "jobTitle";

        String jobTitle = values.stream()
                .filter(value -> value.toUpperCase().contains(key.toUpperCase()))
                .filter(value -> value.split(":").length > 1)
                .map(value -> value.split(":")[1].trim())
                .findFirst()
                .orElseThrow(InvalidJobTitleException::new);

        return jobTitleFromString(jobTitle);
    }

    private JobTitle jobTitleFromString(String jobTitleString) throws InvalidJobTitleException {

        try {
            return JobTitle.valueOf(jobTitleString.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidJobTitleException();
        }
    }

    private List<String> getValues() throws InvalidValuesException {
        String values = ioReader.read();

        if(values == null || values.isEmpty()) {
            throw new InvalidValuesException();
        }

        return Arrays.stream(values.split("\n")).collect(Collectors.toList());
    }
}
