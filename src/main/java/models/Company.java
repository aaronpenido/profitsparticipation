package models;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.io.IOManager;

public class Company {

    private IOManager ioManager;
    private Integer numberOfEmployees;
    private Double profitMarginValue;
    private Boolean isInternAllowedToParticipate;

    public Company(IOManager ioManager) throws InvalidNumberOfEmployeesException,
            InvalidProfitMarginValueException, InvalidAllowInternParticipationValueException {

        this.ioManager = ioManager;

        writeNumberOfEmployeesMessage();
        this.numberOfEmployees = readNumberOfEmployees();

        writeProfitMarginMessage();
        this.profitMarginValue = readProfitMarginValue();

        writeAllowInternParticipationMessage();
        this.isInternAllowedToParticipate = readAllowInternParticipationValue();
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

    private void writeAllowInternParticipationMessage() {
        ioManager.write("Please inform 'yes' or 'no' if an intern is allowed to participate:");
    }

    private Boolean readAllowInternParticipationValue() throws InvalidAllowInternParticipationValueException {
        String value = ioManager.read();

        if(value.equalsIgnoreCase("yes")) {
            return true;
        }

        if(value.equalsIgnoreCase("no")) {
            return false;
        }

        throw new InvalidAllowInternParticipationValueException();
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
