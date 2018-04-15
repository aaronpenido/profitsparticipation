package utils;

import exceptions.*;
import models.JobTitle;

public class ProfitParticipationIOManager {

    private IOManager ioManager;

    public ProfitParticipationIOManager(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public int readNumberOfEmployees() throws InvalidNumberOfEmployeesException {

        try {
            writeNumberOfEmployeesMessage();
            return Integer.parseInt(ioManager.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    public double readProfitMarginValue() throws InvalidProfitMarginValueException {

        try {
            writeProfitMarginMessage();
            return Double.parseDouble(ioManager.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidProfitMarginValueException();
        }
    }

    public JobTitle readJobTitle() throws InvalidJobTitleException {
        writeJobTitleMessage();
        return JobTitle.jobTitleFromString(ioManager.read());
    }

    public int readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException {

        try {
            writeAnnualPerformanceValueMessage();
            int annualPerformanceValue = Integer.parseInt(ioManager.read());

            throwExceptionIfAnnualPerformanceValueIsInvalid(annualPerformanceValue);

            return annualPerformanceValue;
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    public void writeErrorFromProfitParticipationException(ProfitParticipationException profitParticipationException) {
        ioManager.writeError(profitParticipationException.getMessage());
    }

    public void writeProfitParticipationValue(double profitParticipationValue) {
        ioManager.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    private void writeNumberOfEmployeesMessage() {
        ioManager.write("Please inform the number of employees:");
    }

    private void writeProfitMarginMessage() {
        ioManager.write("Please inform the profit margin value:");
    }

    private void writeJobTitleMessage() {
        ioManager.write("Please inform the job title:");
    }

    private void writeAnnualPerformanceValueMessage() {
        ioManager.write("Please inform the employee's annual performance value:");
    }

    private void throwExceptionIfAnnualPerformanceValueIsInvalid(
            int annualPerformanceValue) throws InvalidAnnualPerformanceValueException {

        if (annualPerformanceValue < 1 || annualPerformanceValue > 5) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }
}
