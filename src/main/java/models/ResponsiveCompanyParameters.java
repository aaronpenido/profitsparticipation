package models;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.io.IOReader;
import models.io.IOWriter;

public class ResponsiveCompanyParameters implements CompanyParametersReader {

    private IOWriter ioWriter;
    private IOReader ioReader;

    public ResponsiveCompanyParameters(IOWriter ioWriter, IOReader ioReader) {
        this.ioWriter = ioWriter;
        this.ioReader = ioReader;
    }

    @Override
    public Integer readNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        try {
            writeNumberOfEmployeesMessage();
            return Integer.parseInt(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    @Override
    public Double readProfitMarginValue() throws InvalidProfitMarginValueException {
        try {
            writeProfitMarginMessage();
            return Double.parseDouble(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidProfitMarginValueException();
        }
    }

    @Override
    public Boolean readAllowInternParticipationValue() throws InvalidAllowInternParticipationValueException {
        writeAllowInternParticipationMessage();
        String value = ioReader.read();

        if(value.equalsIgnoreCase("yes")) {
            return true;
        }

        if(value.equalsIgnoreCase("no")) {
            return false;
        }

        throw new InvalidAllowInternParticipationValueException();
    }

    private void writeNumberOfEmployeesMessage() {
        ioWriter.write("Please inform the number of employees:");
    }

    private void writeProfitMarginMessage() {
        ioWriter.write("Please inform the profit margin value:");
    }

    private void writeAllowInternParticipationMessage() {
        ioWriter.write("Please inform 'yes' or 'no' if an intern is allowed to participate:");
    }
}
