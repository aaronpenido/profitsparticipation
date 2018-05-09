package models.company;

import exceptions.InvalidAllowInternParticipationValueException;
import exceptions.InvalidNumberOfEmployeesException;
import exceptions.InvalidProfitMarginValueException;
import exceptions.InvalidValuesException;
import models.io.IOReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BatchCompanyParametersReaderTest {

    @Mock
    private IOReader ioReader;
    BatchCompanyParametersReader batchCompanyParametersReader;

    @Test
    public void throwExceptionWhenValuesAreNull() {
        when(ioReader.read()).thenReturn(null);

        assertThatThrownBy(() -> new BatchCompanyParametersReader(ioReader))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void throwExceptionWhenValuesAreEmpty() {
        when(ioReader.read()).thenReturn("");

        assertThatThrownBy(() -> new BatchCompanyParametersReader(ioReader))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void readNumberOfEmployees() throws InvalidNumberOfEmployeesException, InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: 100");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        Integer numberOfEmployees = batchCompanyParametersReader.readNumberOfEmployees();

        assertThat(numberOfEmployees).isEqualTo(100);
    }

    @Test
    public void throwExceptionWhenNumberOfEmployeesValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: invalid");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        assertThatThrownBy(() -> batchCompanyParametersReader.readNumberOfEmployees())
                .isInstanceOf(InvalidNumberOfEmployeesException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoNumberOfEmployees() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("profitMarginValue: 1");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        assertThatThrownBy(() -> batchCompanyParametersReader.readNumberOfEmployees())
                .isInstanceOf(InvalidNumberOfEmployeesException.class);
    }

    @Test
    public void readProfitMarginValue() throws InvalidValuesException, InvalidProfitMarginValueException {
        when(ioReader.read()).thenReturn("profitMarginValue: 10000");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        Double profitMarginValue = batchCompanyParametersReader.readProfitMarginValue();

        assertThat(profitMarginValue).isEqualTo(10000);
    }

    @Test
    public void throwExceptionProfitMarginValueValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("profitMarginValue: invalid");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        assertThatThrownBy(() -> batchCompanyParametersReader.readProfitMarginValue())
                .isInstanceOf(InvalidProfitMarginValueException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoProfitMarginValue() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: 1");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        assertThatThrownBy(() -> batchCompanyParametersReader.readProfitMarginValue())
                .isInstanceOf(InvalidProfitMarginValueException.class);
    }

    @Test
    public void readAllowInternParticipation() throws InvalidValuesException, InvalidAllowInternParticipationValueException {
        when(ioReader.read()).thenReturn("allowInternParticipation: true");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        Boolean allowInternParticipationValue = batchCompanyParametersReader.readAllowInternParticipationValue();

        assertThat(allowInternParticipationValue).isTrue();
    }

    @Test
    public void readDoesNotAllowInternParticipation() throws InvalidValuesException, InvalidAllowInternParticipationValueException {
        when(ioReader.read()).thenReturn("allowInternParticipation: false");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        Boolean allowInternParticipationValue = batchCompanyParametersReader.readAllowInternParticipationValue();

        assertThat(allowInternParticipationValue).isFalse();
    }

    @Test
    public void throwExceptionWhenAllowInternParticipationValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("allowInternParticipation: invalid");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        assertThatThrownBy(() -> batchCompanyParametersReader.readAllowInternParticipationValue())
                .isInstanceOf(InvalidAllowInternParticipationValueException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoAllowInternParticipationValue() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("numberOfEmployees: 1");

        batchCompanyParametersReader = new BatchCompanyParametersReader(ioReader);

        assertThatThrownBy(() -> batchCompanyParametersReader.readAllowInternParticipationValue())
                .isInstanceOf(InvalidAllowInternParticipationValueException.class);
    }
}