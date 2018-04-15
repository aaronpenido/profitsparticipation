package exceptions;

public class InvalidJobTitleException extends Exception {

    public InvalidJobTitleException() {
        super("The employee's job title must be Trainee, Analyst or Manager");
    }
}
