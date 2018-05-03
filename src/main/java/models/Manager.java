package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOReader;
import models.io.IOWriter;

public class Manager extends Employee {

    Manager(IOReader ioReader, IOWriter ioWriter) throws InvalidAnnualPerformanceValueException {
        super(ioReader, ioWriter);
    }

    @Override
    public int jobTitleMultiplier() {
        return 3;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
