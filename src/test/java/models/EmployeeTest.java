package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {

    @Mock
    private IOManager ioManager;

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedNonNumericValue() {

        when(ioManager.read()).thenReturn("invalidAnnualPerformanceValue");

        assertThatThrownBy(() -> new Manager(ioManager))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedValueLowerThanOne() {

        when(ioManager.read()).thenReturn("0");

        assertThatThrownBy(() -> new Manager(ioManager))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedValueGreaterThanOne() {

        when(ioManager.read()).thenReturn("6");

        assertThatThrownBy(() -> new Manager(ioManager))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }
}