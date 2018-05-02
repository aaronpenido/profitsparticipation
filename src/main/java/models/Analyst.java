package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOManager;

public class Analyst extends Employee {

    Analyst(IOManager ioManager) throws InvalidAnnualPerformanceValueException {
        super(ioManager);
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
