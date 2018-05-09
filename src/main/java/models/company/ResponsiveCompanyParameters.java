package models.company;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.io.IOReader;
import models.io.IOWriter;

public class ResponsiveCompanyParameters implements CompanyParametersReader {

    private IOReader ioReader;
    private CompanyParametersMessagesWriter responsiveCompanyParametersWriter;

    public ResponsiveCompanyParameters(IOWriter ioWriter, IOReader ioReader) {
        this.responsiveCompanyParametersWriter = new CompanyParametersMessagesWriter(ioWriter);
        this.ioReader = ioReader;
    }

    @Override
    public Integer readNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        try {
            responsiveCompanyParametersWriter.writeNumberOfEmployeesMessage();
            return Integer.parseInt(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    @Override
    public Double readProfitMarginValue() throws InvalidProfitMarginValueException {
        try {
            responsiveCompanyParametersWriter.writeProfitMarginMessage();
            return Double.parseDouble(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidProfitMarginValueException();
        }
    }

    @Override
    public Boolean readAllowInternParticipationValue() throws InvalidAllowInternParticipationValueException {
        responsiveCompanyParametersWriter.writeAllowInternParticipationMessage();
        String value = ioReader.read();

        if(value.equalsIgnoreCase("yes")) {
            return true;
        }

        if(value.equalsIgnoreCase("no")) {
            return false;
        }

        throw new InvalidAllowInternParticipationValueException();
    }


}
