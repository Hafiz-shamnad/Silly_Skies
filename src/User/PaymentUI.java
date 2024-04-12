package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentUI extends JFrame {

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
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve entered payment details
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                String cvv = cvvField.getText();

                // Perform payment processing logic here
                // For demonstration, just displaying a message
                String message = "Payment successful!\n" +
                        "Card Number: " + cardNumber + "\n" +
                        "Expiry Date: " + expiryDate + "\n" +
                        "CVV: " + cvv;
                JOptionPane.showMessageDialog(null, message);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaymentUI().setVisible(true);
            }
        });
    }
}
