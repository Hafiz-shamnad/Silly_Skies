package Admin;

import static Jdbc_Connection.DatabaseConnection.getConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class FlightDetailsUI extends JFrame {

  public FlightDetailsUI() {
    setTitle("Enter Flight Details");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1440, 1024);
    setLocationRelativeTo(null); // Center the window
    getContentPane().setBackground(new Color(238, 238, 238)); // Set background color

    // Components
    JLabel titleLabel = new JLabel("Enter Flight Details");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(new Color(34, 40, 49)); // Set text color

    JLabel flightNumberLabel = new JLabel("Flight Number");
    JTextField flightNumberField = new JTextField(40);

    JLabel modelLabel = new JLabel("Flight Model");
    JTextField modelField = new JTextField(40);

    JLabel luggageLabel = new JLabel("Luggage Capacity");
    JTextField luggageField = new JTextField(40);

    JLabel passengerCapacityLabel = new JLabel("Passenger Capacity");
    JTextField passengerCapacityField = new JTextField(40);

    Font labelFont = new Font("Poppins", Font.BOLD, 15);
    Font textFieldFont = new Font("Poppins", Font.PLAIN, 14);
    Color labelColor = new Color(75, 118, 120);
    Color textFieldColor = new Color(217, 217, 217);
    Color buttonBgColor = new Color(34, 40, 49);
    Color buttonTextColor = new Color(238, 238, 238);

    JButton saveButton = new JButton("Create Now");
    saveButton.setBackground(buttonBgColor); // Set button color
    saveButton.setForeground(buttonTextColor); // Set button text color

    JButton gobackButton = new JButton("Go Back");
    gobackButton.setBackground(buttonBgColor); // Set button color
    gobackButton.setForeground(buttonTextColor); // Set button text color

    flightNumberLabel.setFont(labelFont);
    flightNumberLabel.setForeground(labelColor);
    modelLabel.setFont(labelFont);
    modelLabel.setForeground(labelColor);
    luggageLabel.setFont(labelFont);
    luggageLabel.setForeground(labelColor);
    passengerCapacityLabel.setFont(labelFont);
    passengerCapacityLabel.setForeground(labelColor);

    flightNumberField.setBackground(textFieldColor);
    flightNumberField.setFont(textFieldFont);
    modelField.setBackground(textFieldColor);
    modelField.setFont(textFieldFont);
    luggageField.setBackground(textFieldColor);
    luggageField.setFont(textFieldFont);
    passengerCapacityField.setBackground(textFieldColor);
    passengerCapacityField.setFont(textFieldFont);

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
    panel.add(flightNumberLabel, gbc);
    gbc.gridx++;
    panel.add(flightNumberField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(modelLabel, gbc);
    gbc.gridx++;
    panel.add(modelField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(luggageLabel, gbc);
    gbc.gridx++;
    panel.add(luggageField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    panel.add(passengerCapacityLabel, gbc);
    gbc.gridx++;
    panel.add(passengerCapacityField, gbc);

    gbc.gridy++;
    gbc.gridx=0;
    panel.add(gobackButton, gbc);

    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridwidth = 2;
    panel.add(saveButton, gbc);



    // Action Listeners
    saveButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String flightNumber = flightNumberField.getText();
          String flightModel = modelField.getText();
          String luggage = luggageField.getText();
          String capacity = passengerCapacityField.getText();

          String sql =
            "INSERT INTO flight_details (flight_num, model,luggage_capacity,passenger_capacity)\n" +
            "VALUES (" +
            '"' +
            flightNumber +
            '"' +
            ", " +
            '"' +
            flightModel +
            '"' +
            ", " +
            '"' +
            luggage +
            '"' +
            ", " +
            '"' +
            capacity +
            '"' +
            ");";
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
          // Save flight details logic here
          // For demonstration, just displaying a message
          JOptionPane.showMessageDialog(
            null,
            "Flight details saved:\n" +
            "Flight Number: " +
            flightNumber +
            "\n" +
            "Departure Location: " +
            flightModel +
            "\n" +
            "Arrival Location: " +
            luggage +
            "\n" +
            "Departure Time: " +
            capacity
          );
        }
      }
    );

    gobackButton.addActionListener(
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
    SwingUtilities.invokeLater(() -> new FlightDetailsUI().setVisible(true));
  }
}
