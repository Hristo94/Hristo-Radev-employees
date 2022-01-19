package ui;

import domain.CommonProject;
import domain.EmployeePair;
import service.EmployeeCalculatorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {
    private EmployeeCalculatorService employeeCalculatorService;

    private JTable jTable;
    private DefaultTableModel tableModel;

    private JButton openCSVButton;
    private JFileChooser fileChooser;
    private JToolBar toolBar;
    private JComboBox<String> jComboBox;

    private String[] columnNames = new String[]{
            "Employee ID #1",
            "Employee ID #2",
            "Project ID",
            "Days worked"
    };

    private String[] supportedDateFormats = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd",
            "yyyy-dd-MM", "yyyy/dd/MM", "yyyy.dd.MM",
            "MM-dd-yyyy", "MM/dd/yyyy", "MM.dd.yyyy",
            "dd-MM-yyyy", "dd/MM/yyyy", "dd.MM.yyyy",
    };

    public MainFrame() {
        this.setVisible(true);
        this.setBounds(100,100,900,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        init();
    }

    private void init(){
        // create the table
        jTable = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        jTable.setModel(tableModel);
        this.add(new JScrollPane(jTable), BorderLayout.CENTER);

        toolBar = new JToolBar();
        fileChooser = new JFileChooser();

        jComboBox = new JComboBox<>(supportedDateFormats);
        toolBar.add(jComboBox);
        jComboBox.setMaximumSize(new Dimension(200,100));

        openCSVButton = new JButton("Select File");
        toolBar.add(openCSVButton);

        this.add(toolBar, BorderLayout.NORTH);
        this.setVisible(true);

        initListeners();
    }

    private void initListeners() {
        openCSVButton.addActionListener(event -> {
            if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String dateFormat = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                try {
                    employeeCalculatorService = new EmployeeCalculatorService(file, dateFormat);
                } catch (Exception e) {
                    System.out.println(e);
                    System.exit(1);
                }
            } else {
                return;
            }

            EmployeePair employeePair = employeeCalculatorService.calculateMaxPair();

            if (employeePair != null) {
                for (CommonProject commonProject : employeePair.getCommonProjects()) {
                    tableModel.addRow(new Object[]{
                            employeePair.getEmployeeId1(),
                            employeePair.getEmployeeId2(),
                            commonProject.getProjectId(),
                            commonProject.getDaysWorked()});
                }
            }
        });
    }

}
