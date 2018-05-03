import exceptions.*;
import models.*;
import models.io.ConsoleReader;
import models.EmployeeFactory;
import models.io.ConsoleWriter;
import models.io.IOReader;
import models.io.IOWriter;

public class Main {

    public static void main(String[] args)  {

        IOReader ioReader = new ConsoleReader();
        IOWriter ioWriter = new ConsoleWriter();

        try {

            Company company = new Company(ioReader, ioWriter);
            Employee employee = instantiateEmployeeFromInputValues(ioReader, ioWriter);

            double profitParticipationValue = company.calculateProfitParticipationValue(employee);

            writeProfitParticipationValue(ioWriter, profitParticipationValue);

        } catch (ProfitParticipationException exception) {
            writeErrorFromProfitParticipationException(ioWriter, exception);
        }
    }

    private static Employee instantiateEmployeeFromInputValues(IOReader ioReader, IOWriter ioWriter)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        EmployeeFactory employeeFactory = new EmployeeFactory(ioReader, ioWriter);

        return employeeFactory.getEmployee();
    }

    private static void writeProfitParticipationValue(IOWriter ioWriter, double profitParticipationValue) {
        ioWriter.write(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    private static void writeErrorFromProfitParticipationException(IOWriter ioWriter,
                                                                   ProfitParticipationException profitParticipationException) {
        ioWriter.writeError(profitParticipationException.getMessage());
    }
}
