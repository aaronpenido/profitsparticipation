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
}
