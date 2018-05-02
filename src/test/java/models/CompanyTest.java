package models;

import exceptions.InvalidAnnualPerformanceValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import models.io.IOManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber() {

        final int numberOfEmployees = 10;
        final double profitMargin = 99999;
        final Employee employee = mock(Employee.class);


        final Company company = new Company(numberOfEmployees, profitMargin);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber() {

        final int numberOfEmployees = 10;
        final double profitMargin = 100000;
        final Employee employee = mock(Employee.class);

        final Company company = new Company(numberOfEmployees, profitMargin);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber()
            throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100001;
        final String employeesAnnualPerformanceValue = "1";

        final Company company = new Company(numberOfEmployees, profitMargin);

        IOManager ioManager = mock(IOManager.class);
        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);

        final Employee employee = new Trainee(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 8000;

        final Company company = new Company(numberOfEmployees, profitMargin);

        IOManager ioManager = mock(IOManager.class);
        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);

        Employee employee = new Trainee(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 16000;

        final Company company = new Company(numberOfEmployees, profitMargin);

        IOManager ioManager = mock(IOManager.class);
        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);

        Employee employee = new Analyst(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);
        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 24000;

        Company company = new Company(numberOfEmployees, profitMargin);

        IOManager ioManager = mock(IOManager.class);
        when(ioManager.read()).thenReturn(employeesAnnualPerformanceValue);

        Employee employee = new Manager(ioManager);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }
}