package models.employee;

import models.io.IOWriter;

public class EmployeeParametersMessagesWriter {

    private IOWriter ioWriter;

    public EmployeeParametersMessagesWriter(IOWriter ioWriter) {
        this.ioWriter = ioWriter;
    }

    void writeAnnualPerformanceValueMessage() {
        ioWriter.write("Please inform the employee's annual performance value:");
    }

    void writeJobTitleMessage() {
        ioWriter.write("Please inform the job title:");
    }
}
