package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOManager;

public class Intern extends Employee {

    Intern(IOManager ioManager) throws InvalidAnnualPerformanceValueException {
        super(ioManager);
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
