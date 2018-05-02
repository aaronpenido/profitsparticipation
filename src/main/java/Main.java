import exceptions.*;
import models.*;
import models.io.ConsoleManager;
import models.EmployeeFactory;
import models.io.IOManager;

public class Main {

    public static void main(String[] args)  {

        IOManager ioManager = new ConsoleManager();

        try {

            Company company = instantiateCompanyFromInputValues(ioManager);
            Employee employee = instantiateEmployeeFromInputValues(ioManager);

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(ioManager, profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            writeErrorFromProfitParticipationException(ioManager, exception);
        }
    }

    private static Company instantiateCompanyFromInputValues(IOManager ioManager)
            throws InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        CompanyFactory companyFactory = new CompanyFactory(ioManager);

        return companyFactory.getCompany();
    }

    private static Employee instantiateEmployeeFromInputValues(IOManager ioManager)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeFactory employeeFactory = new EmployeeFactory(ioManager);

        return employeeFactory.getEmployee();
    }

    private static void writeProfitParticipationValue(IOManager ioManager, double profitParticipationValue) {
        ioManager.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    private static void writeErrorFromProfitParticipationException(IOManager ioManager,
                                                                   ProfitParticipationException profitParticipationException) {
        ioManager.writeError(profitParticipationException.getMessage());
    }
}
