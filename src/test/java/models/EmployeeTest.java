package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOReader;
import models.io.IOWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {

    @Mock
    private IOReader ioReader;
    @Mock
    private IOWriter ioWriter;

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedNonNumericValue() {

        when(ioReader.read()).thenReturn("invalidAnnualPerformanceValue");

        assertThatThrownBy(() -> new Manager(ioReader, ioWriter))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedValueLowerThanOne() {

        when(ioReader.read()).thenReturn("0");

        assertThatThrownBy(() -> new Manager(ioReader, ioWriter))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedValueGreaterThanOne() {

        when(ioReader.read()).thenReturn("6");

        assertThatThrownBy(() -> new Manager(ioReader, ioWriter))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }
}