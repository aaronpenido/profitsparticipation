package models;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import utils.IOManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {

    @Mock
    private IOManager ioManager;

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber()
            throws InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final int profitMargin = 99999;
        final Employee employee = mock(Employee.class);

        when(ioManager.read()).thenReturn(String.valueOf(numberOfEmployees)).thenReturn(String.valueOf(profitMargin));

        final Company company = new Company(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber()
            throws InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100000;
        final Employee employee = mock(Employee.class);

        when(ioManager.read()).thenReturn(String.valueOf(numberOfEmployees)).thenReturn(String.valueOf(profitMargin));

        final Company company = new Company(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber()
            throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100001;
        final String employeesAnnualPerformanceValue = "1";

        when(ioManager.read()).thenReturn(String.valueOf(numberOfEmployees)).thenReturn(String.valueOf(profitMargin));

        final Company company = new Company(ioManager);

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Trainee(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue()
            throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 8000;

        when(ioManager.read()).thenReturn(String.valueOf(numberOfEmployees)).thenReturn(String.valueOf(profitMargin));

        final Company company = new Company(ioManager);

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Trainee(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue()
            throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 16000;

        when(ioManager.read()).thenReturn(String.valueOf(numberOfEmployees)).thenReturn(String.valueOf(profitMargin));

        final Company company = new Company(ioManager);

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Analyst(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);
        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue()
            throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 24000;

        when(ioManager.read()).thenReturn(String.valueOf(numberOfEmployees)).thenReturn(String.valueOf(profitMargin));

        final Company company = new Company(ioManager);

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Manager(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }
}