package Admin;


import Jdbc_Connection.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteEmployeeAccountUI extends JFrame {

    public DeleteEmployeeAccountUI() {
        setTitle("Delete Employee Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window

        // Components
        JLabel empIdLabel = new JLabel("Employee ID:");
        JTextField empIdField = new JTextField(20);
        JButton deleteButton = new JButton("Delete");
        JButton gobackButton = new JButton("Go Back");

        // Apply UI colors and styles
        Font labelFont = new Font("Poppins", Font.BOLD, 15);
        Color labelColor = new Color(75, 118, 120);
        Color textFieldColor = new Color(217, 217, 217);
        Color buttonBgColor = new Color(34, 40, 49);
        Color buttonTextColor = new Color(238, 238, 238);

        empIdLabel.setFont(labelFont);
        empIdLabel.setForeground(labelColor);

        empIdField.setBackground(textFieldColor);

        deleteButton.setBackground(buttonBgColor);
        deleteButton.setForeground(buttonTextColor);
        gobackButton.setBackground(buttonBgColor);
        gobackButton.setForeground(buttonTextColor);

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(empIdLabel, gbc);
        gbc.gridx++;
        panel.add(empIdField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(deleteButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(gobackButton, gbc);


        add(panel);

        // Action Listener for delete button
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String empId = empIdField.getText();
                // Perform database deletion logic
                try (Connection connection = DatabaseConnection.getConnection()) {
                    // Create a PreparedStatement
                    String sql = "DELETE FROM employees WHERE employee_id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, empId);

                    // Execute the update
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Employee account deleted successfully!\nEmployee ID: " + empId);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete employee account.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        gobackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminEntryUI().setVisible(true);
            }
        });

        // For demonstration, just displaying a message
        add(panel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DeleteEmployeeAccountUI().setVisible(true);
            }
        });
    }
}

