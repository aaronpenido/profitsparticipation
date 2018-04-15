import exceptions.InvalidAnnualPerformanceValue;
import exceptions.InvalidJobTitleException;
import models.*;
import utils.ConsoleManager;
import utils.IOManager;

public class Main {

    public static void main(String[] args)  {

        IOManager ioManager = new ConsoleManager();

        try {

            final int numberOfEmployees = ioManager.readNumberOfEmployees();
            final double profitMargin = ioManager.readProfitMargin();
            final JobTitle jobTitle = ioManager.readJobTitle();
            final int annualPerformanceValue = ioManager.readAnnualPerformanceValue();

            throwExceptionIfAnnualPerformanceValueIsInvalid(annualPerformanceValue);

            Employee employee = createEmployee(jobTitle, annualPerformanceValue);

            ProfitParticipationCalculator profitParticipationCalculator =
                    new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

            final double profitParticipationValue = profitParticipationCalculator.calculate();

            ioManager.writeProfitParticipationValue(profitParticipationValue);

        } catch (NumberFormatException numberFormatException) {
            ioManager.writeError(numberFormatException.getMessage());
        } catch (InvalidJobTitleException invalidJobTitleException) {
            ioManager.writeError(invalidJobTitleException.getMessage());
        } catch (Exception exception) {
            ioManager.writeError(exception.getMessage());
        }
    }

    private static void throwExceptionIfAnnualPerformanceValueIsInvalid(int annualPerformanceValue) throws Exception {

        if (annualPerformanceValue < 1 || annualPerformanceValue > 5) {
            throw new InvalidAnnualPerformanceValue();
        }
    }

    private static Employee createEmployee(final JobTitle jobTitle, final int annualPerformanceValue) throws InvalidJobTitleException {
        return Employee.employeeFromJobTitle(jobTitle, annualPerformanceValue);
    }
}
