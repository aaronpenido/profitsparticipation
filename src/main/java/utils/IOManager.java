package utils;

import exceptions.InvalidJobTitleException;
import models.JobTitle;

public interface IOManager {

    int readNumberOfEmployees() throws NumberFormatException;

    double readProfitMargin() throws NumberFormatException;

    JobTitle readJobTitle() throws InvalidJobTitleException;

    int readAnnualPerformanceValue() throws NumberFormatException;

    String read();

    void writeProfitParticipationValue(double profitParticipationValue);

    void writeMessage(String message);

    void writeError(String errorMessage);
}
