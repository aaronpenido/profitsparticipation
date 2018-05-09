package models.company;

import exceptions.*;
import models.io.IOReader;
import models.io.IOWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BatchCompanyParameters implements CompanyParametersReader {

    private IOReader ioReader;
    private IOWriter ioWriter;
    private List<String> values;

    public BatchCompanyParameters(IOReader ioReader, IOWriter ioWriter) throws InvalidValuesException {
        this.ioReader = ioReader;
        this.ioWriter = ioWriter;
        this.values = getValues();
    }

    @Override
    public Integer readNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        final String key = "numberOfEmployees";

        String numberOfEmployeesLine = values.stream()
                .filter(value -> value.toUpperCase().contains(key.toUpperCase()))
                .findFirst()
                .orElseThrow(InvalidNumberOfEmployeesException::new);
        try {
            return Integer.parseInt(numberOfEmployeesLine.replaceAll("\\D+",""));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    @Override
    public Double readProfitMarginValue() throws InvalidProfitMarginValueException {
        final String key = "profitMarginValue";

        String profitMarginValueLine = values.stream()
                .filter(value -> value.toUpperCase().contains(key.toUpperCase()))
                .findFirst()
                .orElseThrow(InvalidProfitMarginValueException::new);
        try {
            return Double.parseDouble(profitMarginValueLine.replaceAll("\\D+",""));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidProfitMarginValueException();
        }
    }

    @Override
    public Boolean readAllowInternParticipationValue() throws InvalidAllowInternParticipationValueException {
        final String key = "allowInternParticipation";

        String allowInternParticipationValueLine = values.stream()
                .filter(value -> value.toUpperCase().contains(key.toUpperCase()))
                .findFirst()
                .orElseThrow(InvalidAllowInternParticipationValueException::new);

        if(allowInternParticipationValueLine.contains("true")) {
            return true;
        }
        if(allowInternParticipationValueLine.contains("false")) {
            return false;
        }
        throw new InvalidAllowInternParticipationValueException();
    }

    private List<String> getValues() throws InvalidValuesException {
        String values = ioReader.read();

        if(values == null || values.isEmpty()) {
            throw new InvalidValuesException();
        }

        return Arrays.stream(values.split("\n")).collect(Collectors.toList());
    }
}
