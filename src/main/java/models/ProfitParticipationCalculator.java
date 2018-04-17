package models;

public class ProfitParticipationCalculator {

    private int numberOfEmployees;
    private double profitMargin;
    private Employee employee;

    public ProfitParticipationCalculator(Company company, Employee employee) {
        this.numberOfEmployees = company.getNumberOfEmployees();
        this.profitMargin = company.getProfitMargin();
        this.employee = employee;
    }

    public double calculate() {

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
