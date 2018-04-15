package exceptions;

public class InvalidNumberOfEmployeesException extends ProfitParticipationException {

    public InvalidNumberOfEmployeesException() {
        super("Invalid number of employees");
    }
}
