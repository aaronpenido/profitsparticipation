import exceptions.*;
import models.*;
import utils.ConsoleManager;
import utils.EmployeeBuilder;
import utils.IOManager;
import utils.ProfitParticipationIOManager;

public class Main {

    public static void main(String[] args)  {

        IOManager ioManager = new ConsoleManager();

        ProfitParticipationIOManager profitParticipationIOManager =
                new ProfitParticipationIOManager(ioManager);

        try {

            Company company = new Company(ioManager);
            Employee employee = instantiateEmployeeFromInputValues(profitParticipationIOManager);

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            profitParticipationIOManager.writeProfitParticipationValue(profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            profitParticipationIOManager.writeErrorFromProfitParticipationException(exception);
        }
    }

    private static Employee instantiateEmployeeFromInputValues(ProfitParticipationIOManager profitParticipationIOManager)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeBuilder employeeBuilder = new EmployeeBuilder(profitParticipationIOManager);

        return employeeBuilder.build();
    }
}
