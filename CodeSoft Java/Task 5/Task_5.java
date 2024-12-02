import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

class Student {
    String name;
    int rollNumber;
    String grade;

    Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "<html>Name: " + name + "<br>Roll Number: " + rollNumber + "<br>Grade: " + grade + "</html>";
    }
}

public class Task_5 {
    private JFrame frame;
    private Map<Integer, Student> studentDatabase = new HashMap<>();

    public Task_5() {
        frame = new JFrame("Student Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");
        JButton exitButton = new JButton("Exit");

        addButton.addActionListener(e -> addStudent());
        removeButton.addActionListener(e -> removeStudent());
        searchButton.addActionListener(e -> searchStudent());
        displayButton.addActionListener(e -> displayAllStudents());
        exitButton.addActionListener(e -> System.exit(0));

        frame.add(addButton);
        frame.add(removeButton);
        frame.add(searchButton);
        frame.add(displayButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

    private void addStudent() {
        JTextField nameField = new JTextField();
        JTextField rollNumberField = new JTextField();
        JTextField gradeField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "Roll Number:", rollNumberField,
                "Grade:", gradeField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int rollNumber;
            try {
                rollNumber = Integer.parseInt(rollNumberField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Roll Number must be a valid number.");
                return;
            }
            String grade = gradeField.getText();
            studentDatabase.put(rollNumber, new Student(name, rollNumber, grade));
            JOptionPane.showMessageDialog(frame, "Student added successfully.");
        }
    }

    private void removeStudent() {
        String input = JOptionPane.showInputDialog(frame, "Enter Roll Number to Remove:");
        if (input != null) {
            try {
                int rollNumber = Integer.parseInt(input);
                if (studentDatabase.remove(rollNumber) != null) {
                    JOptionPane.showMessageDialog(frame, "Student removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid roll number.");
            }
        }
    }

    private void searchStudent() {
        String input = JOptionPane.showInputDialog(frame, "Enter Roll Number to Search:");
        if (input != null) {
            try {
                int rollNumber = Integer.parseInt(input);
                Student student = studentDatabase.get(rollNumber);
                if (student != null) {
                    JOptionPane.showMessageDialog(frame, student.toString(), "Student Found", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid roll number.");
            }
        }
    }

    private void displayAllStudents() {
        if (studentDatabase.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No students currently in the system.");
        } else {
            StringBuilder allStudents = new StringBuilder("<html>");
            for (Student student : studentDatabase.values()) {
                allStudents.append(student.toString()).append("<br><br>");
            }
            allStudents.append("</html>");
            JOptionPane.showMessageDialog(frame, allStudents.toString(), "All Students", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task_5::new);
    }
}
