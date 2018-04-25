package models;

import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import utils.ProfitParticipationIOManager;

public class Company {

    private int numberOfEmployees;
    private double profitMargin;

    public Company(ProfitParticipationIOManager profitParticipationIOManager)
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        this.numberOfEmployees = profitParticipationIOManager.readNumberOfEmployees();
        this.profitMargin = profitParticipationIOManager.readProfitMarginValue();
    }

    public double calculateProfitParticipationValue(Employee employee) {

        if (allowParticipation()) {

            final int employeesPerformanceValue = employee.getAnnualPerformanceValue();
            final int jobTitleMultiplier = employee.jobTitleMultiplier();
            final double calculationFactor = getCalculationFactor();

            return employeesPerformanceValue * jobTitleMultiplier * calculationFactor;
        }

        return 0;
    }

    private boolean allowParticipation() {
        final int profitFactor = 10000 * numberOfEmployees;

        return profitMargin > profitFactor;
    }

    private double getCalculationFactor() {
        return profitMargin * 0.4 / numberOfEmployees;
    }
}
