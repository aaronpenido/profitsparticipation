package models;

public class Company {

    private int numberOfEmployees;
    private double profitMargin;

    public Company(int numberOfEmployees, double profitMargin) {
        this.numberOfEmployees = numberOfEmployees;
        this.profitMargin = profitMargin;
    }

    public double calculateProfitMargin(Employee employee) {

        double profitParticipationValue = 0;

        if (profitMarginIsGreaterThanProfitFactorToAllowParticipation()) {

            final int employeesPerformanceValue = employee.getAnnualPerformanceValue();
            final int jobTitleMultiplier = employee.jobTitleMultiplier();
            final double calculationFactor = (profitMargin * 0.4 / numberOfEmployees);

            profitParticipationValue = employeesPerformanceValue * jobTitleMultiplier * calculationFactor;
        }

        return profitParticipationValue;
    }

    private boolean profitMarginIsGreaterThanProfitFactorToAllowParticipation() {
        final int profitFactor = 10000 * numberOfEmployees;

        return profitMargin > profitFactor;
    }
}
