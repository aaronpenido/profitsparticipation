package models;

import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.io.IOManager;

public class Company {

    private IOManager ioManager;
    private Integer numberOfEmployees;
    private Double profitMarginValue;

    public Company(IOManager ioManager) throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {
        this.ioManager = ioManager;

        writeNumberOfEmployeesMessage();
        this.numberOfEmployees = readNumberOfEmployees();

        writeProfitMarginMessage();
        this.profitMarginValue = readProfitMarginValue();
    }

    private void writeNumberOfEmployeesMessage() {
        ioManager.write("Please inform the number of employees:");
    }

    private Integer readNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        try {
            return Integer.parseInt(ioManager.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    private void writeProfitMarginMessage() {
        ioManager.write("Please inform the profit margin value:");
    }

    private Double readProfitMarginValue() throws InvalidProfitMarginValueException {
        try {
            return Double.parseDouble(ioManager.read());
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

        return profitMarginValue > profitFactor;
    }

    private double getCalculationFactor() {
        return profitMarginValue * 0.4 / numberOfEmployees;
    }
}
