package service;

import domain.*;
import utils.CSVUtils;
import utils.DateUtils;

import java.io.File;
import java.util.*;

public class EmployeeCalculatorService {
    private Map<String, EmployeePair> employeePairs = new HashMap<>();
    private Map<String, Employee> employees = new HashMap<>();
    private Map<String, Project> projects = new HashMap<>();

    public EmployeeCalculatorService(File file, String dateFormat) throws Exception {
        List<EmployeeProject> employeeProjects = CSVUtils.parseCSV(file, dateFormat);

        // populate the lists of all employees and all projects
        for(EmployeeProject employeeProject: employeeProjects) {
            String employeeId = employeeProject.getEmployeeId();
            String projectId = employeeProject.getProjectId();

            Employee employee = employees.computeIfAbsent(employeeId, key -> new Employee(employeeId));
            employee.addEmployeeProject(employeeProject);

            Project project = projects.computeIfAbsent(projectId, key -> new Project(projectId));
            project.addEmployee(employee);
        }
    }

    public EmployeePair calculateMaxPair() {
        // for each project find all employees that worked together on it
        for(Project project: projects.values()) {
            String projectId = project.getId();
            List<Employee> projectEmployees = project.getEmployees();

            // iterate over all pairs of employees to identify whether they have worked together on that project
            for(int i = 0; i < projectEmployees.size() - 1; i++) {
                for(int j = i + 1; j < projectEmployees.size(); j++) {
                    Employee employee1 = projectEmployees.get(i);
                    Employee employee2 = projectEmployees.get(j);

                    EmployeeProject employeeProject1 = employee1.getEmployeeProject(projectId);
                    EmployeeProject employeeProject2 = employee2.getEmployeeProject(projectId);

                    // both employees have worked on the same project
                    if(employeeProject1 != null && employeeProject2 != null) {
                        // calculate overlap if any
                        long daysOverlap = DateUtils.getDaysOverlap(
                                employeeProject1.getFromDate(),
                                employeeProject1.getToDate(),
                                employeeProject2.getFromDate(),
                                employeeProject2.getToDate());

                        // if the employees have worked on the project at the same time, add to employee pair
                        if(daysOverlap > 0) {
                            CommonProject commonProject = new CommonProject(employee1.getId(), employee2.getId(), projectId, daysOverlap);
                            String[] employeePairIds = {employee1.getId(), employee2.getId()};
                            Arrays.sort(employeePairIds);
                            String employeePairId = String.format("%s_%s", employeePairIds[0], employeePairIds[1]);

                            EmployeePair employeePair = employeePairs.computeIfAbsent(employeePairId, key -> new EmployeePair(employeePairIds[0], employeePairIds[1]));
                            employeePair.addCommonProject(commonProject);
                        }
                    }
                }
            }
        }

        // find the employee pair that has worked together the longest
        return employeePairs.values().stream()
                .max(Comparator.comparing(EmployeePair::getTotalDaysWorked))
                .orElse(null);
    }
}
