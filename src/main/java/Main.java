import exceptions.InvalidValuesException;
import models.calculator.ProfitParticipationCalculator;
import models.company.BatchCompanyParameters;
import models.company.CompanyParametersReader;
import models.company.ResponsiveCompanyParameters;
import models.employee.BatchEmployeeParameters;
import models.employee.EmployeeParametersReader;
import models.employee.ResponsiveEmployeeParameters;
import models.io.*;

public class Main {

    private static CompanyParametersReader companyParametersReader;
    private static EmployeeParametersReader employeeParameters;

    public static void main(String[] args) throws InvalidValuesException {
        IOWriter ioWriter = new ConsoleWriter();

        calculateFromConsole(ioWriter);
        calculateFromFile(ioWriter);
    }

    private static void calculateFromConsole(IOWriter ioWriter) {
        IOReader ioReader = new ConsoleReader();

        companyParametersReader = new ResponsiveCompanyParameters(ioWriter, ioReader);
        employeeParameters = new ResponsiveEmployeeParameters(ioWriter, ioReader);

        calculate(ioWriter);
    }

    private static void calculateFromFile(IOWriter ioWriter) throws InvalidValuesException {
        String filePath = "/Users/apenido/IdeaProjects/profitsparticipation/src/main/resources/ProfitParticipationValues.txt";
        IOReader ioReader = new FileReader(filePath);

        companyParametersReader = new BatchCompanyParameters(ioReader);
        employeeParameters = new BatchEmployeeParameters(ioReader);

        calculate(ioWriter);
    }

    private static void calculate(IOWriter ioWriter) {
        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(ioWriter, companyParametersReader, employeeParameters);

        profitParticipationCalculator.calculate();
    }
}
