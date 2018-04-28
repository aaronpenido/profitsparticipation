package models.io;

public interface IOManager {

    String read();

    void write(String value);

    void writeError(String errorMessage);
}
