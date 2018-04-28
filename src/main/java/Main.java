import exceptions.*;
import models.*;
import utils.ConsoleManager;
import utils.EmployeeBuilder;
import utils.IOManager;

public class Main {

    public static void main(String[] args)  {

        IOManager ioManager = new ConsoleManager();

        try {

            Company company = new Company(ioManager);
            Employee employee = instantiateEmployeeFromInputValues(ioManager);

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(ioManager, profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            writeErrorFromProfitParticipationException(ioManager, exception);
        }
    }

    private static Employee instantiateEmployeeFromInputValues(IOManager ioManager)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeBuilder employeeBuilder = new EmployeeBuilder(ioManager);

        return employeeBuilder.build();
    }

    private static void writeProfitParticipationValue(IOManager ioManager, double profitParticipationValue) {
        ioManager.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    private static void writeErrorFromProfitParticipationException(IOManager ioManager,
                                                                   ProfitParticipationException profitParticipationException) {
        ioManager.writeError(profitParticipationException.getMessage());
    }
}
