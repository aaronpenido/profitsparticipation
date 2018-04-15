package models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.doubleThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProfitParticipationCalculatorTest {

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsLessThanTenThousandTimesEmployeesNumber() {
        final int numberOfEmployees = 10;
        final double profitMargin = 99999;
        final Employee employee = mock(Employee.class);

        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

        double profitParticipationValue = profitParticipationCalculator.calculate();

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsEqualsToZeroWhenProfitMarginIsEqualToTenThousandTimesEmployeesNumber() {
        final int numberOfEmployees = 10;
        final double profitMargin = 100000;
        final Employee employee = mock(Employee.class);

        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

        double profitParticipationValue = profitParticipationCalculator.calculate();

        assertThat(profitParticipationValue).isEqualTo(0);
    }

    @Test
    public void profitParticipationValueIsDifferentFromZeroWhenProfitMarginIsMoreThanTenThousandTimesEmployeesNumber() {
        final int numberOfEmployees = 10;
        final double profitMargin = 100001;
        final int employeesAnnualPerformanceValue = 1;
        final Employee employee = new Trainee(employeesAnnualPerformanceValue);

        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

        double profitParticipationValue = profitParticipationCalculator.calculate();

        assertThat(profitParticipationValue).isNotEqualTo(0);
    }

    @Test
    public void traineesProfitParticipationValue() {
        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final int employeesAnnualPerformanceValue = 1;
        final double expectedEmployeesProfitParticipationValue = 8000;
        final Employee employee = new Trainee(employeesAnnualPerformanceValue);

        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

        double profitParticipationValue = profitParticipationCalculator.calculate();

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void analystsProfitParticipationValue() {
        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final int employeesAnnualPerformanceValue = 1;
        final double expectedEmployeesProfitParticipationValue = 16000;
        final Employee employee = new Analyst(employeesAnnualPerformanceValue);

        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

        double profitParticipationValue = profitParticipationCalculator.calculate();

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }

    @Test
    public void managersProfitParticipationValue() {
        final int numberOfEmployees = 10;
        final double profitMargin = 200000;
        final int employeesAnnualPerformanceValue = 1;
        final double expectedEmployeesProfitParticipationValue = 24000;
        final Employee employee = new Manager(employeesAnnualPerformanceValue);

        ProfitParticipationCalculator profitParticipationCalculator =
                new ProfitParticipationCalculator(numberOfEmployees, profitMargin, employee);

        double profitParticipationValue = profitParticipationCalculator.calculate();

        assertThat(profitParticipationValue).isEqualTo(expectedEmployeesProfitParticipationValue);
    }
}