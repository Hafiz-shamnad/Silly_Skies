package Employee;

import Admin.PassengerInfoUI;
import CmnUtilities.EmployeeLoginUI;
import User.FlightBookingUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EmployeeEntryUI extends JFrame {

  public EmployeeEntryUI() {
      setTitle("Employee Entry");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1200, 720);
      setLocationRelativeTo(null); // Center the window

      // Components
      JButton flightInfoButton = new JButton("View Flight Information");
      JButton passengerDetailsButton = new JButton("View Passenger Details");

      // Apply UI colors and styles
      Font buttonFont = new Font("Poppins", Font.BOLD, 14);
      Color buttonBgColor = new Color(34, 40, 49);
      Color buttonTextColor = new Color(238, 238, 238);

      flightInfoButton.setBackground(buttonBgColor);
      flightInfoButton.setForeground(buttonTextColor);
      flightInfoButton.setFont(buttonFont);

      passengerDetailsButton.setBackground(buttonBgColor);
      passengerDetailsButton.setForeground(buttonTextColor);
      passengerDetailsButton.setFont(buttonFont);

      // Layout
      JPanel panel = new JPanel(null);
      panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      JPanel leftPanel = new JPanel();
      leftPanel.setBounds(0, 0, 422, 1024);
      leftPanel.setBackground(new Color(34, 40, 49));

      // Add logout button on the right side
      JButton logoutButton = new JButton("Logout");
      logoutButton.setForeground(buttonTextColor);
      logoutButton.setBackground(buttonBgColor);
      Font logoutFont = new Font("Poppins", Font.BOLD, 16); // Poppins Semibold
      logoutButton.setFont(logoutFont);
      logoutButton.setBounds(100, 600, 150, 30);
      leftPanel.add(logoutButton);
      panel.add(leftPanel);

      // Add "Welcome" heading
      JLabel welcomeLabel = new JLabel("Welcome ,");
      welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 24));
      welcomeLabel.setForeground(new Color(75, 118, 120));
      welcomeLabel.setBounds(500, 150, 400, 50);
      panel.add(welcomeLabel);

      flightInfoButton.setBounds(500, 225, 300, 50);
      passengerDetailsButton.setBounds(850, 225, 300, 50);

      panel.add(flightInfoButton);
      panel.add(passengerDetailsButton);

    // Action Listeners
    flightInfoButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the flight information UI
            new FlightBookingUI().setVisible(true);
        }
      }
    );

    passengerDetailsButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the passenger details UI
          new PassengerInfoUI().setVisible(true);
        }
      }
    );

    logoutButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new EmployeeLoginUI().setVisible(true);
        }
    });

    add(panel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          new EmployeeEntryUI().setVisible(true);
        }
      }
    );
  }
}
