package models.io;

public interface IOWriter {

    void write(String value);

    void writeError(String errorMessage);
}
