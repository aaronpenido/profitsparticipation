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
    private EmployeeParametersReader employeeParametersReader;

    public ProfitParticipationCalculator(IOWriter ioWriter,
                                         CompanyParametersReader companyParametersReader,
                                         EmployeeParametersReader employeeParametersReader) {
        this.ioWriter = ioWriter;
        this.companyParametersReader = companyParametersReader;
        this.employeeParametersReader = employeeParametersReader;
    }

    public void calculate() {
        try {
            Company company = new Company(companyParametersReader);
            Employee employee = instantiateEmployeeFromInputValues();

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            writeErrorFromProfitParticipationException(exception);
        }
    }

    private Employee instantiateEmployeeFromInputValues() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeFactory employeeFactory = new EmployeeFactory(employeeParametersReader);

        return employeeFactory.getEmployee();
    }

    private void writeProfitParticipationValue(double profitParticipationValue) {
        ioWriter.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    private void writeErrorFromProfitParticipationException(ProfitParticipationException profitParticipationException) {
        ioWriter.writeError(profitParticipationException.getMessage());
    }
}
