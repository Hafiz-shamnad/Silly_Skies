package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminEntryUI extends JFrame {

  public AdminEntryUI() {
      setTitle("Admin and Employee Entry");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1440, 1024);
      setLocationRelativeTo(null); // Center the window

      // Components
      JButton passengerInfoButton = new JButton("Passenger Info");
      JButton flightDetailsButton = new JButton("Flight Details");
      JButton flightDeletionButton = new JButton("Flight Deletion");
      JButton flightCreationButton = new JButton("Flight Creation");
      JButton employeeAccountCreationButton = new JButton("Employee Account Creation");
      JButton employeeAccountDeletionButton = new JButton("Employee Account Deletion");

      // Apply UI colors and styles
      Font buttonFont = new Font("Poppins", Font.BOLD, 14);
      Color buttonBgColor = new Color(34, 40, 49);
      Color buttonTextColor = new Color(238, 238, 238);

      passengerInfoButton.setFont(buttonFont);
      passengerInfoButton.setBackground(buttonBgColor);
      passengerInfoButton.setForeground(buttonTextColor);

      flightDetailsButton.setFont(buttonFont);
      flightDetailsButton.setBackground(buttonBgColor);
      flightDetailsButton.setForeground(buttonTextColor);

      flightDeletionButton.setFont(buttonFont);
      flightDeletionButton.setBackground(buttonBgColor);
      flightDeletionButton.setForeground(buttonTextColor);

      flightCreationButton.setFont(buttonFont);
      flightCreationButton.setBackground(buttonBgColor);
      flightCreationButton.setForeground(buttonTextColor);

      employeeAccountCreationButton.setFont(buttonFont);
      employeeAccountCreationButton.setBackground(buttonBgColor);
      employeeAccountCreationButton.setForeground(buttonTextColor);

      employeeAccountDeletionButton.setFont(buttonFont);
      employeeAccountDeletionButton.setBackground(buttonBgColor);
      employeeAccountDeletionButton.setForeground(buttonTextColor);

      // Layout
      JPanel panel = new JPanel(null);
      panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      // Left panel for logout button
      JPanel leftPanel = new JPanel(null);
      leftPanel.setBounds(0, 0, 422, 1024);
      leftPanel.setBackground(buttonBgColor);

      // Add logout button on the right side
      JButton logoutButton = new JButton("Logout");
      logoutButton.setBounds(100, 600, 150, 30);
      logoutButton.setBackground(new Color(49, 54, 60));
      leftPanel.add(logoutButton);
      panel.add(leftPanel);

      // Add "Welcome" heading
      JLabel welcomeLabel = new JLabel("Welcome");
      welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 24));
      welcomeLabel.setForeground(new Color(75, 118, 120));
      welcomeLabel.setBounds(500, 50, 400, 50);
      panel.add(welcomeLabel);

      // Add buttons on the right side
      passengerInfoButton.setBounds(500, 200, 300, 50);
      flightDetailsButton.setBounds(500, 275, 300, 50);
      flightDeletionButton.setBounds(850, 200, 300, 50);
      flightCreationButton.setBounds(850, 275, 300, 50);
      employeeAccountCreationButton.setBounds(500, 350, 300, 50);
      employeeAccountDeletionButton.setBounds(850, 350, 300, 50);

      panel.add(passengerInfoButton);
      panel.add(flightDetailsButton);
      panel.add(flightDeletionButton);
      panel.add(flightCreationButton);
      panel.add(employeeAccountCreationButton);
      panel.add(employeeAccountDeletionButton);

      add(panel);

    // Action Listeners
    passengerInfoButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the Passenger Info UI
          new PassengerInfoUI().setVisible(true);
        }
      }
    );

    flightDetailsButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the Flight Details UI
          new FlightDetailsUI().setVisible(true);
        }
      }
    );

    flightDeletionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the Flight Deletion UI
          new FlightDeletionUI().setVisible(true);
        }
      }
    );

    flightCreationButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the Flight Creation UI
          new FlightCreationUI().setVisible(true);
        }
      }
    );

    employeeAccountDeletionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the Edit Passenger Info UI
          new DeleteEmployeeAccountUI().setVisible(true);
        }
      }
    );

    employeeAccountCreationButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the Employee Account Creation UI
          new CreateEmployeeAccountUI().setVisible(true);
        }
      }
    );

    add(panel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          new AdminEntryUI().setVisible(true);
        }
      }
    );
  }
}
