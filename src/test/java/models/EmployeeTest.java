package models;

import exceptions.InvalidAnnualPerformanceValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {

    @Mock
    private EmployeeParameters employeeParameters;

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedValueLowerThanOne() throws InvalidAnnualPerformanceValueException {
        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(0);

        assertThatThrownBy(() -> new Analyst(employeeParameters))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedValueGreaterThanFive() throws InvalidAnnualPerformanceValueException {
        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(6);

        assertThatThrownBy(() -> new Analyst(employeeParameters))
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }
}