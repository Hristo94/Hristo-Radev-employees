package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * domain.Project is uniquely identified by an Id and contains a list of employees that have worked on that project
 */
public class Project {
    private final String id;

    // all employees that have worked on that project keyed by employee id
    private List<Employee> employees;

    public Project(String id) {
        this.id = id;
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public String getId() {
        return id;
    }
}
