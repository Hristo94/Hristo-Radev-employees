package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Class describing a pair of Employees, listing all the common projects they have worked on
 * and total days worked together on all the projects
 **/
public class EmployeePair {
    private final String employeeId1;
    private final String employeeId2;
    private List<CommonProject> commonProjects;
    private long totalDaysWorked;

    public EmployeePair(String employeeId1, String employeeId2) {
        this.employeeId1 = employeeId1;
        this.employeeId2 = employeeId2;
        commonProjects = new ArrayList<>();
    }

    public String getEmployeeId1() {
        return employeeId1;
    }

    public String getEmployeeId2() {
        return employeeId2;
    }

    public List<CommonProject> getCommonProjects() {
        return commonProjects;
    }

    public long getTotalDaysWorked() {
        return totalDaysWorked;
    }

    public void addCommonProject(CommonProject commonProject) {
        commonProjects.add(commonProject);
        totalDaysWorked += commonProject.getDaysWorked();
    }
}
