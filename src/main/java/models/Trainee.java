package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOReader;
import models.io.IOWriter;

public class Trainee extends Employee {

    Trainee(IOReader ioReader, IOWriter ioWriter) throws InvalidAnnualPerformanceValueException {
        super(ioReader, ioWriter);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
