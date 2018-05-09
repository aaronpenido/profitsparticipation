package models.company;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import models.io.IOReader;
import models.io.IOWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResponsiveCompanyParametersReaderTest {

    @Mock
    private IOReader ioReader;
    @Mock
    private IOWriter ioWriter;
    private CompanyParametersReader companyParametersReader;

    @Before
    public void setUp() {
        companyParametersReader = new ResponsiveCompanyParameters(ioWriter, ioReader);
    }

    @Test
    public void getNumberOfEmployees() throws InvalidNumberOfEmployeesException {
        Integer expectedNumberOfEmployees = 100;

        when(ioReader.read()).thenReturn(String.valueOf(expectedNumberOfEmployees));

        Integer numberOfEmployees = companyParametersReader.readNumberOfEmployees();

        assertThat(numberOfEmployees).isEqualTo(expectedNumberOfEmployees);
    }

    @Test
    public void throwInvalidNumberOfEmployeesExceptionWhenNumberOfEmployeesIsInvalid() {
        when(ioReader.read()).thenReturn("invalidNumberOfEmployees");

        assertThatThrownBy(() -> companyParametersReader.readNumberOfEmployees())
                .isInstanceOf(InvalidNumberOfEmployeesException.class);
    }

    @Test
    public void throwInvalidProfitMarginValueExceptionWhenNumberOfEmployeesIsInvalid() {
        when(ioReader.read()).thenReturn(String.valueOf("invalidProfitMargin"));

        assertThatThrownBy(() -> companyParametersReader.readProfitMarginValue())
                .isInstanceOf(InvalidProfitMarginValueException.class);
    }

    @Test
    public void throwInvalidAllowInternParticipationValueExceptionWhenNumberOfEmployeesIsInvalid() {
        when(ioReader.read()).thenReturn("invalidIsInternAllowedToParticipate");

        assertThatThrownBy(() -> companyParametersReader.readAllowInternParticipationValue())
                .isInstanceOf(InvalidAllowInternParticipationValueException.class);
    }
}