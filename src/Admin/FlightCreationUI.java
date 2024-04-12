package Admin;

import static Jdbc_Connection.DatabaseConnection.getConnection;

import Jdbc_Connection.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class FlightCreationUI extends JFrame {

  int totalseats = 0;

  public FlightCreationUI() {
    setTitle("Flight Creation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1440, 1024);
    setLocationRelativeTo(null); // Center the window

    JLabel headingLabel = new JLabel("Create Flight");
    headingLabel.setForeground(new Color(34, 40, 49)); // Heading color 222831
    headingLabel.setFont(new Font("Poppins", Font.BOLD, 24)); // Heading font
    headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the heading

    // Components
    JLabel travelIdLabel = new JLabel("Travel ID:");
    JTextField travelIdField = new JTextField(40);
    JLabel flightNumberLabel = new JLabel("Flight Number:");
    JTextField flightNumberField = new JTextField(40);
    JLabel departureLabel = new JLabel("Departure Location:");
    JTextField departureField = new JTextField(40);
    JLabel arrivalLabel = new JLabel("Arrival Location:");
    JTextField arrivalField = new JTextField(40);
    JLabel departureTimeLabel = new JLabel("Departure Time:");
    JTextField departureTimeField = new JTextField(40);
    JLabel arrivalTimeLabel = new JLabel("Arrival Time:");
    JTextField arrivalTimeField = new JTextField(40);
    JButton createButton = new JButton("Create");
    JButton goBackButton = new JButton("Go Back");

    // Set font colors and styles
    Font labelFont = new Font("Poppins", Font.BOLD, 15);
    Font textFieldFont = new Font("Poppins", Font.PLAIN, 14);
    Color labelColor = new Color(75, 118, 120);
    Color textFieldColor = new Color(217, 217, 217);
    Color buttonBgColor = new Color(34, 40, 49);
    Color buttonTextColor = new Color(238, 238, 238);

    travelIdLabel.setFont(labelFont);
    travelIdLabel.setForeground(labelColor);
    flightNumberLabel.setFont(labelFont);
    flightNumberLabel.setForeground(labelColor);
    departureLabel.setFont(labelFont);
    departureLabel.setForeground(labelColor);
    arrivalLabel.setFont(labelFont);
    arrivalLabel.setForeground(labelColor);
    departureTimeLabel.setFont(labelFont);
    departureTimeLabel.setForeground(labelColor);
    arrivalTimeLabel.setFont(labelFont);
    arrivalTimeLabel.setForeground(labelColor);

    travelIdField.setFont(textFieldFont);
    travelIdField.setBackground(textFieldColor);
    flightNumberField.setFont(textFieldFont);
    flightNumberField.setBackground(textFieldColor);
    departureField.setFont(textFieldFont);
    departureField.setBackground(textFieldColor);
    arrivalField.setFont(textFieldFont);
    arrivalField.setBackground(textFieldColor);
    departureTimeField.setFont(textFieldFont);
    departureTimeField.setBackground(textFieldColor);
    arrivalTimeField.setFont(textFieldFont);
    arrivalTimeField.setBackground(textFieldColor);

    createButton.setFont(labelFont);
    createButton.setBackground(buttonBgColor);
    createButton.setForeground(buttonTextColor);
    goBackButton.setFont(labelFont);
    goBackButton.setBackground(buttonBgColor);
    goBackButton.setForeground(buttonTextColor);

    // Layout
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    panel.add(headingLabel, gbc);
    gbc.gridy++;
    panel.add(flightNumberLabel, gbc);
    gbc.gridx++;
    panel.add(flightNumberField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(travelIdLabel, gbc);
    gbc.gridx++;
    panel.add(travelIdField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(departureLabel, gbc);
    gbc.gridx++;
    panel.add(departureField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(arrivalLabel, gbc);
    gbc.gridx++;
    panel.add(arrivalField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(departureTimeLabel, gbc);
    gbc.gridx++;
    panel.add(departureTimeField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(arrivalTimeLabel, gbc);
    gbc.gridx++;
    panel.add(arrivalTimeField, gbc);

    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    panel.add(createButton, gbc);
    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    panel.add(goBackButton, gbc);

    add(panel);

    // Action Listener
    createButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int travelId = Integer.parseInt(travelIdField.getText());
          String departure = departureField.getText();
          String arrival = arrivalField.getText();
          String departureTime = departureTimeField.getText();
          String arrivalTime = arrivalTimeField.getText();
          String flightNumber = flightNumberField.getText();

          String sql =
            "INSERT INTO travel_details (travel_id,flight_num,departure_location,arrival_location," +
            "departure_time,arrival_time)\n" +
            "VALUES (" +
            '"' +
            travelId +
            '"' +
            ", " +
            '"' +
            flightNumber +
            '"' +
            ", " +
            '"' +
            departure +
            '"' +
            ", " +
            '"' +
            arrival +
            '"' +
            ", " +
            "'" +
            departureTime +
            "'" +
            ", " +
            "'" +
            arrivalTime +
            "'" +
            ");\n";
          System.out.println(sql);
          // Try-with-resources to automatically close resources
          try (Statement statement = getConnection().createStatement()) {
            // Execute the INSERT query
            int rowsAffected = statement.executeUpdate(sql);

            // Check if any rows were affected
            if (rowsAffected > 0) {
              System.out.println("Data inserted successfully.");
            } else {
              System.out.println("Failed to insert data.");
            }
          } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQL exceptions
          }
          // Perform flight creation logic here
          // For demonstration, showing a message dialog
          String message =
            "Flight created with the following details:\n" +
            "Flight Number: " +
            flightNumber +
            "\n" +
            "Departure: " +
            departure +
            "\n" +
            "Arrival: " +
            arrival +
            "\n" +
            "Departure Time: " +
            departureTime +
            "\n" +
            "Arrival Time: " +
            arrivalTime +
            "\n";
          JOptionPane.showMessageDialog(null, message);
        }
      }
    );

    goBackButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          dispose();
          new AdminEntryUI().setVisible(true);
        }
      }
    );

    add(panel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          new FlightCreationUI().setVisible(true);
        }
      }
    );
  }
}
