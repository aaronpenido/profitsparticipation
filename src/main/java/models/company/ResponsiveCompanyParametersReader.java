package models.company;

import com.sun.javafx.css.converters.BooleanConverter;
import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidBooleanValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.BooleanValue;
import models.io.IOReader;
import models.io.IOWriter;

public class ResponsiveCompanyParametersReader implements CompanyParametersReader {

    private IOReader ioReader;
    private CompanyParametersMessagesWriter companyParametersMessagesWriter;

    public ResponsiveCompanyParametersReader(IOWriter ioWriter, IOReader ioReader) {
        this.companyParametersMessagesWriter = new CompanyParametersMessagesWriter(ioWriter);
        this.ioReader = ioReader;
    }

    @Override
    public Integer readNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        try {
            companyParametersMessagesWriter.writeNumberOfEmployeesMessage();
            return Integer.parseInt(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidNumberOfEmployeesException();
        }
    }

    @Override
    public Double readProfitMarginValue() throws InvalidProfitMarginValueException {
        try {
            companyParametersMessagesWriter.writeProfitMarginMessage();
            return Double.parseDouble(ioReader.read());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidProfitMarginValueException();
        }
    }

    @Override
    public Boolean readAllowInternParticipationValue() throws InvalidAllowInternParticipationValueException {
        try {
            companyParametersMessagesWriter.writeAllowInternParticipationMessage();
            String value = ioReader.read();
            return BooleanValue.fromString(value);
        } catch (InvalidBooleanValueException e) {
            throw new InvalidAllowInternParticipationValueException();
        }
    }
}
