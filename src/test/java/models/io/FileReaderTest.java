package models.io;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReaderTest {

    private String filePath;

    @Before
    public void setUp() {
        filePath = getClass().getClassLoader().getResource("fileTest.txt").getPath();
    }

    @Test
    public void readContentFromFile() {
        FileReader fileReader = new FileReader(filePath);

        String value = fileReader.read();

        assertThat(value).contains("test");
    }
}