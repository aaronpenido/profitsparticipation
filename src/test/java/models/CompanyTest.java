package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {

    @Mock
    private EmployeeParameters employeeParameters;

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber() {

        final int numberOfEmployees = 10;
        final double profitMargin = 99999;
        final Employee employee = mock(Employee.class);
        final boolean isInternAllowedToParticipate = false;

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber() {

        final int numberOfEmployees = 10;
        final double profitMargin = 100000;
        final boolean isInternAllowedToParticipate = false;

        final Employee employee = mock(Employee.class);

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100001;
        final boolean isInternAllowedToParticipate = false;

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Trainee(employeeParameters);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;
        final double expectedEmployeesProfitParticipationValue = 8000;

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Trainee(employeeParameters);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;
        final double expectedEmployeesProfitParticipationValue = 16000;

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Analyst(employeeParameters);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);
        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;

        final double expectedEmployeesProfitParticipationValue = 24000;

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Manager(employeeParameters);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void internsProfitParticipationValueWhenIsAllowed() throws InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = true;

        final double expectedEmployeesProfitParticipationValue = 8000;

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Intern(employeeParameters);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void internsProfitParticipationIsZeroWhenIsNotAllowed() {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;

        final double expectedEmployeesProfitParticipationValue = 0;

        final Company company = new Company(numberOfEmployees, profitMargin, isInternAllowedToParticipate);

        final int employeesAnnualPerformanceValue = 1;
        final Employee employee = mock(Intern.class);
        when(employee.getAnnualPerformanceValue()).thenReturn(employeesAnnualPerformanceValue);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }
}