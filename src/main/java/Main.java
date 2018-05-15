import exceptions.InvalidValuesException;
import models.calculator.ProfitParticipationCalculator;
import models.company.BatchCompanyParametersReader;
import models.company.CompanyParametersReader;
import models.company.CommandLineCompanyParametersReader;
import models.employee.BatchEmployeeParametersReader;
import models.employee.EmployeeParametersReader;
import models.employee.CommandLineEmployeeParametersReader;
import models.io.*;

public class Main {

    private static CompanyParametersReader companyParametersReader;
    private static EmployeeParametersReader employeeParameters;
    private static IOReader ioReader;

    public static void main(String[] args) throws InvalidValuesException {
        IOWriter ioWriter = new ConsoleWriter();

        calculateFromConsole(ioWriter);
        calculateFromFile(ioWriter);
    }

    private static void calculateFromConsole(IOWriter ioWriter) {
        ioReader = new ConsoleReader();

        companyParametersReader = new CommandLineCompanyParametersReader(ioWriter, ioReader);
        employeeParameters = new CommandLineEmployeeParametersReader(ioWriter, ioReader);

        calculate(ioWriter);
    }

    private static void calculateFromFile(IOWriter ioWriter) throws InvalidValuesException {
        String filePath = "/Users/apenido/IdeaProjects/profitsparticipation/src/main/resources/ProfitParticipationValues.txt";
        ioReader = new FileReader(filePath);

        companyParametersReader = new BatchCompanyParametersReader(ioReader);
        employeeParameters = new BatchEmployeeParametersReader(ioReader);

        calculate(ioWriter);
    }

    private static void calculate(IOWriter ioWriter) {
        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(ioWriter, companyParametersReader, employeeParameters);

        profitParticipationCalculator.calculate();
    }
}
