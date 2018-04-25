package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.IOManager;

public class Trainee extends Employee {

    public Trainee(IOManager ioManager) throws InvalidAnnualPerformanceValueException {
        super(ioManager);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }
}
