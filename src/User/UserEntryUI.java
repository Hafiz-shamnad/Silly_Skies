package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEntryUI extends JFrame {

    public UserEntryUI() {
        setTitle("Entry Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Components
        JButton paymentButton = new JButton("Payment");
        JButton flightBookingButton = new JButton("Flight Booking");
        JButton cancelFlightButton = new JButton("Cancel Flight");
        JButton logoutButton = new JButton("Logout");

        // Layout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(paymentButton);
        panel.add(flightBookingButton);
        panel.add(cancelFlightButton);
        panel.add(logoutButton);

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

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserEntryUI().setVisible(true);
            }
        });
    }
}
