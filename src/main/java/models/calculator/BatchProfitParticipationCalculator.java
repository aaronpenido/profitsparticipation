package models.calculator;

import exceptions.*;
import models.company.BatchCompanyParameters;
import models.company.Company;
import models.company.CompanyParametersReader;
import models.employee.*;
import models.io.IOReader;
import models.io.IOWriter;

public class BatchProfitParticipationCalculator implements ProfitParticipationCalculator {

    IOReader ioReader;
    IOWriter ioWriter;
    CompanyParametersReader companyParametersReader;
    EmployeeParameters employeeParameters;

    public BatchProfitParticipationCalculator(IOReader ioReader, IOWriter ioWriter) throws InvalidValuesException {
        this.ioReader = ioReader;
        this.ioWriter = ioWriter;
        this.companyParametersReader = new BatchCompanyParameters(ioReader);
        this.employeeParameters = new BatchEmployeeParameters(ioReader);
    }

    @Override
    public void calculate() {
        try {
            Company company = instantiateCompanyFromInputValues();
            Employee employee = instantiateEmployeeFromInputValues();

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            ioWriter.writeError(exception.getMessage());
        }
    }

    private Employee instantiateEmployeeFromInputValues() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeFactory employeeFactory = new EmployeeFactory(employeeParameters);

        return employeeFactory.getEmployee();
    }

    private Company instantiateCompanyFromInputValues() throws InvalidNumberOfEmployeesException,
            InvalidProfitMarginValueException, InvalidAllowInternParticipationValueException {

        Integer numberOfEmployees = companyParametersReader.readNumberOfEmployees();
        Double profitMarginValue = companyParametersReader.readProfitMarginValue();
        Boolean isInternAllowedToParticipate = companyParametersReader.readAllowInternParticipationValue();

        return new Company(numberOfEmployees, profitMarginValue, isInternAllowedToParticipate);
    }

    private void writeProfitParticipationValue(double profitParticipationValue) {
        ioWriter.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }
}
