package models.io;

public class ConsoleWriter implements IOWriter {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void writeError(String errorMessage) {
        System.err.println(errorMessage);
    }
}
