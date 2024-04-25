package User;

import Jdbc_Connection.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CancelFlightUI extends JFrame {

    private Connection connection; // Declare a Connection object

    public CancelFlightUI() {
        setTitle("Cancel Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null); // Center the window

        JLabel headingLabel = new JLabel("Cancel Your Flight!");
        headingLabel.setForeground(new Color(34, 40, 49)); // Heading color 222831
        headingLabel.setFont(new Font("Poppins", Font.BOLD, 24)); // Heading font
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the heading

        // Components

        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField(20);

        JLabel passportLabel = new JLabel("Passport Number");
        JTextField passportField = new JTextField(20);

        JLabel ticketIdLabel = new JLabel("Ticket ID");
        JTextField ticketIdField = new JTextField(20);

        JButton confirmButton = new JButton("Confirm to cancel");
        JButton goBackButton = new JButton("Go Back");

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
        ticketIdLabel.setFont(labelFont);
        ticketIdLabel.setForeground(labelColor);

        nameField.setFont(textFieldFont);
        nameField.setBackground(textFieldColor);
        passportField.setFont(textFieldFont);
        passportField.setBackground(textFieldColor);
        ticketIdField.setFont(textFieldFont);
        ticketIdField.setBackground(textFieldColor);

        confirmButton.setFont(labelFont);
        confirmButton.setBackground(buttonBgColor);
        confirmButton.setForeground(buttonTextColor);

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
        panel.add(ticketIdLabel, gbc);
        gbc.gridx++;
        panel.add(ticketIdField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx++;
        panel.add(nameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(passportLabel, gbc);
        gbc.gridx++;
        panel.add(passportField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(confirmButton, gbc);

        gbc.gridy++;
        panel.add(goBackButton, gbc);

        add(panel);

        // Action Listeners
        confirmButton.addActionListener(_ -> {
            String bookingID = ticketIdField.getText();
            // Perform cancellation logic
            cancelBooking(bookingID);
        });

        goBackButton.addActionListener(_ -> {
            // Close the window
            dispose();
            // Close the connection if it's open
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed."); // Log connection status
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred while closing the connection: " + ex.getMessage());
            } finally {
                new UserEntryUI().setVisible(true);
            }
        });

        add(panel);
    }

    private void cancelBooking(String bookingID) {
        // Perform cancellation logic with the database
        try {
            // Open the connection if it's not open
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }

            String deleteQuery = "DELETE FROM ticket_details WHERE ticket_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                // Set the parameter for the prepared statement
                preparedStatement.setString(1, bookingID);

                // Execute the query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Booking with ID " + bookingID + " canceled successfully.");
                    String updateQuery = "UPDATE travel_details SET available_seats = available_seats + 1 WHERE travel_id = ?";
                    try (PreparedStatement preparedStatement2 = connection.prepareStatement(updateQuery)) {
                        // Set parameter for the prepared statement
                        preparedStatement2.setString(1, bookingID);
                        // Execute the SQL query
                        preparedStatement2.executeUpdate();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No booking found with ID " + bookingID + ".");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while canceling the booking: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CancelFlightUI().setVisible(true));
    }
}
