package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.enums.JobTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeFactoryTest {

    @Mock
    private EmployeeParametersReader employeeParameters;

    @Test
    public void getManager() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        when(employeeParameters.readJobTitle()).thenReturn(JobTitle.MANAGER);
        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);

        Employee employee = new EmployeeFactory(employeeParameters).getEmployee();

        assertThat(employee).isInstanceOf(Manager.class);
    }

    @Test
    public void getAnalyst() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        when(employeeParameters.readJobTitle()).thenReturn(JobTitle.ANALYST);
        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);

        Employee employee = new EmployeeFactory(employeeParameters).getEmployee();

        assertThat(employee).isInstanceOf(Analyst.class);
    }

    @Test
    public void getTrainee() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        when(employeeParameters.readJobTitle()).thenReturn(JobTitle.TRAINEE);
        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);

        Employee employee = new EmployeeFactory(employeeParameters).getEmployee();

        assertThat(employee).isInstanceOf(Trainee.class);
    }

    @Test
    public void getIntern() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {
        when(employeeParameters.readJobTitle()).thenReturn(JobTitle.INTERN);
        when(employeeParameters.readAnnualPerformanceValue()).thenReturn(1);

        Employee employee = new EmployeeFactory(employeeParameters).getEmployee();

        assertThat(employee).isInstanceOf(Intern.class);
    }
}