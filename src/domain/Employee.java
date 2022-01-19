package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * domain.Employee is uniquely identified by an Id and contains a list of projects they have worked on
 */
public class Employee {
    private final String id;

    // all projects the employee has worked on keyed by projectId
    private Map<String, EmployeeProject> employeeProjects;

    public Employee(String id) {
        this.id = id;
        this.employeeProjects = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public EmployeeProject getEmployeeProject(String projectId) {
        return employeeProjects.get(projectId);
    }

    public void addEmployeeProject(EmployeeProject employeeProject) {
        employeeProjects.put(employeeProject.getProjectId(), employeeProject);
    }
}
