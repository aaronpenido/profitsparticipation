package models.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader implements IOReader {

    private String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() {
        try {
            Path path = Paths.get(filePath);

            StringBuilder data = new StringBuilder();
            Stream<String> lines = Files.lines(path);

            lines.forEach(line -> data.append(line).append("\n"));
            lines.close();

            return data.toString();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }
}
