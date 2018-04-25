package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.IOManager;

public class Analyst extends Employee {

    public Analyst(IOManager ioManager) throws InvalidAnnualPerformanceValueException {
        super(ioManager);
    }

    @Override
    public int jobTitleMultiplier() {
        return 2;
    }
}
