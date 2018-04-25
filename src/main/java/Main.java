import exceptions.*;
import models.*;
import utils.ConsoleManager;
import utils.EmployeeBuilder;
import utils.ProfitParticipationIOManager;

public class Main {

    public static void main(String[] args)  {

        ProfitParticipationIOManager profitParticipationIOManager =
                new ProfitParticipationIOManager(new ConsoleManager());

        try {

            Company company = new Company(profitParticipationIOManager);
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
