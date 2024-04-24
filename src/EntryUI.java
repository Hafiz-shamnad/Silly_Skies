import CmnUtilities.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EntryUI extends JFrame {

  public EntryUI() {
    setTitle("Silly Skies");
    setSize(1440, 1024);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame on the screen

    // Create main panel with BorderLayout
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBackground(new Color(238, 238, 238)); // Background color

    // Create right panel for rectangle and buttons
    JPanel rightPanel = new JPanel(null); // Use null layout
    rightPanel.setPreferredSize(new Dimension(620, 1024));
    rightPanel.setBackground(new Color(34, 40, 49)); // Rectangle color

    JPanel leftPanel = new JPanel(new GridBagLayout());
    leftPanel.setPreferredSize(new Dimension(800, 600)); // Adjust the size as needed
    leftPanel.setBackground(new Color(238, 238, 238));

    // Load image
    try {
      // Change "imagePath" to the path where your image is located
      File imageFile = new File(
        "/home/hafiz/Clg_project/Project1-dms/Airline Management/src/Resources/aviation_logo-22 [Converted]-01.png"
      );
      if (imageFile.exists()) {
        Image originalImage = ImageIO.read(imageFile);
        // Scale the image to desired dimensions
        int scaledWidth = 800; // Adjust width as needed
        int scaledHeight = 650; // Adjust height as needed
        Image scaledImage = originalImage.getScaledInstance(
          scaledWidth,
          scaledHeight,
          Image.SCALE_SMOOTH
        );
        ImageIcon icon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(icon);
        getContentPane().add(imageLabel, BorderLayout.WEST); // Add image label to the content pane
      } else {
        System.err.println(
          "Image file not found: " + imageFile.getAbsolutePath()
        );
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    // Create buttons
    RoundedCornerButton userLoginButton = new RoundedCornerButton("USER LOGIN");
    RoundedCornerButton employeeLoginButton = new RoundedCornerButton(
      "EMPLOYEE LOGIN"
    );
    RoundedCornerButton createAccountButton = new RoundedCornerButton(
      "CREATE ACCOUNT"
    );

    // Set button colors
    Color buttonColor = new Color(49, 54, 63);
    userLoginButton.setBackground(buttonColor);
    employeeLoginButton.setBackground(buttonColor);
    createAccountButton.setBackground(buttonColor);

    // Set button text colors
    Color textColor = new Color(238, 238, 238);
    userLoginButton.setForeground(textColor);
    employeeLoginButton.setForeground(textColor);
    createAccountButton.setForeground(textColor);

    // Set button font
    Font buttonFont = new Font("Poppins", Font.BOLD, 22);
    userLoginButton.setFont(buttonFont);
    employeeLoginButton.setFont(buttonFont);
    createAccountButton.setFont(buttonFont);

    // Set button sizes and positions
    int buttonWidth = 469;
    int buttonHeight = 67;
    userLoginButton.setBounds(105, 240, buttonWidth, buttonHeight);
    employeeLoginButton.setBounds(105, 340, buttonWidth, buttonHeight);
    createAccountButton.setBounds(105, 440, buttonWidth, buttonHeight);

    // Add action listeners to the buttons
    userLoginButton.addActionListener(e -> {
      dispose();
      // Add your code for user login action
      new UserLoginUI().setVisible(true);
    });

    employeeLoginButton.addActionListener(e -> {
      dispose();
      // Add your code for employee login action
      new EmployeeLoginUI().setVisible(true);
    });

    createAccountButton.addActionListener(e -> {
      // Add your code for user login action
      dispose();
      new CreateAccountUI().setVisible(true);
    });

    // Add buttons to right panel
    rightPanel.add(userLoginButton);
    rightPanel.add(employeeLoginButton);
    rightPanel.add(createAccountButton);

    // Add right panel to main panel
    mainPanel.add(rightPanel, BorderLayout.EAST);
    mainPanel.add(leftPanel, BorderLayout.WEST);

    // Add main panel to the frame
    add(mainPanel);
  }

  // Custom JButton class with rounded corners
  static class RoundedCornerButton extends JButton {

    public RoundedCornerButton(String text) {
      super(text);
      setContentAreaFilled(false);
      setOpaque(false);
      setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
      if (getModel().isArmed()) {
        g.setColor(getBackground().darker());
      } else {
        g.setColor(getBackground());
      }
      g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
      super.paintComponent(g);
    }

      @Override
      protected void paintBorder(Graphics g) {
          // Set the color of the border to match the background color of the button
          g.setColor(getBackground());
          g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
      }

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new EntryUI().setVisible(true));
  }
}
