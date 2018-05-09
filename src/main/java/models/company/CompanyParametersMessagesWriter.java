package models.company;

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
        ioWriter.write("Please inform 'yes' or 'no' if an intern is allowed to participate:");
    }
}
