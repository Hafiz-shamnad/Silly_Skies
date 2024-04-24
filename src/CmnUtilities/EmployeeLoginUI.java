package CmnUtilities;

import Admin.AdminEntryUI;
import Employee.EmployeeEntryUI;
import Jdbc_Connection.*;
import User.UserEntryUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EmployeeLoginUI extends JFrame {

  static boolean admin = false;
  static boolean user = false;

  public EmployeeLoginUI() {
    setTitle("Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1440, 1024); // Set frame size to match requirements
    setLocationRelativeTo(null); // Center the window

// Components
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBackground(new Color(238, 238, 238)); // Background color

// Left side panel
    JPanel leftPanel = new JPanel();
    leftPanel.setPreferredSize(new Dimension(620, 1024));
    leftPanel.setBackground(new Color(34, 40, 49)); // Rectangle color
    mainPanel.add(leftPanel, BorderLayout.WEST);

    // Load image
    try {
      // Change "imagePath" to the path where your image is located
      File imageFile = new File("/home/anonym8y/Clg_project/Project1-dms/Airline Management/src/Resources/aviation_logo-22 [Converted]-white-01.png");
      if (imageFile.exists()) {
        Image originalImage = ImageIO.read(imageFile);
        // Scale the image to desired dimensions
        int scaledWidth = 800; // Adjust width as needed
        int scaledHeight = 600; // Adjust height as needed
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(icon);

        // Add image label to the left panel with GridBagConstraints to center it
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(imageLabel, gbc);
      } else {
        System.err.println("Image file not found: " + imageFile.getAbsolutePath());
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }

// Right side panel
    JPanel rightPanel = new JPanel(new GridBagLayout());
    rightPanel.setPreferredSize(new Dimension(820, 1024));
    rightPanel.setBackground(new Color(238, 238, 238)); // Background color

// Components from the new code snippet
    JLabel employeeLabel = new JLabel("Employee ID");
    employeeLabel.setForeground(new Color(75, 118, 120)); // Label color 4B7678
    employeeLabel.setFont(new Font("Poppins", Font.BOLD, 18));
    JTextField employeeField = new JTextField(40);
    employeeField.setBackground(new Color(217, 217, 217)); // Field background color D9D9D9

    JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setForeground(new Color(75, 118, 120)); // Label color 4B7678
    passwordLabel.setFont(new Font("Poppins", Font.BOLD, 18));
    JPasswordField passwordField = new JPasswordField(40);
    passwordField.setBackground(new Color(217, 217, 217)); // Field background color D9D9D9

    JButton loginButton = new JButton("Login");
    loginButton.setBackground(new Color(34, 40, 49)); // Button background color 222831
    loginButton.setForeground(new Color(238, 238, 238)); // Button text color eeeeee
    loginButton.setFont(new Font("Poppins", Font.BOLD, 15)); // Font Poppins, bold, size 24

// Layout
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 50, 5, 5); // Adjusted insets for better alignment

    rightPanel.add(employeeLabel, gbc);
    gbc.gridy++;
    rightPanel.add(employeeField, gbc);
    gbc.gridy++;
    rightPanel.add(passwordLabel, gbc);
    gbc.gridy++;
    rightPanel.add(passwordField, gbc);
    gbc.gridy++;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.EAST;
    rightPanel.add(loginButton, gbc);

    mainPanel.add(rightPanel, BorderLayout.CENTER);

    add(mainPanel);



    // Action Listener
    loginButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String emId = employeeField.getText();
          String password = new String(passwordField.getPassword());

          // Perform login authentication logic here
          // For demonstration, check if username is "admin" and password is "admin"

          if (validateUser(emId, password)) {
            // Open Admin Entry UI
            dispose(); // Close the login window
            new EmployeeEntryUI().setVisible(true);
          } else {
            dispose();
          }
        }
      }
    );
  }

  public static boolean validateUser(String username, String password) {
    // SQL query to retrieve user information based on the provided username
    String sql = "SELECT * FROM employees WHERE employee_id = ?";
    // Create a PreparedStatement with the SQL query
    try (
      PreparedStatement preparedStatement = DatabaseConnection
        .getConnection()
        .prepareStatement(sql)
    ) {
      preparedStatement.setString(1, username);

      // Execute the query and retrieve the result set
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          // Retrieve the password stored in the database
          String storedPassword = resultSet.getString("password");
          if (password.equals(storedPassword)) {
            return true;
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      // Handle any SQL exceptions
    }
    // If the username doesn't exist or passwords don't match, user is not validated
    return false;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          new EmployeeLoginUI().setVisible(true);
        }
      }
    );
  }
}
