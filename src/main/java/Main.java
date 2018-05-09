import exceptions.InvalidValuesException;
import models.io.*;

public class Main {

    public static void main(String[] args) throws InvalidValuesException {

        IOReader ioReader = new FileReader("/Users/apenido/IdeaProjects/profitsparticipation/src/main/resources/ProfitParticipationValues.txt");
        IOWriter ioWriter = new ConsoleWriter();

        ProfitParticipationCalculator profitParticipationCalculator =
                new BatchProfitParticipationCalculator(ioReader, ioWriter);

        profitParticipationCalculator.calculate();
    }
}
