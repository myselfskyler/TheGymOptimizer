import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminHomePage extends JFrame {

    private JPanel cardPanel;
    private Connection connection;

    public AdminHomePage() {
        setTitle("Gym Optimizer - Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize database connection
        initializeDatabase();

        // Create card panel
        cardPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add cards to the panel
        addCard("Total Members", getTotalMembers());
        addCard("Active Members", getActiveMembers());
        addCard("Add New Member", 0); // Placeholder for action
        addCard("Update Member Details", 0); // Placeholder for action
        addCard("Payments", getTotalPayments());
        addCard("Manage Gym", 0); // Placeholder for action
        addCard("Trainer", getTotalTrainers());

        // Add panel to the frame
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void initializeDatabase() {
        try {
            // Replace with your database URL, username, and password
            String url = "jdbc:mysql://localhost:3306/gym_optimizer";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getTotalMembers() {
        int totalMembers = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM members";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalMembers = resultSet.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalMembers;
    }

    private int getActiveMembers() {
        int activeMembers = 0;
        try {
            String query = "SELECT COUNT(*) AS active FROM members WHERE status = 'active'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                activeMembers = resultSet.getInt("active");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activeMembers;
    }

    private int getTotalPayments() {
        int totalPayments = 0;
        try {
            String query = "SELECT COUNT(*) AS payments FROM payments";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalPayments = resultSet.getInt("payments");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPayments;
    }

    private int getTotalTrainers() {
        int totalTrainers = 0;
        try {
            String query = "SELECT COUNT(*) AS trainers FROM trainers";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalTrainers = resultSet.getInt("trainers");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalTrainers;
    }

    private void addCard(String label, int value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);

        JLabel valueLabel = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        card.add(valueLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(label, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        card.add(titleLabel, BorderLayout.SOUTH);

        cardPanel.add(card);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminHomePage::new);
    }
}
