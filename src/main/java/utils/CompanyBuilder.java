package utils;

import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.Company;

public class CompanyBuilder {

    private IOManager ioManager;

    public CompanyBuilder(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public Company build() throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {
        writeNumberOfEmployeesMessage();
        Integer numberOfEmployees = readNumberOfEmployees();

        writeProfitMarginMessage();
        Double profitMarginValue = readProfitMarginValue();

        return new Company(numberOfEmployees, profitMarginValue);
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
}
