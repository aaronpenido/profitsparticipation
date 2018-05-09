package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.io.IOReader;
import models.io.IOWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResponsiveEmployeeParametersReaderTest {

    @Mock
    private IOReader ioReader;
    @Mock
    private IOWriter ioWriter;
    private ResponsiveEmployeeParametersReader responsiveEmployeeParameters;

    @Before
    public void setUp() {
        responsiveEmployeeParameters = new ResponsiveEmployeeParametersReader(ioWriter, ioReader);
    }

    @Test
    public void throwInvalidAnnualPerformanceValueExceptionWhenInformedNonNumericValue() {

        when(ioReader.read()).thenReturn("invalidAnnualPerformanceValue");

        assertThatThrownBy(() -> responsiveEmployeeParameters.readAnnualPerformanceValue())
                .isInstanceOf(InvalidAnnualPerformanceValueException.class);
    }

    @Test
    public void throwInvalidJobTitleExceptionIfJobIsInvalid() {

        when(ioReader.read()).thenReturn("invalidJobTitle");

        assertThatThrownBy(() -> responsiveEmployeeParameters.readJobTitle())
                .isInstanceOf(InvalidJobTitleException.class);
    }
}