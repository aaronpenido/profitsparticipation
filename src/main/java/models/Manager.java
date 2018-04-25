package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.ProfitParticipationIOManager;

public class Manager extends Employee {

    public Manager(ProfitParticipationIOManager profitParticipationIOManager) throws InvalidAnnualPerformanceValueException {
        super(profitParticipationIOManager);
    }

    @Override
    public int jobTitleMultiplier() {
        return 3;
    }
}
