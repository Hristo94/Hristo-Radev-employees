package utils;

import domain.EmployeeProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVUtils {

    public static List<EmployeeProject> parseCSV(File file, String dateFormat) throws Exception {
        List<EmployeeProject> employeeProjects = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String employeeId = values[0].trim();
            String projectId = values[1].trim();
            Date fromDate = DateUtils.parseDate(values[2].trim(), dateFormat);
            Date toDate = DateUtils.parseDate(values[3].trim(), dateFormat);

            EmployeeProject employeeProject = new EmployeeProject(employeeId, projectId, fromDate, toDate);
            employeeProjects.add(employeeProject);
        }

        return employeeProjects;
    }
}
