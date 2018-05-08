import exceptions.*;
import models.*;
import models.io.ConsoleReader;
import models.EmployeeFactory;
import models.io.ConsoleWriter;
import models.io.IOReader;
import models.io.IOWriter;

public class Main {

    public static void main(String[] args)  {

        IOReader ioReader = new ConsoleReader();
        IOWriter ioWriter = new ConsoleWriter();
        CompanyParametersReader companyParametersReader = new ResponsiveCompanyParameters(ioWriter, ioReader);
        EmployeeParameters employeeParameters = new ResponsiveEmployeeParameters(ioWriter, ioReader);

        try {

            Company company = instantiateCompanyFromInputValues(companyParametersReader);
            Employee employee = instantiateEmployeeFromInputValues(employeeParameters);

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(ioWriter, profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            writeErrorFromProfitParticipationException(ioWriter, exception);
        }
    }

    private static Employee instantiateEmployeeFromInputValues(EmployeeParameters employeeParameters)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeFactory employeeFactory = new EmployeeFactory(employeeParameters);

        return employeeFactory.getEmployee();
    }

    private static Company instantiateCompanyFromInputValues(CompanyParametersReader companyParametersReader)
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAllowInternParticipationValueException {

        Integer numberOfEmployees = companyParametersReader.readNumberOfEmployees();
        Double profitMarginValue = companyParametersReader.readProfitMarginValue();
        Boolean isInternAllowedToParticipate = companyParametersReader.readAllowInternParticipationValue();

        return new Company(numberOfEmployees, profitMarginValue, isInternAllowedToParticipate);
    }

    private static void writeProfitParticipationValue(IOWriter ioWriter, double profitParticipationValue) {
        ioWriter.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    private static void writeErrorFromProfitParticipationException(IOWriter ioWriter,
                                                                   ProfitParticipationException profitParticipationException) {
        ioWriter.writeError(profitParticipationException.getMessage());
    }
}
