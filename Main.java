package projectPOO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
public class Main {
private static Project myProject;
private static JTextField titleField, taskTitleField, startDateField, endDateField, assignedToField;
private static JTextArea descField, taskDescField;
private static JComboBox statusBox;
private static JFrame frame;

public static void main(String[] args) {
    SwingUtilities.invokeLater(Main::showLoginDialog);
}

// نافذة تسجيل الدخول
private static void showLoginDialog() {
    JDialog loginDialog = new JDialog();
    loginDialog.setTitle("Login");
    loginDialog.setSize(350, 250);
    loginDialog.setLayout(new BorderLayout());
    loginDialog.setModal(true);

   
    JPanel topPanel = new JPanel(new GridLayout(2, 1));
    JLabel appName = new JLabel(" Inelyn", JLabel.CENTER);
    JLabel appDesc = new JLabel("Manage your projects and tasks efficiently.", JLabel.CENTER);
    
    // **Set font sizes**
    appName.setFont(appName.getFont().deriveFont(22f)); // Bigger font for app name
    appDesc.setFont(appDesc.getFont().deriveFont(14f)); // Smaller font for description

    topPanel.add(appName);
    topPanel.add(appDesc);

    JPanel centerPanel = new JPanel(new GridLayout(3, 2, 5, 5));
    JLabel userLabel = new JLabel("Username:");
    JTextField userField = new JTextField();
    JLabel passLabel = new JLabel("Password:");
    JPasswordField passField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    
    loginButton.setPreferredSize(new java.awt.Dimension(80, 25)); // Smaller button size

    loginButton.addActionListener(e -> {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (username.equals("admin") && password.equals("1234")) {
            loginDialog.dispose(); // Close login window
            createAndShowGUI(); // Open main interface
        } else {
            JOptionPane.showMessageDialog(loginDialog, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);
    centerPanel.add(new JLabel()); 
    centerPanel.add(loginButton);

    
    loginDialog.add(topPanel, BorderLayout.NORTH);
    loginDialog.add(centerPanel, BorderLayout.CENTER);

    loginDialog.setLocationRelativeTo(null);
    loginDialog.setVisible(true);
}
private static JTable taskTable;
private static DefaultTableModel tableModel;

private static void createAndShowGUI() {
    frame = new JFrame("Inelyn");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLayout(new BorderLayout());

    JPanel panel = new JPanel(new GridLayout(10, 2, 5, 5));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.setBackground(new Color(211, 211, 211));

    titleField = new JTextField(20);
    descField = new JTextArea(3, 20);
    taskTitleField = new JTextField(20);
    taskDescField = new JTextArea(3, 20);
    startDateField = new JTextField(10);
    endDateField = new JTextField(10);
    assignedToField = new JTextField(20);
    String[] statusOptions = {"Not Started", "In Progress", "Done"};
    statusBox = new JComboBox<>(statusOptions);

    Color softPeach = new Color(255, 218, 185);
    titleField.setBackground(softPeach);
    descField.setBackground(softPeach);
    taskTitleField.setBackground(softPeach);
    taskDescField.setBackground(softPeach);
    startDateField.setBackground(softPeach);
    endDateField.setBackground(softPeach);
    assignedToField.setBackground(softPeach);
    statusBox.setBackground(softPeach);

    JButton addTaskButton = new JButton("Add Task");
    JButton displayButton = new JButton("Display Project");
    JButton saveButton = new JButton("Save Project");
    JButton loadButton = new JButton("Load Project");

    
    String[] columnNames = {"Title", "Description", "Start Date", "End Date", "Assigned To", "Status"};
    tableModel = new DefaultTableModel(columnNames, 0);
    taskTable = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(taskTable);

   
    myProject = Project.loadFromFile("projectData.txt");
    if (myProject == null) {
        myProject = new Project("New Project", "Description here..."); 
    }
    titleField.setText(myProject.getTitle());
    descField.setText(myProject.getDescription());
    updateTaskTable(); 

    
    addTaskButton.addActionListener(e -> {
        try {
            String taskTitle = taskTitleField.getText().trim();
            String taskDesc = taskDescField.getText().trim();
            String assignedTo = assignedToField.getText().trim();
            LocalDate startDate = LocalDate.parse(startDateField.getText().trim());
            LocalDate endDate = LocalDate.parse(endDateField.getText().trim());
            String status = (String) statusBox.getSelectedItem();

            if (taskTitle.isEmpty() || taskDesc.isEmpty() || assignedTo.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled!");
            }

           
            Task1 task = new Task1(taskTitle, taskDesc, startDate, endDate, assignedTo);
            task.setStatus(status);

            
            if (myProject == null) {
                myProject = new Project(titleField.getText(), descField.getText());
            }

            myProject.addTask(task);
            
            
            updateTaskTable();
            System.out.println("Task added: " + task.getTitle());

            JOptionPane.showMessageDialog(frame, "Task added successfully!");

            // Clear input fields after adding task
            taskTitleField.setText("");
            taskDescField.setText("");
            startDateField.setText("");
            endDateField.setText("");
            assignedToField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    
    displayButton.addActionListener(e -> {
        if (myProject != null) {
            myProject.displayProject(); // Prints tasks in console
            updateTaskTable(); // Refreshes table
            JOptionPane.showMessageDialog(frame, "Project displayed!");
        } else {
            JOptionPane.showMessageDialog(frame, "No project found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    
    saveButton.addActionListener(e -> {
        if (myProject != null) {
            myProject.saveToFile("projectData.txt");
            JOptionPane.showMessageDialog(frame, "Project saved successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "No project to save!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    
    loadButton.addActionListener(e -> {
        myProject = Project.loadFromFile("projectData.txt");
        if (myProject != null) {
            titleField.setText(myProject.getTitle());
            descField.setText(myProject.getDescription());
            updateTaskTable();
            JOptionPane.showMessageDialog(frame, "Project loaded successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "Failed to load project!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    addTaskButton.setBackground(softPeach);
    displayButton.setBackground(softPeach);
    saveButton.setBackground(softPeach);
    loadButton.setBackground(softPeach);

    panel.add(new JLabel("Project Title:"));
    panel.add(titleField);
    panel.add(new JLabel("Project Description:"));
    panel.add(new JScrollPane(descField));
    panel.add(new JLabel("Task Title:"));
    panel.add(taskTitleField);
    panel.add(new JLabel("Task Description:"));
    panel.add(new JScrollPane(taskDescField));
    panel.add(new JLabel("Start Date (YYYY-MM-DD):"));
    panel.add(startDateField);
    panel.add(new JLabel("End Date (YYYY-MM-DD):"));
    panel.add(endDateField);
    panel.add(new JLabel("Assigned To:"));
    panel.add(assignedToField);
    panel.add(new JLabel("Task Status:"));
    panel.add(statusBox);
    panel.add(addTaskButton);
    panel.add(displayButton);
    panel.add(saveButton);
    panel.add(loadButton);

    frame.add(panel, BorderLayout.NORTH);
    frame.add(tableScrollPane, BorderLayout.CENTER); // ✅ Add JTable to UI
    frame.setVisible(true);
}




//

private static void updateTaskTable() {
    tableModel.setRowCount(0); 

    if (myProject != null) {
        for (Task1 task : myProject.getTasks()) {
            Object[] rowData = {
                task.getTitle(),
                task.getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getAssignedTo(),
                task.getStatus()
            };
            tableModel.addRow(rowData);
        }
    }
}


////
public static Project getMyProject() {
	return myProject;
}

public static void setMyProject(Project myProject) {
	Main.myProject = myProject;
}

private static void loadProject() {
}
}
