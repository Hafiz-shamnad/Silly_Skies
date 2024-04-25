package Admin;

import static Jdbc_Connection.DatabaseConnection.getConnection;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class CreateEmployeeAccountUI extends JFrame {

  public CreateEmployeeAccountUI() {
    setTitle("Create Account");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);
    setLocationRelativeTo(null); // Center the window

    // Components
    JLabel nameLabel = new JLabel("Name");
    JTextField nameField = new JTextField(20);
    JLabel emIdLabel = new JLabel("Employee ID");
    JTextField emIdField = new JTextField(20);
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField(20);
    JButton createButton = new JButton("Create");
    JButton gobackButton = new JButton("Go Back");

    // Apply UI colors and styles
    Font labelFont = new Font("Poppins", Font.BOLD, 15);
    Color labelColor = new Color(75, 118, 120);
    Color textFieldColor = new Color(217, 217, 217);
    Color buttonBgColor = new Color(34, 40, 49);
    Color buttonTextColor = new Color(238, 238, 238);

    nameLabel.setFont(labelFont);
    nameLabel.setForeground(labelColor);
    emIdLabel.setFont(labelFont);
    emIdLabel.setForeground(labelColor);
    passwordLabel.setFont(labelFont);
    passwordLabel.setForeground(labelColor);

    nameField.setBackground(textFieldColor);
    emIdField.setBackground(textFieldColor);
    passwordField.setBackground(textFieldColor);

    createButton.setBackground(buttonBgColor);
    createButton.setForeground(buttonTextColor);
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

    panel.add(nameLabel, gbc);
    gbc.gridx++;
    panel.add(nameField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(emIdLabel, gbc);
    gbc.gridx++;
    panel.add(emIdField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(passwordLabel, gbc);
    gbc.gridx++;
    panel.add(passwordField, gbc);

    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    panel.add(createButton, gbc);

    gbc.gridy++;
    panel.add(gobackButton, gbc);

    add(panel);

    // Action Listeners
    createButton.addActionListener(
        _ -> {
          // Retrieve entered account details
          String name = nameField.getText();
          String emId = emIdField.getText();
          String password = new String(passwordField.getPassword());
          String sql = "INSERT INTO employees (employee_id, name, password) " +
              "VALUES (\"" +
              emId +
              "\", \"" +
              name +
              "\", \"" +
              password +
              '"' +
              ");";

          System.out.println(sql);
          // Try-with-resources to automatically close resources
          try (Statement statement = getConnection().createStatement()) {
            // Execute the INSERT query
            int rowsAffected = statement.executeUpdate(sql);

            // Check if any rows were affected
            if (rowsAffected > 0) {
              String message = "Account created successfully!\n" +
                  "Employee ID : " +
                  emId +
                  "\n" +
                  "Name: " +
                  name +
                  "\n";
              JOptionPane.showMessageDialog(null, message);
            } else {
              System.out.println("Failed to insert data.");
            }
          } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQL exceptions
          }
        });

    gobackButton.addActionListener(
        _ -> {
          dispose(); // Close the window
        });

    add(panel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> new CreateEmployeeAccountUI().setVisible(true));
  }
}
