package domain;

import java.util.Date;

/**
 * Class describing the relationship between an employee and a project
 * it contains the start and end date of the employee on that project
 **/
public class EmployeeProject {
    private final String employeeId;
    private final String projectId;
    private final Date fromDate;
    private final Date toDate;

    public EmployeeProject(String employeeId, String projectId, Date fromDate, Date toDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }
}