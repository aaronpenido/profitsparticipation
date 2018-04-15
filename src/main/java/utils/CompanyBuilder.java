package utils;

import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.Company;

public class CompanyBuilder {

    private ProfitParticipationIOManager profitParticipationIOManager;

    public CompanyBuilder(ProfitParticipationIOManager profitParticipationIOManager) {
        this.profitParticipationIOManager = profitParticipationIOManager;
    }

    public Company build() throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        final int numberOfEmployees = profitParticipationIOManager.readNumberOfEmployees();
        final double profitMargin = profitParticipationIOManager.readProfitMarginValue();

        return new Company(numberOfEmployees, profitMargin);
    }
}
