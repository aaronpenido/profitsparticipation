package models;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.io.IOManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeFactoryTest {

    @Mock
    private IOManager ioManager;

    @Test
    public void throwInvalidJobTitleExceptionIfJobIsInvalid() {

        when(ioManager.read()).thenReturn("invalidJobTitle");

        EmployeeFactory employeeFactory = new EmployeeFactory(ioManager);

        assertThatThrownBy(() -> employeeFactory.getEmployee())
                .isInstanceOf(InvalidJobTitleException.class);
    }

    @Test
    public void getManager() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        String annualPerformanceValue = "1";

        when(ioManager.read()).thenReturn("manager").thenReturn(annualPerformanceValue);

        Employee employee = new EmployeeFactory(ioManager).getEmployee();

        assertThat(employee).isInstanceOf(Manager.class);
    }

    @Test
    public void getAnalyst() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        String annualPerformanceValue = "1";

        when(ioManager.read()).thenReturn("analyst").thenReturn(annualPerformanceValue);

        Employee employee = new EmployeeFactory(ioManager).getEmployee();

        assertThat(employee).isInstanceOf(Analyst.class);
    }

    @Test
    public void getTrainee() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        String annualPerformanceValue = "1";

        when(ioManager.read()).thenReturn("trainee").thenReturn(annualPerformanceValue);

        Employee employee = new EmployeeFactory(ioManager).getEmployee();

        assertThat(employee).isInstanceOf(Trainee.class);
    }

    @Test
    public void getIntern() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        String annualPerformanceValue = "1";

        when(ioManager.read()).thenReturn("intern").thenReturn(annualPerformanceValue);

        Employee employee = new EmployeeFactory(ioManager).getEmployee();

        assertThat(employee).isInstanceOf(Intern.class);
    }
}