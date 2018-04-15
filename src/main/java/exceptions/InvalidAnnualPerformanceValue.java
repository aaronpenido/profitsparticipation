package exceptions;

public class InvalidAnnualPerformanceValue extends Exception {

    public InvalidAnnualPerformanceValue() {
        super("Annual performance value must be between 1 and 5");
    }
}
