package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEntryUI extends JFrame {

    public UserEntryUI() {
        setTitle("Entry Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 1024);
        setLocationRelativeTo(null); // Center the window

        // Components
        JButton paymentButton = new JButton("Payment");
        JButton flightBookingButton = new JButton("Flight Booking");
        JButton cancelFlightButton = new JButton("Cancel Flight");

        // Apply UI colors and styles
        Color buttonBgColor = new Color(34, 40, 49); // Dark blue
        Color buttonTextColor = new Color(238, 238, 238); // Light gray
        Font buttonFont = new Font("Poppins", Font.BOLD, 16);

        paymentButton.setBackground(buttonBgColor);
        paymentButton.setForeground(buttonTextColor);
        paymentButton.setFont(buttonFont);

        flightBookingButton.setBackground(buttonBgColor);
        flightBookingButton.setForeground(buttonTextColor);
        flightBookingButton.setFont(buttonFont);

        cancelFlightButton.setBackground(buttonBgColor);
        cancelFlightButton.setForeground(buttonTextColor);
        cancelFlightButton.setFont(buttonFont);

        JPanel panel = new JPanel(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Left panel for logout button
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(0, 0, 422, 1024);
        leftPanel.setBackground(buttonBgColor);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(buttonTextColor);
        logoutButton.setBackground(buttonBgColor);
        Font logoutFont = new Font("Poppins", Font.BOLD, 16); // Poppins Semibold
        logoutButton.setFont(logoutFont);
        logoutButton.setBounds(100, 650, 150, 30);
        leftPanel.add(logoutButton);
        panel.add(leftPanel);

        // Main panel for buttons
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(75, 118, 120));
        welcomeLabel.setBounds(500, 150, 400, 50);
        panel.add(welcomeLabel);

        flightBookingButton.setBounds(500, 225, 300, 50);
        cancelFlightButton.setBounds(850, 225, 300, 50);
        paymentButton.setBounds(500, 300, 300, 50);

        panel.add(paymentButton);
        panel.add(flightBookingButton);
        panel.add(cancelFlightButton);

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Add components to frame
        add(panel);

        // Action Listeners
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Payment UI
                new PaymentUI().setVisible(true);
            }
        });

        flightBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Flight Booking UI
                new FlightBookingUI().setVisible(true);
            }
        });

        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Cancel Flight UI
                new CancelFlightUI().setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Cancel Flight UI
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserEntryUI().setVisible(true);
            }
        });
    }
}
