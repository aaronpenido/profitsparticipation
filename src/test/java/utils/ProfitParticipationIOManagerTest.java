package utils;

import exceptions.InvalidNumberOfEmployeesException;
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
}