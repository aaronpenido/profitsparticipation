import exceptions.*;
import models.*;
import utils.ConsoleManager;
import utils.ProfitParticipationIOManager;

public class Main {

    public static void main(String[] args)  {

        ProfitParticipationIOManager profitParticipationIOManager =
                new ProfitParticipationIOManager(new ConsoleManager());

        try {

            Company company = instantiateCompanyFromInputValues(profitParticipationIOManager);
            Employee employee = instantiateEmployeeFromInputValues(profitParticipationIOManager);

            ProfitParticipationCalculator profitParticipationCalculator =
                    new ProfitParticipationCalculator(company, employee);

            final double profitParticipationValue = profitParticipationCalculator.calculate();

            profitParticipationIOManager.writeProfitParticipationValue(profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            profitParticipationIOManager.writeErrorFromProfitParticipationException(exception);
        }
    }

    private static Company instantiateCompanyFromInputValues(ProfitParticipationIOManager profitParticipationIOManager)
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        final int numberOfEmployees = profitParticipationIOManager.readNumberOfEmployees();
        final double profitMargin = profitParticipationIOManager.readProfitMarginValue();

        return new Company(numberOfEmployees, profitMargin);
    }

    private static Employee instantiateEmployeeFromInputValues(ProfitParticipationIOManager profitParticipationIOManager)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        final JobTitle jobTitle = profitParticipationIOManager.readJobTitle();
        final int annualPerformanceValue = profitParticipationIOManager.readAnnualPerformanceValue();

        return Employee.employeeFromJobTitle(jobTitle, annualPerformanceValue);
    }
}
