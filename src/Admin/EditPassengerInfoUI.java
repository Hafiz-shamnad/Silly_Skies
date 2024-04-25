package Admin;

import Jdbc_Connection.DatabaseConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class EditPassengerInfoUI extends JFrame {

  public EditPassengerInfoUI() {
    setTitle("Edit Passenger Information");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null); // Center the window
    getContentPane().setBackground(new Color(238, 238, 238)); // Set background color

    JLabel titleLabel = new JLabel("Enter Flight Details");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(new Color(34, 40, 49)); // Set text color

    // Components
    JLabel nameLabel = new JLabel("Name");
    JTextField nameField = new JTextField(20);
    JLabel flightNumberLabel = new JLabel("Flight Number");
    JTextField flightNumberField = new JTextField(20);
    JLabel seatNumberLabel = new JLabel("Seat Number");
    JTextField seatNumberField = new JTextField(5);
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");

    Font labelFont = new Font("Poppins", Font.BOLD, 15);
    Font textFieldFont = new Font("Poppins", Font.PLAIN, 14);
    Color labelColor = new Color(75, 118, 120);
    Color textFieldColor = new Color(217, 217, 217);
    Color buttonBgColor = new Color(34, 40, 49);
    Color buttonTextColor = new Color(238, 238, 238);

    nameLabel.setFont(labelFont);
    nameLabel.setForeground(labelColor);
    flightNumberLabel.setFont(labelFont);
    flightNumberLabel.setForeground(labelColor);
    seatNumberLabel.setFont(labelFont);
    seatNumberLabel.setForeground(labelColor);

    nameField.setBackground(textFieldColor);
    nameField.setFont(textFieldFont);
    flightNumberField.setBackground(textFieldColor);
    flightNumberField.setFont(textFieldFont);
    seatNumberField.setBackground(textFieldColor);
    seatNumberField.setFont(textFieldFont);

    saveButton.setBackground(buttonBgColor); // Set button color
    saveButton.setForeground(buttonTextColor); // Set button text color
    cancelButton.setBackground(buttonBgColor); // Set button color
    cancelButton.setForeground(buttonTextColor); // Set button text color

    // Layout
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.setBackground(new Color(240, 240, 240)); // Set panel background color

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    panel.add(titleLabel, gbc);

    gbc.gridy++;
    panel.add(nameLabel, gbc);
    gbc.gridx++;
    panel.add(nameField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(flightNumberLabel, gbc);
    gbc.gridx++;
    panel.add(flightNumberField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(seatNumberLabel, gbc);
    gbc.gridx++;
    panel.add(seatNumberField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(cancelButton, gbc);
    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridwidth = 2;
    panel.add(saveButton, gbc);

    add(panel);
    // Action Listeners
    saveButton.addActionListener(
        _ -> {
          // Retrieve entered passenger details
          String name = nameField.getText();
          String flightNumber = flightNumberField.getText();
          String seatNumber = seatNumberField.getText();

          // Perform saving logic here
          boolean saved = savePassengerInfo(name, flightNumber, seatNumber);
          if (saved) {
            JOptionPane.showMessageDialog(
                null,
                "Passenger details saved successfully.");
            dispose(); // Close the window
          } else {
            JOptionPane.showMessageDialog(
                null,
                "Failed to save passenger details.");
          }
        });

    cancelButton.addActionListener(
        _ -> {
          dispose(); // Close the window
        });

    add(panel);
  }

  // Method to save passenger information into the database
  private boolean savePassengerInfo(
      String name,
      String flightNumber,
      String seatNumber) {
    try (Connection connection = DatabaseConnection.getConnection()) {
      String query = "UPDATE ticket_details SET flight_number = ?, seat_number = ? WHERE name = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, flightNumber);
        statement.setString(2, seatNumber);
        statement.setString(3, name);
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(
          null,
          "Error saving passenger information: " + ex.getMessage());
      return false;
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> new EditPassengerInfoUI().setVisible(true));
  }
}
