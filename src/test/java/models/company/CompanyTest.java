package models.company;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.employee.*;
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
    private EmployeeParametersReader employeeParametersReader;

    @Mock
    CompanyParametersReader companyParametersReader;

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber()
            throws InvalidAllowInternParticipationValueException, InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 99999;
        final Employee employee = mock(Employee.class);
        final boolean isInternAllowedToParticipate = false;

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber()
            throws InvalidAllowInternParticipationValueException, InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100000;
        final boolean isInternAllowedToParticipate = false;

        final Employee employee = mock(Employee.class);

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);
        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber()
            throws InvalidAnnualPerformanceValueException, InvalidAllowInternParticipationValueException,
            InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100001;
        final boolean isInternAllowedToParticipate = false;

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);

        when(employeeParametersReader.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Trainee(employeeParametersReader);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue() throws InvalidAnnualPerformanceValueException, InvalidAllowInternParticipationValueException,
            InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;
        final double expectedEmployeesProfitParticipationValue = 8000;

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);

        when(employeeParametersReader.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Trainee(employeeParametersReader);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue() throws InvalidAnnualPerformanceValueException, InvalidAllowInternParticipationValueException,
            InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;
        final double expectedEmployeesProfitParticipationValue = 16000;

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);

        when(employeeParametersReader.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Analyst(employeeParametersReader);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);
        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue() throws InvalidAnnualPerformanceValueException, InvalidAllowInternParticipationValueException,
            InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;

        final double expectedEmployeesProfitParticipationValue = 24000;

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);

        when(employeeParametersReader.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Manager(employeeParametersReader);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void internsProfitParticipationValueWhenIsAllowed() throws InvalidAnnualPerformanceValueException, InvalidAllowInternParticipationValueException,
            InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = true;

        final double expectedEmployeesProfitParticipationValue = 8000;

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);

        when(employeeParametersReader.readAnnualPerformanceValue()).thenReturn(1);
        final Employee employee = new Intern(employeeParametersReader);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void internsProfitParticipationIsZeroWhenIsNotAllowed()
            throws InvalidAllowInternParticipationValueException, InvalidProfitMarginValueException, InvalidNumberOfEmployeesException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final boolean isInternAllowedToParticipate = false;

        final double expectedEmployeesProfitParticipationValue = 0;

        when(companyParametersReader.readNumberOfEmployees()).thenReturn(numberOfEmployees);
        when(companyParametersReader.readProfitMarginValue()).thenReturn(profitMargin);
        when(companyParametersReader.readAllowInternParticipationValue()).thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(companyParametersReader);

        final int employeesAnnualPerformanceValue = 1;
        final Employee employee = mock(Intern.class);
        when(employee.getAnnualPerformanceValue()).thenReturn(employeesAnnualPerformanceValue);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }
}