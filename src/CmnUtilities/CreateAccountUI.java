package CmnUtilities;

import static Jdbc_Connection.DatabaseConnection.getConnection;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class CreateAccountUI extends JFrame {

  public CreateAccountUI() {
    setTitle("Create Account");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200, 720);
    setLocationRelativeTo(null); // Center the window

    JLabel headingLabel = new JLabel("Create Your Profile");
    headingLabel.setForeground(new Color(34, 40, 49)); // Heading color 222831
    headingLabel.setFont(new Font("Poppins", Font.BOLD, 24)); // Heading font
    headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the heading

    // Components
    JLabel nameLabel = new JLabel("Name");
    JTextField nameField = new JTextField(40);
    JLabel emailLabel = new JLabel("Email");
    JTextField emailField = new JTextField(40);
    JLabel passportNumberLabel = new JLabel("Passport Number");
    JTextField passportNumberField = new JTextField(40);
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField(40);
    JLabel addressLabel = new JLabel("Address");
    JTextField addressField = new JTextField(40);
    addressField.setPreferredSize(new Dimension(200, 50));
    JButton createButton = new JButton("Create");
    JButton goBackButton = new JButton("Exit");

    // Set font colors and styles
    Font labelFont = new Font("Poppins", Font.BOLD, 14);
    Font textFieldFont = new Font("Poppins", Font.PLAIN, 14);
    Color labelColor = new Color(75, 118, 120);
    Color textFieldColor = new Color(217, 217, 217);
    Color buttonBgColor = new Color(34, 40, 49);
    Color buttonTextColor = new Color(238, 238, 238);

    nameLabel.setFont(labelFont);
    nameLabel.setForeground(labelColor);

    nameField.setFont(textFieldFont);
    nameField.setBackground(textFieldColor);

    emailLabel.setFont(labelFont);
    emailLabel.setForeground(labelColor);

    emailField.setFont(textFieldFont);
    emailField.setBackground(textFieldColor);

    passportNumberLabel.setFont(labelFont);
    passportNumberLabel.setForeground(labelColor);

    passportNumberField.setFont(textFieldFont);
    passportNumberField.setBackground(textFieldColor);

    passwordLabel.setFont(labelFont);
    passwordLabel.setForeground(labelColor);

    passwordField.setFont(textFieldFont);
    passwordField.setBackground(textFieldColor);

    addressLabel.setFont(labelFont);
    addressLabel.setForeground(labelColor);

    addressField.setFont(textFieldFont);
    addressField.setBackground(textFieldColor);

    createButton.setFont(labelFont);
    createButton.setBackground(buttonBgColor);
    createButton.setForeground(buttonTextColor);

    goBackButton.setFont(labelFont);
    goBackButton.setBackground(buttonBgColor);
    goBackButton.setForeground(buttonTextColor);

    // Layout
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    panel.add(headingLabel, gbc);

    gbc.gridy++;
    panel.add(nameLabel, gbc);
    gbc.gridx++;
    panel.add(nameField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(emailLabel, gbc);
    gbc.gridx++;
    panel.add(emailField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(passportNumberLabel, gbc);
    gbc.gridx++;
    panel.add(passportNumberField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(passwordLabel, gbc);
    gbc.gridx++;
    panel.add(passwordField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(addressLabel, gbc);
    gbc.gridx++;
    panel.add(addressField, gbc);

    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    panel.add(createButton, gbc);

    gbc.gridy++;
    panel.add(goBackButton, gbc);

    add(panel);
    // Action Listeners
    createButton.addActionListener(
        _ -> {
          // Retrieve entered account details
          String name = nameField.getText();
          String email = emailField.getText();
          String password = new String(passwordField.getPassword());
          String address = addressField.getText();
          String passportNumber = passportNumberField.getText();

          String sql = "INSERT INTO users (username, password, user_type, email, house_address , passport_number)\n" +
              "VALUES (" +
              '"' +
              name +
              '"' +
              ", " +
              '"' +
              password +
              '"' +
              ", 'user', " +
              '"' +
              email +
              '"' +
              ", " +
              '"' +
              address +
              '"' +
              ", " +
              '"' +
              passportNumber +
              '"' +
              ");\n";
          System.out.println(sql);
          // Try-with-resources to automatically close resources
          try (Statement statement = getConnection().createStatement()) {
            // Execute the INSERT query
            int rowsAffected = statement.executeUpdate(sql);

            // Check if any rows were affected
            if (rowsAffected > 0) {
              String message = "Account created successfully!\n" +
                  "Name: " +
                  name +
                  "\n" +
                  "Email: " +
                  email;
              JOptionPane.showMessageDialog(null, message);
              dispose();
              new UserLoginUI().setVisible(true);
            } else {
              System.out.println("Failed to insert data.");
            }
          } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQL exceptions
          }
        });

    goBackButton.addActionListener(
        _ -> {
          dispose();
          // Close the window
        });

    add(panel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> new CreateAccountUI().setVisible(true));
  }
}
