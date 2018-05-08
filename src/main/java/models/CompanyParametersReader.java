package models;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;

public interface CompanyParametersReader {

    Integer readNumberOfEmployees() throws InvalidNumberOfEmployeesException;

    Double readProfitMarginValue() throws InvalidProfitMarginValueException;

    Boolean readAllowInternParticipationValue() throws InvalidAllowInternParticipationValueException;
}
