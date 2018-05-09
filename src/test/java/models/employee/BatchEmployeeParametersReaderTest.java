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
public class BatchEmployeeParametersReaderTest {

    @Mock
    private IOReader ioReader;
    private BatchEmployeeParametersReader batchEmployeeParametersReader;

    @Test
    public void throwExceptionWhenValuesAreNull() {
        when(ioReader.read()).thenReturn(null);

        assertThatThrownBy(() -> new BatchEmployeeParametersReader(ioReader))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void throwExceptionWhenValuesAreEmpty() {
        when(ioReader.read()).thenReturn("");

        assertThatThrownBy(() -> new BatchEmployeeParametersReader(ioReader))
                .isInstanceOf(InvalidValuesException.class);
    }

    @Test
    public void readAnnualPerformanceValue() throws InvalidAnnualPerformanceValueException, InvalidValuesException {
        when(ioReader.read()).thenReturn("annualPerformanceValue: 1");

        batchEmployeeParametersReader = new BatchEmployeeParametersReader(ioReader);

        Integer annualPerformanceValue = batchEmployeeParametersReader.readAnnualPerformanceValue();

        assertThat(annualPerformanceValue).isEqualTo(1);
    }

    @Test
    public void throwExceptionWhenAnnualPerformanceValueIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("annualPerformanceValue: invalid");

        batchEmployeeParametersReader = new BatchEmployeeParametersReader(ioReader);

        assertThatThrownBy(() -> batchEmployeeParametersReader.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoAnnualPerformanceValue() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("invalid: invalid");

        batchEmployeeParametersReader = new BatchEmployeeParametersReader(ioReader);

        assertThatThrownBy(() -> batchEmployeeParametersReader.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void readJobTitle() throws InvalidValuesException, InvalidJobTitleException {
        when(ioReader.read()).thenReturn("jobTitle: Manager");

        batchEmployeeParametersReader = new BatchEmployeeParametersReader(ioReader);

        JobTitle jobTitle = batchEmployeeParametersReader.readJobTitle();

        assertThat(jobTitle).isEqualTo(MANAGER);
    }

    @Test
    public void throwExceptionWhenJobTitleIsInvalid() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("jobTitle: invalid");

        batchEmployeeParametersReader = new BatchEmployeeParametersReader(ioReader);

        assertThatThrownBy(() -> batchEmployeeParametersReader.readJobTitle())
                .isInstanceOf(InvalidJobTitleException.class);
    }

    @Test
    public void throwExceptionWhenThereIsNoJobTitle() throws InvalidValuesException {
        when(ioReader.read()).thenReturn("invalid: invalid");

        batchEmployeeParametersReader = new BatchEmployeeParametersReader(ioReader);

        assertThatThrownBy(() -> batchEmployeeParametersReader.readJobTitle())
                .isInstanceOf(InvalidJobTitleException.class);
    }
}