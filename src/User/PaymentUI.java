package User;

import Jdbc_Connection.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PaymentUI extends JFrame {
    Connection connection = null;

    public PaymentUI() {
        setTitle("Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Components
        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField(20);
        JLabel expiryDateLabel = new JLabel("Expiry Date:");
        JTextField expiryDateField = new JTextField(10);
        JLabel cvvLabel = new JLabel("CVV:");
        JTextField cvvField = new JTextField(5);
        JButton payButton = new JButton("Pay");
        JButton cancelButton = new JButton("Cancel");

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(cardNumberLabel, gbc);
        gbc.gridx++;
        panel.add(cardNumberField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(expiryDateLabel, gbc);
        gbc.gridx++;
        panel.add(expiryDateField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(cvvLabel, gbc);
        gbc.gridx++;
        panel.add(cvvField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(payButton, gbc);

        gbc.gridy++;
        panel.add(cancelButton, gbc);

        // Action Listeners
        payButton.addActionListener(_ -> {
            // Retrieve entered payment details
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();
            String cvv = cvvField.getText();
            // Database connection

            try {
                // Establish database connection
                connection = DatabaseConnection.getConnection();

                // Create SQL query to insert payment details into the database
                String sql = "INSERT INTO payments (card_number, expiry_date, cvv) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, cardNumber);
                statement.setString(2, expiryDate);
                statement.setString(3, cvv);

                // Execute the insert query
                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, """
                                            String message = "Payment successful!\\n" +
                                                    "Card Number: " + cardNumber + "\\n" +
                                                    "Expiry Date: " + expiryDate + "\\n" +
                                                    "CVV: " + cvv;
                            Payment details inserted successfully!""");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to insert payment details.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            } finally {
                try {
                    // Close the database connection
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            // For demonstration, just displaying a message
        });

        cancelButton.addActionListener(_ -> {
            dispose(); // Close the window
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentUI().setVisible(true));
    }
}
