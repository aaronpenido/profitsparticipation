package models.company;

import models.BooleanValue;
import models.io.IOWriter;

public class CompanyParametersMessagesWriter {

    private IOWriter ioWriter;

    public CompanyParametersMessagesWriter(IOWriter ioWriter) {
        this.ioWriter = ioWriter;
    }

    void writeNumberOfEmployeesMessage() {
        ioWriter.write("Please inform the number of employees:");
    }

    void writeProfitMarginMessage() {
        ioWriter.write("Please inform the profit margin value:");
    }

    void writeAllowInternParticipationMessage() {
        String trueValues = BooleanValue.TRUE.getValues();
        String falseValues = BooleanValue.FALSE.getValues();

        ioWriter.write(String.format("Please inform %s, %s if an intern is allowed to participate:", trueValues, falseValues));
    }
}
