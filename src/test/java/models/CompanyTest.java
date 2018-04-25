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
    IOManager ioManager;

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        final String numberOfEmployees = "10";
        final String profitMargin = "99999";
        final Employee employee = mock(Employee.class);

        when(ioManager.read()).thenReturn(numberOfEmployees).thenReturn(profitMargin);

        final Company company = new Company(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException {

        final String numberOfEmployees = "10";
        final String profitMargin = "100000";
        final Employee employee = mock(Employee.class);

        when(ioManager.read()).thenReturn(numberOfEmployees).thenReturn(profitMargin);

        final Company company = new Company(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final String numberOfEmployees = "10";
        final String profitMargin = "100001";
        final String employeesAnnualPerformanceValue = "1";

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);


        final Employee employee = new Trainee(ioManager);

        when(ioManager.read()).thenReturn(numberOfEmployees).thenReturn(profitMargin);

        final Company company = new Company(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final String numberOfEmployees = "10";
        final String profitMargin = "200000";
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 8000;

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Trainee(ioManager);

        when(ioManager.read()).thenReturn(numberOfEmployees).thenReturn(profitMargin);

        final Company company = new Company(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final String numberOfEmployees = "10";
        final String profitMargin = "200000";
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 16000;

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Analyst(ioManager);

        when(ioManager.read()).thenReturn(numberOfEmployees).thenReturn(profitMargin);

        final Company company = new Company(ioManager);


        double profitParticipationValue = company.calculateProfitParticipationValue(employee);
        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue()
            throws InvalidNumberOfEmployeesException, InvalidProfitMarginValueException, InvalidAnnualPerformanceValueException {

        final String numberOfEmployees = "10";
        final String profitMargin = "200000";
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 24000;

        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Manager(ioManager);

        when(ioManager.read()).thenReturn(numberOfEmployees).thenReturn(profitMargin);

        final Company company = new Company(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }
}