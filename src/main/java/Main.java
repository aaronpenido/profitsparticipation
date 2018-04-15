import exceptions.*;
import models.*;
import utils.CompanyBuilder;
import utils.ConsoleManager;
import utils.EmployeeBuilder;
import utils.ProfitParticipationIOManager;

public class Main {

    public static void main(String[] args)  {

        ProfitParticipationIOManager profitParticipationIOManager =
                new ProfitParticipationIOManager(new ConsoleManager());

        try {

            Company company = instantiateCompanyFromInputValues(profitParticipationIOManager);
            Employee employee = instantiateEmployeeFromInputValues(profitParticipationIOManager);

            double profitParticipationValue = calculateProfitParticipationValue(company, employee);

            profitParticipationIOManager.writeProfitParticipationValue(profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            profitParticipationIOManager.writeErrorFromProfitParticipationException(exception);
        }
    }

    private static Company instantiateCompanyFromInputValues(ProfitParticipationIOManager profitParticipationIOManager)
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        CompanyBuilder companyBuilder = new CompanyBuilder(profitParticipationIOManager);

        return companyBuilder.build();
    }

    private static Employee instantiateEmployeeFromInputValues(ProfitParticipationIOManager profitParticipationIOManager)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeBuilder employeeBuilder = new EmployeeBuilder(profitParticipationIOManager);

        return employeeBuilder.build();
    }

    private static double calculateProfitParticipationValue(Company company, Employee employee) {

        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(company, employee);

        return profitParticipationCalculator.calculate();
    }
}
