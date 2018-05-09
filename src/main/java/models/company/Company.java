package models.company;

import models.employee.Employee;

public class Company {

    private Integer numberOfEmployees;
    private Double profitMarginValue;
    private Boolean isInternAllowedToParticipate;

    public Company(Integer numberOfEmployees, Double profitMarginValue, Boolean isInternAllowedToParticipate) {
        this.numberOfEmployees = numberOfEmployees;
        this.profitMarginValue = profitMarginValue;
        this.isInternAllowedToParticipate = isInternAllowedToParticipate;
    }

    public double calculateProfitParticipationValue(Employee employee) {
        if (isParticipationAllowed(employee)) {

            final int employeesPerformanceValue = employee.getAnnualPerformanceValue();
            final int jobTitleMultiplier = employee.jobTitleMultiplier();
            final double calculationFactor = getCalculationFactor();

            return employeesPerformanceValue * jobTitleMultiplier * calculationFactor;
        }

        return 0;
    }

    public Boolean isInternAllowedToParticipate() {
        return isInternAllowedToParticipate;
    }

    private boolean isParticipationAllowed(Employee employee) {
        final int profitFactor = 10000 * numberOfEmployees;

        return employee.isAllowedToParticipate(this)
                && profitMarginValue > profitFactor;
    }

    private double getCalculationFactor() {
        return profitMarginValue * 0.4 / numberOfEmployees;
    }
}
