package models.io;

import java.util.Scanner;

public class ConsoleManager implements IOManager {

    private Scanner scanner;
    
    public ConsoleManager() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void writeError(String errorMessage) {
        System.err.println(errorMessage);
    }
}
