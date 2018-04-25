package models;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import utils.ProfitParticipationIOManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {

    @Mock
    ProfitParticipationIOManager profitParticipationIOManager;

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 99999;
        final Employee employee = mock(Employee.class);

        when(profitParticipationIOManager.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(profitParticipationIOManager.readProfitMarginValue()).thenReturn(profitMargin);

        final Company company = new Company(profitParticipationIOManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100000;
        final Employee employee = mock(Employee.class);

        when(profitParticipationIOManager.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(profitParticipationIOManager.readProfitMarginValue()).thenReturn(profitMargin);

        final Company company = new Company(profitParticipationIOManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100001;
        final int employeesAnnualPerformanceValue = 1;

        when(profitParticipationIOManager.readAnnualPerformanceValue()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Trainee(profitParticipationIOManager);

        when(profitParticipationIOManager.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(profitParticipationIOManager.readProfitMarginValue()).thenReturn(profitMargin);

        final Company company = new Company(profitParticipationIOManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final int employeesAnnualPerformanceValue = 1;
        final double expectedEmployeesProfitParticipationValue = 8000;

        when(profitParticipationIOManager.readAnnualPerformanceValue()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Trainee(profitParticipationIOManager);

        when(profitParticipationIOManager.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(profitParticipationIOManager.readProfitMarginValue()).thenReturn(profitMargin);

        final Company company = new Company(profitParticipationIOManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final int employeesAnnualPerformanceValue = 1;
        final double expectedEmployeesProfitParticipationValue = 16000;

        when(profitParticipationIOManager.readAnnualPerformanceValue()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Analyst(profitParticipationIOManager);

        when(profitParticipationIOManager.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(profitParticipationIOManager.readProfitMarginValue()).thenReturn(profitMargin);

        final Company company = new Company(profitParticipationIOManager);


        double profitParticipationValue = company.calculateProfitParticipationValue(employee);
        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final int employeesAnnualPerformanceValue = 1;
        final double expectedEmployeesProfitParticipationValue = 24000;

        when(profitParticipationIOManager.readAnnualPerformanceValue()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Manager(profitParticipationIOManager);

        when(profitParticipationIOManager.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(profitParticipationIOManager.readProfitMarginValue()).thenReturn(profitMargin);

        final Company company = new Company(profitParticipationIOManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }
}