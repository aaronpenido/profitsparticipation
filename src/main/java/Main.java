import exceptions.*;
import models.*;
import utils.ConsoleManager;
import utils.ProfitParticipationIOManager;

public class Main {

    public static void main(String[] args)  {

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(new ConsoleManager());

        try {

            final int numberOfEmployees = profitParticipationIOManager.readNumberOfEmployees();
            final double profitMargin = profitParticipationIOManager.readProfitMarginValue();
            final JobTitle jobTitle = profitParticipationIOManager.readJobTitle();
            final int annualPerformanceValue = profitParticipationIOManager.readAnnualPerformanceValue();

            Employee employee = createEmployee(jobTitle, annualPerformanceValue);

            ProfitParticipationCalculator profitParticipationCalculator =
                    new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

            final double profitParticipationValue = profitParticipationCalculator.calculate();

            profitParticipationIOManager.writeProfitParticipationValue(profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            profitParticipationIOManager.writeErrorFromProfitParticipationException(exception);
        }
    }

    private static Employee createEmployee(final JobTitle jobTitle, final int annualPerformanceValue) throws InvalidJobTitleException {
        return Employee.employeeFromJobTitle(jobTitle, annualPerformanceValue);
    }
}
