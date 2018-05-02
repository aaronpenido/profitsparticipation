package models;

public class Company {

    private Integer numberOfEmployees;
    private Double profitMarginValue;

    public Company(Integer numberOfEmployees, Double profitMarginValue) {
        this.numberOfEmployees = numberOfEmployees;
        this.profitMarginValue = profitMarginValue;
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

        return profitMarginValue > profitFactor;
    }

    private double getCalculationFactor() {
        return profitMarginValue * 0.4 / numberOfEmployees;
    }
}
