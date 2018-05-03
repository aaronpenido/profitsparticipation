package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOReader;
import models.io.IOWriter;

public class Intern extends Employee {

    Intern(IOReader ioReader, IOWriter ioWriter) throws InvalidAnnualPerformanceValueException {
        super(ioReader, ioWriter);
    }

    @Override
    int jobTitleMultiplier() {
        return 1;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return company.isInternAllowedToParticipate();
    }
}
