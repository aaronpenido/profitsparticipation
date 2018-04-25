package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.ProfitParticipationIOManager;

public class Trainee extends Employee {

    public Trainee(ProfitParticipationIOManager profitParticipationIOManager) throws InvalidAnnualPerformanceValueException {
        super(profitParticipationIOManager);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }
}
