package User;

import Jdbc_Connection.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class FlightBookingUI extends JFrame {
    private String travelId;
    private final JTable flightTable;
    private Connection connection;

    public FlightBookingUI() {
        setTitle("Flight Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 1024);
        setLocationRelativeTo(null); // Center the window
        flightTable = new JTable();
        JScrollPane flightScrollPane = new JScrollPane(flightTable);
        flightScrollPane.setPreferredSize(new Dimension(800, 200));

        // Components
        JLabel headingLabel = new JLabel("Book Your Ticket!");
        headingLabel.setForeground(new Color(34, 40, 49)); // Heading color 222831
        headingLabel.setFont(new Font("Poppins", Font.BOLD, 24)); // Heading font
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the heading

        // Name section
        JLabel nameLabel = new JLabel("Name ");
        nameLabel.setForeground(new Color(75, 118, 120)); // Color 4B7678
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        JTextField nameField = new JTextField(40);
        nameField.setBackground(new Color(217, 217, 217)); // Text field background color D9D9D9

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.setMaximumSize(new Dimension(1440, 1024)); // Limit the width

        // Passport Number section
        JLabel passportLabel = new JLabel("Passport Number ");
        passportLabel.setForeground(new Color(75, 118, 120)); // Color 4B7678
        passportLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        JTextField passportField = new JTextField(40);
        passportField.setBackground(new Color(217, 217, 217)); // Text field background color D9D9D9

        JPanel passportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passportPanel.add(passportLabel);
        passportPanel.add(passportField);
        passportPanel.setMaximumSize(new Dimension(1440, 1024)); // Limit the width

        // Travel ID section
        JLabel travelIdLabel = new JLabel("Travel ID ");
        travelIdLabel.setForeground(new Color(75, 118, 120)); // Color 4B7678
        travelIdLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        JTextField travelIdField = new JTextField(40);
        travelIdField.setBackground(new Color(217, 217, 217)); // Text field background color D9D9D9

        JPanel travelIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        travelIdPanel.add(travelIdLabel);
        travelIdPanel.add(travelIdField);
        travelIdPanel.setMaximumSize(new Dimension(1440, 1024)); // Limit the width

        // Buttons
        JButton bookButton = new JButton("Book Now");
        JButton cancelButton = new JButton("Go Back");
        bookButton.setBackground(new Color(34, 40, 49)); // Button background color 222831
        bookButton.setForeground(new Color(238, 238, 238)); // Button text color eeeeee
        cancelButton.setBackground(new Color(34, 40, 49)); // Button background color 222831
        cancelButton.setForeground(new Color(238, 238, 238)); // Button text color eeeeee
        bookButton.setFont(new Font("Poppins", Font.BOLD, 15));
        cancelButton.setFont(new Font("Poppins", Font.BOLD, 15));

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(headingLabel,gbc);
        gbc.gridy++;
        // Name
        gbc.gridy++;
        panel.add(nameLabel, gbc);
        gbc.gridx++;
        panel.add(nameField, gbc);

        // Passport Number
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(passportLabel, gbc);
        gbc.gridx++;
        panel.add(passportField, gbc);

        // Travel ID
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(travelIdLabel, gbc);
        gbc.gridx++;
        panel.add(travelIdField, gbc);

        // Flight Table
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(flightScrollPane, gbc);

        // Buttons
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 5;
        panel.add(bookButton, gbc);
        gbc.gridy++;
        panel.add(cancelButton, gbc);

        // Action Listeners
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input details from GUI components
                String passengerName = nameField.getText();
                String passportNumber = passportField.getText();
                travelId = travelIdField.getText();
                // Perform booking logic
                bookFlight(passengerName, passportNumber);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the window and the connection
                dispose();
                closeConnection();
            }
        });

        // Populate flight list
        populateFlightTable();
        add(panel);
    }

    private void populateFlightTable() {
        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM travel_details";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Getting column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                Vector<String> columnNames = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                // Getting data
                Vector<Vector<Object>> data = new Vector<>();
                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(resultSet.getObject(i));
                    }
                    data.add(row);
                }

                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                flightTable.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving flight data from database");
        }
    }

    private void bookFlight(String passengerName, String passportNumber) {
        // Perform booking logic with the database
// Perform booking logic with the database
        try {
            String insertQuery = "INSERT INTO ticket_details (passenger_name, passport_number) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                // Set parameters for the prepared statement
                preparedStatement.setString(1, passengerName);
                preparedStatement.setString(2, passportNumber);

                // Execute the SQL query
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    // Retrieve the generated ticket number
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int ticketNumber = generatedKeys.getInt(1);
                            // Display the ticket number to the user
                            JOptionPane.showMessageDialog(null, "Booking confirmed! Ticket Number: " + ticketNumber);

                            // Update the available seats in the travel_details table
                            String updateQuery = "UPDATE travel_details SET available_seats = available_seats - 1 WHERE travel_id = ?";
                            try (PreparedStatement preparedStatement2 = connection.prepareStatement(updateQuery)) {
                                // Set parameter for the prepared statement
                                preparedStatement2.setString(1, travelId);
                                // Execute the SQL query
                                preparedStatement2.executeUpdate();
                            }

                            // Insert the ticket number into the ticket_details table
                            String updateQuery1 = "UPDATE ticket_details SET seat_number = ? WHERE ticket_number = ?";
                            try (PreparedStatement preparedStatement3 = connection.prepareStatement(updateQuery1)) {
                                // Set parameters for the prepared statement
                                preparedStatement3.setString(1, String.valueOf(ticketNumber));
                                preparedStatement3.setInt(2, ticketNumber); // Assuming ticket_number is the primary key
                                // Execute the SQL query
                                preparedStatement3.executeUpdate();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Booking failed. Unable to retrieve ticket number.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Booking failed. No rows affected.");
                }
            }
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while booking the ticket: " + ex.getMessage());
        }

    }


    private void closeConnection() {
        // Close the connection if it's open
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed."); // Log connection status
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while closing the connection: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlightBookingUI().setVisible(true));
    }
}
