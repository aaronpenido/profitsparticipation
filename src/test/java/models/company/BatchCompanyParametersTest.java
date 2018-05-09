package models.company;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import exceptions.InvalidValuesException;
import models.io.IOReader;
import models.io.IOWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BatchCompanyParametersTest {

    @Mock
    private IOReader ioReader;
    @Mock
    private IOWriter ioWriter;
    BatchCompanyParameters batchCompanyParameters;

    @Test
    public void throwExceptionWhenValuesAreNull() {
        when(ioReader.read()).thenReturn(null);

        assertThatThrownBy(() -> new BatchCompanyParameters(ioReader, ioWriter))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void throwExceptionWhenValuesAreEmpty() {
        when(ioReader.read()).thenReturn("");

        assertThatThrownBy(() -> new BatchCompanyParameters(ioReader, ioWriter))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void readNumberOfEmployees() throws InvalidNumberOfEmployeesException, InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: 100");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        Integer numberOfEmployees = batchCompanyParameters.readNumberOfEmployees();

        assertThat(numberOfEmployees).isEqualTo(100);
    }

    @Test
    public void throwExceptionWhenNumberOfEmployeesValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: invalid");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        assertThatThrownBy(() -> batchCompanyParameters.readNumberOfEmployees())
                .isInstanceOf(InvalidNumberOfEmployeesException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoNumberOfEmployees() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("profitMarginValue: 1");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        assertThatThrownBy(() -> batchCompanyParameters.readNumberOfEmployees())
                .isInstanceOf(InvalidNumberOfEmployeesException.class);
    }

    @Test
    public void readProfitMarginValue() throws InvalidValuesException, InvalidProfitMarginValueException {
        when(ioReader.read()).thenReturn("profitMarginValue: 10000");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        Double profitMarginValue = batchCompanyParameters.readProfitMarginValue();

        assertThat(profitMarginValue).isEqualTo(10000);
    }

    @Test
    public void throwExceptionProfitMarginValueValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("profitMarginValue: invalid");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        assertThatThrownBy(() -> batchCompanyParameters.readProfitMarginValue())
                .isInstanceOf(InvalidProfitMarginValueException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoProfitMarginValue() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: 1");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        assertThatThrownBy(() -> batchCompanyParameters.readProfitMarginValue())
                .isInstanceOf(InvalidProfitMarginValueException.class);
    }

    @Test
    public void readAllowInternParticipation() throws InvalidValuesException, InvalidAllowInternParticipationValueException {
        when(ioReader.read()).thenReturn("allowInternParticipation: true");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        Boolean allowInternParticipationValue = batchCompanyParameters.readAllowInternParticipationValue();

        assertThat(allowInternParticipationValue).isTrue();
    }

    @Test
    public void readDoesNotAllowInternParticipation() throws InvalidValuesException, InvalidAllowInternParticipationValueException {
        when(ioReader.read()).thenReturn("allowInternParticipation: false");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        Boolean allowInternParticipationValue = batchCompanyParameters.readAllowInternParticipationValue();

        assertThat(allowInternParticipationValue).isFalse();
    }

    @Test
    public void throwExceptionWhenAllowInternParticipationValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("allowInternParticipation: invalid");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        assertThatThrownBy(() -> batchCompanyParameters.readAllowInternParticipationValue())
                .isInstanceOf(InvalidAllowInternParticipationValueException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoAllowInternParticipationValue() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: 1");

        batchCompanyParameters = new BatchCompanyParameters(ioReader, ioWriter);

        assertThatThrownBy(() -> batchCompanyParameters.readAllowInternParticipationValue())
                .isInstanceOf(InvalidAllowInternParticipationValueException.class);
    }
}