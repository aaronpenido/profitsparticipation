package models.io;

import java.util.Scanner;

public class ConsoleReader implements IOReader {

    private Scanner scanner;
    
    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
