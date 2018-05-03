package models;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.io.IOWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import models.io.IOReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {

    @Mock
    private IOReader ioReader;
    @Mock
    private IOWriter ioWriter;

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber()
            throws InvalidProfitMarginValueException, InvalidNumberOfEmployeesException, InvalidAllowInternParticipationValueException {

        final int numberOfEmployees = 10;
        final int profitMargin = 99999;
        final Employee employee = mock(Employee.class);
        final String isInternAllowedToParticipate = "no";

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber()
            throws InvalidProfitMarginValueException, InvalidNumberOfEmployeesException, InvalidAllowInternParticipationValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100000;
        final String isInternAllowedToParticipate = "no";

        final Employee employee = mock(Employee.class);

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber()
            throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException,
            InvalidNumberOfEmployeesException, InvalidAllowInternParticipationValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 100001;
        final String isInternAllowedToParticipate = "no";

        final String employeesAnnualPerformanceValue = "1";

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        when(ioReader.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Trainee(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue() throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException,
            InvalidNumberOfEmployeesException, InvalidAllowInternParticipationValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String isInternAllowedToParticipate = "no";

        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 8000;

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        when(ioReader.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Trainee(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue() throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException,
            InvalidNumberOfEmployeesException, InvalidAllowInternParticipationValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String isInternAllowedToParticipate = "no";

        final String employeesAnnualPerformanceValue = "1";
        final double expectedEmployeesProfitParticipationValue = 16000;

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        when(ioReader.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Analyst(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);
        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue() throws InvalidAnnualPerformanceValueException, InvalidProfitMarginValueException,
            InvalidNumberOfEmployeesException, InvalidAllowInternParticipationValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final String isInternAllowedToParticipate = "no";

        final double expectedEmployeesProfitParticipationValue = 24000;

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        when(ioReader.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Manager(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void internsProfitParticipationValueWhenIsAllowed() throws InvalidAllowInternParticipationValueException,
            InvalidProfitMarginValueException, InvalidNumberOfEmployeesException, InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final String employeesAnnualPerformanceValue = "1";
        final String isInternAllowedToParticipate = "yes";

        final double expectedEmployeesProfitParticipationValue = 8000;

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        when(ioReader.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Intern(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void internsProfitParticipationIsZeroWhenIsNotAllowed() throws InvalidAllowInternParticipationValueException,
            InvalidProfitMarginValueException, InvalidNumberOfEmployeesException, InvalidAnnualPerformanceValueException {

        final int numberOfEmployees = 10;
        final double profitMargin = 200000;

        final String employeesAnnualPerformanceValue = "1";
        final String isInternAllowedToParticipate = "no";

        final double expectedEmployeesProfitParticipationValue = 0;

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn(isInternAllowedToParticipate);

        final Company company = new Company(ioReader, ioWriter);

        when(ioReader.read()).thenReturn(employeesAnnualPerformanceValue);
        final Employee employee = new Intern(ioReader, ioWriter);

        double profitParticipationValue = company.calculateProfitParticipationValue(employee);

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void throwInvalidNumberOfEmployeesExceptionWhenNumberOfEmployeesIsInvalid() {
        when(ioReader.read()).thenReturn("invalidNumberOfEmployees");

        assertThatThrownBy(() -> new Company(ioReader, ioWriter))
                .isInstanceOf(InvalidNumberOfEmployeesException.class);
    }

    @Test
    public void throwInvalidProfitMarginValueExceptionWhenNumberOfEmployeesIsInvalid() {
        final int numberOfEmployees = 10;

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf("invalidProfitMargin"));

        assertThatThrownBy(() -> new Company(ioReader, ioWriter))
                .isInstanceOf(InvalidProfitMarginValueException.class);
    }

    @Test
    public void throwInvalidAllowInternParticipationValueExceptionWhenNumberOfEmployeesIsInvalid() {
        final int numberOfEmployees = 10;
        final double profitMargin = 200000;

        when(ioReader.read())
                .thenReturn(String.valueOf(numberOfEmployees))
                .thenReturn(String.valueOf(profitMargin))
                .thenReturn("invalidIsInternAllowedToParticipate");

        assertThatThrownBy(() -> new Company(ioReader, ioWriter))
                .isInstanceOf(InvalidAllowInternParticipationValueException.class);
    }
}