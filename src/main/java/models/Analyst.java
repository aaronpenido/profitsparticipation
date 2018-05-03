package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOReader;
import models.io.IOWriter;

public class Analyst extends Employee {

    Analyst(IOReader ioReader, IOWriter ioWriter) throws InvalidAnnualPerformanceValueException {
        super(ioReader, ioWriter);
    }

    @Override
    public int jobTitleMultiplier() {
        return 2;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
