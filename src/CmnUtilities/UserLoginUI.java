package CmnUtilities;

import Admin.AdminEntryUI;
import Jdbc_Connection.*;
import User.UserEntryUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UserLoginUI extends JFrame {

  static boolean admin = false;

  public UserLoginUI() {
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
      File imageFile = new File("/home/hafiz/Clg_project/Project1-dms/Student-Management/src/Resources/aviation_logo-22 [Converted]-white-01.png");
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

    JLabel usernameLabel = new JLabel("Username");
    usernameLabel.setForeground(new Color(75, 118, 120)); // Label color 4B7678
    usernameLabel.setFont(new Font("Poppins", Font.BOLD, 18)); // Font Poppins, bold, size 24
    JTextField usernameField = new JTextField(40);
    usernameField.setBackground(new Color(217, 217, 217));

    JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setForeground(new Color(75, 118, 120)); // Label color 4B7678
    passwordLabel.setFont(new Font("Poppins", Font.BOLD, 18)); // Font Poppins, bold, size 24
    JPasswordField passwordField = new JPasswordField(40);
    passwordField.setBackground(new Color(217, 217, 217));

    JButton loginButton = new JButton("LOGIN");
    loginButton.setBackground(new Color(34, 40, 49)); // Button background color 222831
    loginButton.setForeground(new Color(238, 238, 238)); // Button text color eeeeee
    loginButton.setFont(new Font("Poppins", Font.BOLD, 15)); // Font Poppins, bold, size 24

    // Layout
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 50, 5, 5);

    rightPanel.add(usernameLabel, gbc);
    gbc.gridy++;
    rightPanel.add(usernameField, gbc);
    gbc.gridy++;
    rightPanel.add(passwordLabel, gbc);
    gbc.gridy++;
    rightPanel.add(passwordField, gbc);
    gbc.gridx = 0;
    gbc.gridy++;
    rightPanel.add(loginButton, gbc);

    // Adjust insets to move components to the left
    gbc.insets = new Insets(5, 50, 6, 5);
    rightPanel.add(usernameLabel, gbc);
    gbc.gridy++;
    rightPanel.add(usernameField, gbc);
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
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Perform login authentication logic here
                // For demonstration, check if username is "admin" and password is "admin"

                if (validateUser(username, password)) {
                  // Open Admin Entry UI
                  dispose(); // Close the login window
                  if (admin) {
                    new AdminEntryUI().setVisible(true);
                  } else {
                    new UserEntryUI().setVisible(true);
                  }
                } else {
                  // Open Employee Entry UI
                  dispose(); // Close the login window
                }
              }
            }
    );

    mainPanel.add(rightPanel, BorderLayout.CENTER);

    add(mainPanel);
  }

  public static boolean validateUser(String username, String password) {
    // SQL query to retrieve user information based on the provided username
    String sql = "SELECT * FROM users WHERE username = ?";

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
          String usertype = resultSet.getString("user_type");
          // Compare the stored password with the provided password
          if (password.equals(storedPassword)) {
            if (usertype.equals(("admin"))) {
              admin = true;
            }
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
                new UserLoginUI().setVisible(true);
              }
            }
    );
  }
}
