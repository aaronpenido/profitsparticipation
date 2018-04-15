package exceptions;

public class InvalidProfitMarginValueException extends ProfitParticipationException {

    public InvalidProfitMarginValueException() {
        super("Invalid profit margin value");
    }
}
