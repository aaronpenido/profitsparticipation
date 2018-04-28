package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOManager;

public class Trainee extends Employee {

    Trainee(IOManager ioManager) throws InvalidAnnualPerformanceValueException {
        super(ioManager);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }
}
