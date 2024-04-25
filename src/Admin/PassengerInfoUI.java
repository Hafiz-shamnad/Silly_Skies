package Admin;

import Jdbc_Connection.DatabaseConnection;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class PassengerInfoUI extends JFrame {
  private Connection connection;

  public PassengerInfoUI() {
    setTitle("Passenger Information");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1350, 720);
    setLocationRelativeTo(null); // Center the window

    JLabel headingLabel = new JLabel("Passenger Info");
    headingLabel.setForeground(new Color(34, 40, 49)); // Heading color 222831
    headingLabel.setFont(new Font("Poppins", Font.BOLD, 24)); // Heading font
    headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the heading
    // Components
    JLabel nameLabel = new JLabel("Name");
    JTextField nameField = new JTextField(40);
    JLabel passportLabel = new JLabel("Passport Number");
    JTextField passportField = new JTextField(40);
    JButton searchButton = new JButton("Search");
    JTextArea passengerInfoArea = new JTextArea(10, 55);
    JScrollPane scrollPane = new JScrollPane(passengerInfoArea);
    JButton closeButton = new JButton("Close");
    JButton editPassengerDetailsButton = new JButton("Edit Passenger Details");

    // Set font colors and styles
    Font labelFont = new Font("Poppins", Font.BOLD, 14);
    Font textFieldFont = new Font("Poppins", Font.PLAIN, 14);
    Color labelColor = new Color(75, 118, 120);
    Color textFieldColor = new Color(217, 217, 217);
    Color buttonBgColor = new Color(34, 40, 49);
    Color buttonTextColor = new Color(238, 238, 238);

    nameLabel.setFont(labelFont);
    nameLabel.setForeground(labelColor);
    passportLabel.setFont(labelFont);
    passportLabel.setForeground(labelColor);

    nameField.setFont(textFieldFont);
    nameField.setBackground(textFieldColor);
    passportField.setFont(textFieldFont);
    passportField.setBackground(textFieldColor);

    searchButton.setFont(labelFont);
    searchButton.setBackground(buttonBgColor);
    searchButton.setForeground(buttonTextColor);

    passengerInfoArea.setFont(textFieldFont);
    passengerInfoArea.setBackground(textFieldColor);
    passengerInfoArea.setEditable(false); // Make it read-only

    closeButton.setFont(labelFont);
    closeButton.setBackground(buttonBgColor);
    closeButton.setForeground(buttonTextColor);

    editPassengerDetailsButton.setFont(labelFont);
    editPassengerDetailsButton.setBackground(buttonBgColor);
    editPassengerDetailsButton.setForeground(buttonTextColor);

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
    panel.add(passportLabel, gbc);
    gbc.gridx++;
    panel.add(passportField, gbc);

    gbc.gridy++;
    gbc.anchor = GridBagConstraints.EAST;
    panel.add(searchButton, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 3;
    panel.add(scrollPane, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 1;
    panel.add(editPassengerDetailsButton, gbc);

    gbc.gridx++;
    panel.add(closeButton, gbc);

    add(panel);

    // Action Listeners
    searchButton.addActionListener(
        _ -> {
          // Perform database search logic here
          String passportNumber = passportField.getText().trim();
          if (!passportNumber.isEmpty()) {
            String passengerInfo = null;
            try {
              passengerInfo = retrievePassengerInfo(passportNumber);
            } catch (SQLException ex) {
              throw new RuntimeException(ex);
            }
            passengerInfoArea.setText(passengerInfo);
          } else {
            JOptionPane.showMessageDialog(
                null,
                "Please enter a passportNumber to search.");
          }
        });

    editPassengerDetailsButton.addActionListener(
        _ -> {
          // Open edit passenger details window
          new EditPassengerInfoUI().setVisible(true);
        });

    closeButton.addActionListener(
        _ -> {
          dispose(); // Close the window
        });

    add(panel);
  }

  // Method to retrieve passenger information from the database
  private String retrievePassengerInfo(String passportNumber) throws SQLException {
    StringBuilder passengerInfo = new StringBuilder();
    if (connection == null || connection.isClosed()) {
      connection = DatabaseConnection.getConnection();
      System.out.println("Connection established."); // Log connection status
    }
    if (connection != null) {
      // No need to declare a local variable named 'connection'
      String query = "SELECT * FROM ticket_details WHERE passport_number = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, passportNumber);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            passengerInfo
                .append("Passenger Information:\n")
                .append("Name: ")
                .append(resultSet.getString("passenger_name"))
                .append("\n")
                .append("Passport Number: ")
                .append(resultSet.getString("passport_number"))
                .append("\n")
                .append("Ticket Number: ")
                .append(resultSet.getString("ticket_number"))
                .append("\n")
                .append("Seat Number: ")
                .append(resultSet.getString("seat_number"))
                .append("\n");
          } else {
            passengerInfo
                .append("No passenger found with the Passport Number: ")
                .append(passportNumber);
          }
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(
            null,
            "Error retrieving passenger information: " + ex.getMessage());
      }
    }
    return passengerInfo.toString();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> new PassengerInfoUI().setVisible(true));
  }
}
