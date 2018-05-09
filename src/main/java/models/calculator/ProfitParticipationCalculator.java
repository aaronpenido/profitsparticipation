package models.calculator;

import exceptions.*;
import models.company.Company;
import models.company.CompanyParametersReader;
import models.employee.Employee;
import models.employee.EmployeeFactory;
import models.employee.EmployeeParametersReader;
import models.io.IOWriter;

public class ProfitParticipationCalculator {

    private IOWriter ioWriter;
    private CompanyParametersReader companyParametersReader;
    private EmployeeParametersReader employeeParameters;

    public ProfitParticipationCalculator(IOWriter ioWriter,
                                         CompanyParametersReader companyParametersReader,
                                         EmployeeParametersReader employeeParameters) {
        this.ioWriter = ioWriter;
        this.companyParametersReader = companyParametersReader;
        this.employeeParameters = employeeParameters;
    }

    public void calculate() {
        try {
            Company company = instantiateCompanyFromInputValues();
            Employee employee = instantiateEmployeeFromInputValues();

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            writeErrorFromProfitParticipationException(exception);
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

    private void writeErrorFromProfitParticipationException(ProfitParticipationException profitParticipationException) {
        ioWriter.writeError(profitParticipationException.getMessage());
    }
}
