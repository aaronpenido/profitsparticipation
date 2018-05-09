import models.io.ConsoleReader;
import models.io.ConsoleWriter;
import models.io.IOReader;
import models.io.IOWriter;

public class Main {

    public static void main(String[] args)  {

        IOReader ioReader = new ConsoleReader();
        IOWriter ioWriter = new ConsoleWriter();

        ResponsiveProfitParticipationCalculator responsiveProfitParticipationCalculator =
                new ResponsiveProfitParticipationCalculator(ioReader, ioWriter);

        responsiveProfitParticipationCalculator.calculate();
    }
}
