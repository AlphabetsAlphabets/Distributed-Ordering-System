package dcoms.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderPage extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    // Database connection details
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String USER = "star";
    private static final String PASSWORD = "password";

    public OrderPage(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        this.setLayout(new BorderLayout());

        // Create header panel with cancel button
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            this.cardLayout.show(this.cardPanel, "login");
        });
        headerPanel.add(cancelButton);
        this.add(headerPanel, BorderLayout.NORTH);

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fetch food items from database and create buttons
        List<String> foodItems = getFoodItemsFromDatabase();
        
        if (foodItems.isEmpty()) {
            // Fallback to hardcoded food items if database fetch fails
            foodItems = new ArrayList<>();
            foodItems.add("Nasi Lemak");
            foodItems.add("Mee Goreng");
            foodItems.add("Roti Canai");
            foodItems.add("Ramly Burger");
        }
        
        for (String food : foodItems) {
            JButton foodButton = new JButton(food);
            foodButton.setFont(new Font("Arial", Font.BOLD, 16));
            foodButton.setPreferredSize(new Dimension(150, 100));
            foodButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showQuantityDialog(food);
                }
            });
            contentPanel.add(foodButton);
        }

        this.add(contentPanel, BorderLayout.CENTER);
    }
    
    private List<String> getFoodItemsFromDatabase() {
        List<String> foodItems = new ArrayList<>();
        
        try {
            // Register PostgreSQL driver
            Class.forName("org.postgresql.Driver");
            
            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            // Execute query to get food items
            String sql = "SELECT food_name FROM food";
            ResultSet rs = stmt.executeQuery(sql);
            
            // Process results
            while (rs.next()) {
                String foodName = rs.getString("food_name");
                foodItems.add(foodName);
            }
            
            // Clean up
            rs.close();
            stmt.close();
            conn.close();
            
            System.out.println("Successfully fetched " + foodItems.size() + " food items from database");
            
        } catch (Exception e) {
            System.err.println("Error fetching food items from database:");
            e.printStackTrace();
        }
        
        return foodItems;
    }

    private void showQuantityDialog(String foodItem) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        JLabel label = new JLabel("Enter quantity for " + foodItem + ":");
        JTextField quantityField = new JTextField(10);

        panel.add(label);
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Order " + foodItem,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity > 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ordered " + quantity + " " + foodItem,
                            "Order Confirmation",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid quantity greater than 0",
                            "Invalid Quantity",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid number",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
