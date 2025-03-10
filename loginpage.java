import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    // Components
    private JLabel websiteLabel, loginLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton submitButton, forgotPasswordButton, createAccountButton;

    public LoginPage() {
        // Set up the JFrame
        setTitle("Gym Optimizer - Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridBagLayout());

        // Initialize components
        websiteLabel = new JLabel("Gym Optimizer");
        websiteLabel.setFont(new Font("Arial", Font.BOLD, 24));

        loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("password")) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(LoginPage.this, "Please contact support to reset your password.");
            }
        });

        createAccountButton = new JButton("Create New Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(LoginPage.this, "Redirecting to account creation page...");
            }
        });

        // Add components to the JFrame using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Website Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(websiteLabel, gbc);

        // Login Label
        gbc.gridy = 1;
        add(loginLabel, gbc);

        // Username Label and Field
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password Label and Field
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        // Show Password Checkbox
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(showPasswordCheckBox, gbc);

        // Submit Button
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Forgot Password Button
        gbc.gridy = 6;
        add(forgotPasswordButton, gbc);

        // Create New Account Button
        gbc.gridy = 7;
        add(createAccountButton, gbc);
    }

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true); // Display the login page
            }
        });
    }
}
