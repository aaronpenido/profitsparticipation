package models.company;

import exceptions.*;
import models.enums.BooleanValue;
import models.io.IOReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BatchCompanyParametersReader implements CompanyParametersReader {

    private IOReader ioReader;
    private List<String> values;

    public BatchCompanyParametersReader(IOReader ioReader) throws InvalidValuesException {
        this.ioReader = ioReader;
        this.values = getValues();
    }

    @Override
    public Integer readNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        final String key = "numberOfEmployees";

        String numberOfEmployeesLine = getValueFromKey(key)
                .orElseThrow(InvalidNumberOfEmployeesException::new);
        try {
            return Integer.parseInt(getNumberFromString(numberOfEmployeesLine));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    @Override
    public Double readProfitMarginValue() throws InvalidProfitMarginValueException {
        final String key = "profitMarginValue";

        String profitMarginValueLine = getValueFromKey(key)
                .orElseThrow(InvalidProfitMarginValueException::new);
        try {
            return Double.parseDouble(getNumberFromString(profitMarginValueLine));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidProfitMarginValueException();
        }
    }

    @Override
    public Boolean readAllowInternParticipationValue() throws InvalidAllowInternParticipationValueException {
        final String key = "allowInternParticipation";

        String allowInternParticipationValue = values.stream()
                .filter(value -> value.toUpperCase().contains(key.toUpperCase()))
                .filter(value -> value.split(":").length > 1)
                .map(value -> value.split(":")[1].trim())
                .findFirst()
                .orElseThrow(InvalidAllowInternParticipationValueException::new);

        try {
            return BooleanValue.fromString(allowInternParticipationValue);
        } catch (InvalidBooleanValueException e) {
            throw new InvalidAllowInternParticipationValueException();
        }
    }

    private List<String> getValues() throws InvalidValuesException {
        String values = ioReader.read();

        if(values == null || values.isEmpty()) {
            throw new InvalidValuesException();
        }

        return Arrays.stream(values.split("\n")).collect(Collectors.toList());
    }

    private Optional<String> getValueFromKey(String key) {
        return values.stream()
                .filter(value -> value.toUpperCase().contains(key.toUpperCase()))
                .findFirst();
    }

    private String getNumberFromString(String numberOfEmployeesLine) {
        return numberOfEmployeesLine.replaceAll("\\D+","");
    }
}
