package Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EmployeeEntryUI extends JFrame {

  public EmployeeEntryUI() {
    setTitle("Employee Entry");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 200);
    setLocationRelativeTo(null); // Center the window

    // Components
    JButton flightInfoButton = new JButton("View Flight Information");
    JButton passengerDetailsButton = new JButton("View Passenger Details");

    // Layout
    JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.add(flightInfoButton);
    panel.add(passengerDetailsButton);

    // Action Listeners
    flightInfoButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the flight information UI
          // new FlightInfoUI().setVisible(true);
        }
      }
    );

    passengerDetailsButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the passenger details UI
          // new PassengerDetailsUI().setVisible(true);
        }
      }
    );

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
