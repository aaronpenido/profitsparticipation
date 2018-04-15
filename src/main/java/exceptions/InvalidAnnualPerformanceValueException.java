package exceptions;

public class InvalidAnnualPerformanceValueException extends ProfitParticipationException {

    public InvalidAnnualPerformanceValueException() {
        super("Annual performance value must be between 1 and 5");
    }
}
