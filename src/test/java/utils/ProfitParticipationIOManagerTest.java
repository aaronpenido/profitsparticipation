package utils;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.JobTitle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfitParticipationIOManagerTest {

    @Test
    public void readValidNumberOfEmployeesFromConsole() throws InvalidNumberOfEmployeesException {
        int expectedNumberOfEmployees = 100;
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn(String.valueOf(expectedNumberOfEmployees));

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        int numberOfEmployees = profitParticipationIOManager.readNumberOfEmployees();

        assertThat(numberOfEmployees).isEqualTo(expectedNumberOfEmployees);
    }

    @Test
    public void throwsInvalidNumberOfEmployeesExceptionWhenReadingInvalidNumberOfEmployeesFromConsole() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn("invalidValue");

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        assertThatThrownBy(() -> profitParticipationIOManager.readNumberOfEmployees())
                .isInstanceOf(InvalidNumberOfEmployeesException.class);
    }

    @Test
    public void readValidProfitMarginValueFromConsole() throws InvalidProfitMarginValueException {
        int expectedProfitMarginValue = 10000;
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn(String.valueOf(expectedProfitMarginValue));

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        double profitMarginValue = profitParticipationIOManager.readProfitMarginValue();

        assertThat(profitMarginValue).isEqualTo(expectedProfitMarginValue);
    }

    @Test
    public void throwsInvalidProfitMarginValueExceptionWhenReadingInvalidMarginProfitValueFromConsole() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn("invalidValue");

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        assertThatThrownBy(() -> profitParticipationIOManager.readProfitMarginValue())
                .isInstanceOf(InvalidProfitMarginValueException.class);
    }

    @Test
    public void readValidJobTitleFromConsole() throws InvalidJobTitleException {
        JobTitle expectedJobTitle = JobTitle.ANALYST;
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn(String.valueOf(expectedJobTitle));

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        JobTitle jobTitle = profitParticipationIOManager.readJobTitle();

        assertThat(jobTitle).isEqualTo(expectedJobTitle);
    }

    @Test
    public void throwsInvalidJobTitleExceptionWhenReadingInvalidJobTitleFromConsole() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn("invalidValue");

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        assertThatThrownBy(() -> profitParticipationIOManager.readJobTitle())
                .isInstanceOf(InvalidJobTitleException.class);
    }

    @Test
    public void readValidAnnualPerformanceValueFromConsole() throws InvalidAnnualPerformanceValueException {
        int expectedAnnualPerformanceValue = 1;
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn(String.valueOf(expectedAnnualPerformanceValue));

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        int annualPerformanceValue = profitParticipationIOManager.readAnnualPerformanceValue();

        assertThat(annualPerformanceValue).isEqualTo(expectedAnnualPerformanceValue);
    }

    @Test
    public void throwsInvalidAnnualPerformanceValueExceptionWhenReadingInvalidAnnualPerformanceValueFromConsole() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn("invalidValue");

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        assertThatThrownBy(() -> profitParticipationIOManager.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwsInvalidAnnualPerformanceValueExceptionWhenReadingAnnualPerformanceValueFromConsoleGreaterThanFive() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn("6");

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        assertThatThrownBy(() -> profitParticipationIOManager.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwsInvalidAnnualPerformanceValueExceptionWhenReadingAnnualPerformanceValueFromConsoleMinorThanOne() {
        ConsoleManager consoleManager = mock(ConsoleManager.class);

        when(consoleManager.read()).thenReturn("0");

        ProfitParticipationIOManager profitParticipationIOManager = new ProfitParticipationIOManager(consoleManager);

        assertThatThrownBy(() -> profitParticipationIOManager.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }
}