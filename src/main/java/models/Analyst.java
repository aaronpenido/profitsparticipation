package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.ProfitParticipationIOManager;

public class Analyst extends Employee {

    public Analyst(ProfitParticipationIOManager profitParticipationIOManager) throws InvalidAnnualPerformanceValueException {
        super(profitParticipationIOManager);
    }

    @Override
    public int jobTitleMultiplier() {
        return 2;
    }
}
