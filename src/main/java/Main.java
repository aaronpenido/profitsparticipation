import exceptions.InvalidValuesException;
import models.calculator.BatchProfitParticipationCalculator;
import models.calculator.ProfitParticipationCalculator;
import models.calculator.ResponsiveProfitParticipationCalculator;
import models.io.*;

public class Main {

    public static void main(String[] args) throws InvalidValuesException {
        IOWriter ioWriter = new ConsoleWriter();

        calculateFromConsole(ioWriter);
        calculateFromFile(ioWriter);
    }

    private static void calculateFromConsole(IOWriter ioWriter) {
        IOReader ioReader = new ConsoleReader();

        ProfitParticipationCalculator profitParticipationCalculator =
                new ResponsiveProfitParticipationCalculator(ioReader, ioWriter);

        profitParticipationCalculator.calculate();
    }

    private static void calculateFromFile(IOWriter ioWriter) throws InvalidValuesException {
        String filePath = "/Users/apenido/IdeaProjects/profitsparticipation/src/main/resources/ProfitParticipationValues.txt";
        IOReader ioReader = new FileReader(filePath);

        ProfitParticipationCalculator profitParticipationCalculator =
                new BatchProfitParticipationCalculator(ioReader, ioWriter);

        profitParticipationCalculator.calculate();
    }
}
