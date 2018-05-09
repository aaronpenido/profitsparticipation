package models;

import exceptions.InvalidBooleanValueException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BooleanValue {

    TRUE("true", "yes", "y"), FALSE("false", "no", "n");

    private List<String> values;

    BooleanValue(String... values) {
        this.values = Arrays.asList(values);
    }

    public String getValues() {
        return values.stream()
                .map(s -> String.format("'%s'", s))
                .collect(Collectors.joining(","));
    }

    public static Boolean fromString(String value) throws InvalidBooleanValueException {
        if(TRUE.getValues().contains(value.toLowerCase())) {
            return true;
        }

        if(FALSE.getValues().contains(value.toLowerCase())) {
            return false;
        }

        throw new InvalidBooleanValueException();
    }
}
