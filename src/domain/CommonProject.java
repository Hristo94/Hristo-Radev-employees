package domain;

/**
 * Class describing the relationship between two employees working on the same project
 * and how long they have worked together on it
 **/
public class CommonProject {
    private final String employeeId1;
    private final String employeeId2;
    private final String projectId;
    private final long daysWorked;

    public CommonProject(String employeeId1, String employeeId2, String projectId, long daysWorked) {
        // for display purposes sort the employees by Id
        this.employeeId1 = employeeId1;
        this.employeeId2 = employeeId2;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public String getEmployeeId1() {
        return employeeId1;
    }

    public String getEmployeeId2() {
        return employeeId2;
    }

    public String getProjectId() {
        return projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }
}
