package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOManager;

public class Manager extends Employee {

    Manager(IOManager ioManager) throws InvalidAnnualPerformanceValueException {
        super(ioManager);
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
