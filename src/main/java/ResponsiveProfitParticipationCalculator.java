import exceptions.*;
import models.company.Company;
import models.company.CompanyParametersReader;
import models.company.ResponsiveCompanyParameters;
import models.employee.Employee;
import models.employee.EmployeeFactory;
import models.employee.EmployeeParameters;
import models.employee.ResponsiveEmployeeParameters;
import models.io.IOReader;
import models.io.IOWriter;

public class ResponsiveProfitParticipationCalculator implements ProfitParticipationCalculator {

    IOReader ioReader;
    IOWriter ioWriter;
    CompanyParametersReader companyParametersReader;
    EmployeeParameters employeeParameters;

    public ResponsiveProfitParticipationCalculator(IOReader ioReader, IOWriter ioWriter) {
        this.ioReader = ioReader;
        this.ioWriter = ioWriter;
        companyParametersReader = new ResponsiveCompanyParameters(ioWriter, ioReader);
        employeeParameters = new ResponsiveEmployeeParameters(ioWriter, ioReader);
    }

    @Override
    public void calculate() {
        try {
            Company company = instantiateCompanyFromInputValues();
            Employee employee = instantiateEmployeeFromInputValues();

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(ioWriter, profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            writeErrorFromProfitParticipationException(ioWriter, exception);
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

    private void writeProfitParticipationValue(IOWriter ioWriter, double profitParticipationValue) {
        ioWriter.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    private void writeErrorFromProfitParticipationException(IOWriter ioWriter, ProfitParticipationException profitParticipationException) {
        ioWriter.writeError(profitParticipationException.getMessage());
    }
}
