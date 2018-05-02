package exceptions;

public class InvalidAllowInternParticipationValueException extends ProfitParticipationException {

    public InvalidAllowInternParticipationValueException() {
        super("Invalid value to determine if an intern is allowed to participate.");
    }
}
