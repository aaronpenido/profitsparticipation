package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import exceptions.InvalidValuesException;
import models.enums.JobTitle;
import models.io.IOReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static models.enums.JobTitle.MANAGER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BatchEmployeeParametersTest {

    @Mock
    private IOReader ioReader;
    private BatchEmployeeParameters batchEmployeeParameters;

    @Test
    public void throwExceptionWhenValuesAreNull() {
        when(ioReader.read()).thenReturn(null);

        assertThatThrownBy(() -> new BatchEmployeeParameters(ioReader))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void throwExceptionWhenValuesAreEmpty() {
        when(ioReader.read()).thenReturn("");

        assertThatThrownBy(() -> new BatchEmployeeParameters(ioReader))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException, InvalidValuesException {
        when(ioReader.read()).thenReturn("annualPerformanceValue: 1");

        batchEmployeeParameters = new BatchEmployeeParameters(ioReader);

        Integer annualPerformanceValue = batchEmployeeParameters.readAnnualPerformanceValue();

        assertThat(annualPerformanceValue).isEqualTo(1);
    }

    @Test
    public void throwExceptionWhenAnnualPerformanceValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("annualPerformanceValue: invalid");

        batchEmployeeParameters = new BatchEmployeeParameters(ioReader);

        assertThatThrownBy(() -> batchEmployeeParameters.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoAnnualPerformanceValue() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("invalid: invalid");

        batchEmployeeParameters = new BatchEmployeeParameters(ioReader);

        assertThatThrownBy(() -> batchEmployeeParameters.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void readJobTitle() throws InvalidValuesException, InvalidJobTitleException {
        when(ioReader.read()).thenReturn("jobTitle: Manager");

        batchEmployeeParameters = new BatchEmployeeParameters(ioReader);

        JobTitle jobTitle = batchEmployeeParameters.readJobTitle();

        assertThat(jobTitle).isEqualTo(MANAGER);
    }

    @Test
    public void throwExceptionWhenJobTitleIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("jobTitle: invalid");

        batchEmployeeParameters = new BatchEmployeeParameters(ioReader);

        assertThatThrownBy(() -> batchEmployeeParameters.readJobTitle())
                .isInstanceOf(InvalidJobTitleException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoJobTitle() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("invalid: invalid");

        batchEmployeeParameters = new BatchEmployeeParameters(ioReader);

        assertThatThrownBy(() -> batchEmployeeParameters.readJobTitle())
                .isInstanceOf(InvalidJobTitleException.class);
    }
}