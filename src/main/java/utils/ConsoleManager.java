package utils;

import exceptions.InvalidJobTitleException;
import models.JobTitle;

import java.util.Scanner;

public class ConsoleManager implements IOManager {

    private Scanner scanner;
    
    public ConsoleManager() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int readNumberOfEmployees() throws NumberFormatException {
        writeMessage("Please inform the number of employees:");
        return Integer.parseInt(read());
    }

    @Override
    public double readProfitMargin() throws NumberFormatException {
        writeMessage("Please inform the profit margin:");
        return Double.parseDouble(read());
    }

    @Override
    public JobTitle readJobTitle() throws InvalidJobTitleException {
        writeMessage("Please inform the job title:");
        return JobTitle.jobTitleFromString(read());
    }

    @Override
    public int readAnnualPerformanceValue() throws NumberFormatException {
        writeMessage("Please inform the employee's annual performance value:");
        return Integer.parseInt(read());
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void writeProfitParticipationValue(final double profitParticipationValue) {
        writeMessage(String.format("The profit participation value is: %.2f", profitParticipationValue));
    }

    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void writeError(String errorMessage) {
        System.err.println(errorMessage);
    }
}
