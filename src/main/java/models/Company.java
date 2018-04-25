package models;

import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import utils.IOManager;

public class Company {

    private IOManager ioManager;
    private int numberOfEmployees;
    private double profitMargin;

    public Company(IOManager ioManager) throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {
        this.ioManager = ioManager;

        writeNumberOfEmployeesMessage();
        readNumberOfEmployees();

        writeProfitMarginMessage();
        readProfitMarginValue();
    }

    private void writeNumberOfEmployeesMessage() {
        ioManager.write("Please inform the number of employees:");
    }

    private void readNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        try {
            this.numberOfEmployees = Integer.parseInt(ioManager.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    private void writeProfitMarginMessage() {
        ioManager.write("Please inform the profit margin value:");
    }

    private void readProfitMarginValue() throws InvalidProfitMarginValueException {
        try {
            this.profitMargin = Double.parseDouble(ioManager.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidProfitMarginValueException();
        }
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
