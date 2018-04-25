package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.IOManager;

public class Manager extends Employee {

    public Manager(IOManager ioManager) throws InvalidAnnualPerformanceValueException {
        super(ioManager);
    }

    @Override
    public int jobTitleMultiplier() {
        return 3;
    }
}
