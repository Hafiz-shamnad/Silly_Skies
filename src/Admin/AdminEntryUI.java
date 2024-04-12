package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminEntryUI extends JFrame {

  public AdminEntryUI() {
    setTitle("Admin and Employee Entry");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);
    setLocationRelativeTo(null); // Center the window

    // Components
    JButton passengerInfoButton = new JButton("Passenger Info");
    JButton flightDetailsButton = new JButton("Flight Details");
    JButton flightDeletionButton = new JButton("Flight Deletion");
    JButton flightCreationButton = new JButton("Flight Creation");
    JButton employeeAccountCreationButton = new JButton(
      "Employee Account Creation"
    );
    JButton employeeAccountDeletionButton = new JButton("Employee Account Deletion");

    // Layout
    JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.add(passengerInfoButton);
    panel.add(flightDetailsButton);
    panel.add(flightDeletionButton);
    panel.add(flightCreationButton);
    panel.add(employeeAccountCreationButton);
    panel.add(employeeAccountDeletionButton);

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
