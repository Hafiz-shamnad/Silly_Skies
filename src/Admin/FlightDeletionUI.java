package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import Jdbc_Connection.DatabaseConnection;

public class FlightDeletionUI extends JFrame {
  private Connection connection;
  public FlightDeletionUI() {
    setTitle("Delete Flight");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null); // Center the window
    getContentPane().setBackground(new Color(238, 238, 238)); // Set background color

    // Components
    JLabel flightNumberLabel = new JLabel("Flight Number:");
    JTextField flightNumberField = new JTextField(20);
    JLabel travelLabel = new JLabel("Travel ID");
    JTextField travelField = new JTextField(20);
    JButton deleteButton = new JButton("Delete");
    JButton gobackButton = new JButton("Go Back");

    Font labelFont = new Font("Poppins", Font.BOLD, 15);
    Font textFieldFont = new Font("Poppins", Font.PLAIN, 14);
    Color labelColor = new Color(75, 118, 120);
    Color textFieldColor = new Color(217, 217, 217);
    Color buttonBgColor = new Color(34, 40, 49);
    Color buttonTextColor = new Color(238, 238, 238);

    flightNumberLabel.setFont(labelFont);
    flightNumberLabel.setForeground(labelColor);

    travelLabel.setFont(labelFont);
    travelLabel.setForeground(labelColor);

    flightNumberField.setBackground(textFieldColor);
    flightNumberField.setFont(textFieldFont);

    travelField.setBackground(textFieldColor);
    travelField.setFont(textFieldFont);

    deleteButton.setBackground(buttonBgColor); // Set button color
    deleteButton.setForeground(buttonTextColor); // Set button text color

    gobackButton.setBackground(buttonBgColor); // Set button color
    gobackButton.setForeground(buttonTextColor); // Set button text color

    // Layout
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.setBackground(new Color(240, 240, 240)); // Set panel background color

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    panel.add(flightNumberLabel, gbc);
    gbc.gridx++;
    panel.add(flightNumberField, gbc);

    gbc.gridy++;
    gbc.gridx=0;
    panel.add(travelLabel, gbc);
    gbc.gridx++;
    panel.add(travelField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    panel.add(gobackButton,gbc);
    gbc.anchor = GridBagConstraints.EAST;
    panel.add(deleteButton, gbc);


    // Action Listener
    deleteButton.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                String flightNumber = flightNumberField.getText();

                // Perform flight deletion logic here
                deleteFlight(flightNumber);
              }
            }
    );

    gobackButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
        new AdminEntryUI().setVisible(true);
      }
    });

    add(panel);
  }

  // Method to delete flight from the database
  // Method to delete flight from the database
  private void deleteFlight(String flightNumber) {
    try {
      if (connection == null || connection.isClosed()) {
        connection = DatabaseConnection.getConnection();
        System.out.println("Connection established."); // Log connection status
      }
      if (connection != null) {
        String deleteQuery = "DELETE FROM flight_details WHERE flight_num = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
          preparedStatement.setString(1, flightNumber);
          int rowsDeleted = preparedStatement.executeUpdate();

          if (rowsDeleted > 0) {
            String message = "Flight with flight number " + flightNumber + " has been deleted.";
            JOptionPane.showMessageDialog(null, message);
          } else {
            JOptionPane.showMessageDialog(null, "Flight not found with flight number " + flightNumber + ".");
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Failed to establish connection to the database.");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error deleting flight: " + ex.getMessage());
    }
  }


  public static void main(String[] args) {
    SwingUtilities.invokeLater(
            new Runnable() {
              public void run() {
                new FlightDeletionUI().setVisible(true);
              }
            }
    );
  }
}
