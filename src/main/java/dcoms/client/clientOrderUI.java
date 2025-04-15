package dcoms.client;

import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dcoms.Database;

public class clientOrderUI extends javax.swing.JPanel {

    private CardLayout cardLayout;
    private JPanel parentPanel;
    private List<String> foodNames;

    public clientOrderUI(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.foodNames = new ArrayList<>();
        initComponents();
        loadFoodNames();
    }

    private void loadFoodNames() {
        try {
            Connection conn = Database.getConnection();
            String query = "SELECT food_name FROM food ORDER BY food_id ASC LIMIT 4";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            int buttonIndex = 0;
            while (rs.next() && buttonIndex < 4) {
                String foodName = rs.getString("food_name");
                foodNames.add(foodName);
                
                // Update button text with food name from database
                switch (buttonIndex) {
                    case 0:
                        food1Btn.setText(foodName);
                        break;
                    case 1:
                        food2Btn.setText(foodName);
                        break;
                    case 2:
                        food3Btn.setText(foodName);
                        break;
                    case 3:
                        food4Btn.setText(foodName);
                        break;
                }
                buttonIndex++;
            }
            
            // If fewer than 4 food items were found, disable unused buttons
            if (buttonIndex < 4) {
                switch (buttonIndex) {
                    case 0:
                        food1Btn.setEnabled(false);
                        // fallthrough intentional
                    case 1:
                        food2Btn.setEnabled(false);
                        // fallthrough intentional
                    case 2:
                        food3Btn.setEnabled(false);
                        // fallthrough intentional
                    case 3:
                        food4Btn.setEnabled(false);
                        break;
                }
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading food names: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        food1Btn = new javax.swing.JButton();
        food2Btn = new javax.swing.JButton();
        food3Btn = new javax.swing.JButton();
        food4Btn = new javax.swing.JButton();
        signOutBtn = new javax.swing.JButton();

        jPanel1.setPreferredSize(new java.awt.Dimension(650, 500));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Food Ordering");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        food1Btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food1Btn.setText("Food 1");
        food1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                food1BtnActionPerformed(evt);
            }
        });

        food2Btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food2Btn.setText("Food 2");
        food2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                food2BtnActionPerformed(evt);
            }
        });

        food3Btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food3Btn.setText("Food 3");
        food3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                food3BtnActionPerformed(evt);
            }
        });

        food4Btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food4Btn.setText("Food 4");
        food4Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                food4BtnActionPerformed(evt);
            }
        });

        signOutBtn.setBackground(new java.awt.Color(255, 255, 204));
        signOutBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        signOutBtn.setText("Sign Out");
        signOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(food3Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(food1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(food2Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(food4Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signOutBtn)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(food1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(food2Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(food3Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(food4Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(signOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void food2BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_food2BtnActionPerformed
          handleOrder(food2Btn.getText()); 
    }//GEN-LAST:event_food2BtnActionPerformed

    private void food3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_food3BtnActionPerformed
        // TODO add your handling code here:       
         handleOrder(food3Btn.getText()); 
    }//GEN-LAST:event_food3BtnActionPerformed

    private void food4BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_food4BtnActionPerformed
          handleOrder(food4Btn.getText());       
    }//GEN-LAST:event_food4BtnActionPerformed

    private void signOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutBtnActionPerformed
        cardLayout.show(parentPanel, "login");
    }//GEN-LAST:event_signOutBtnActionPerformed

    private void food1BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_food1BtnActionPerformed
        // TODO add your handling code here:
        handleOrder(food1Btn.getText());    
}

    private void handleOrder(String foodName) {
    // Create an array of quantity options (1 to 10)
    Integer[] quantities = new Integer[10];
    for (int i = 0; i < 10; i++) {
        quantities[i] = i + 1;
    }

    // Get database connection to check if item is in stock before showing quantity dialog
    try {
        Connection conn = Database.getConnection();
        String checkQuery = "SELECT quantity FROM food WHERE food_name = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, foodName);
        ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next()) {
            int availableQuantity = rs.getInt("quantity");
            
            // If quantity is zero, show out of stock message and return
            if (availableQuantity <= 0) {
                JOptionPane.showMessageDialog(this, 
                    foodName + " is out of stock!", 
                    "Out of Stock", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return;
    }

    // Create the combo box with quantity options
    JComboBox<Integer> quantityComboBox = new JComboBox<>(quantities);

    // Create a panel to hold the message and combo box
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(new JLabel("Select quantity for " + foodName + ":"));
    panel.add(quantityComboBox);

    // Show confirm dialog
    int result = JOptionPane.showConfirmDialog(
        this,
        panel,
        foodName + " Order",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    // If OK was clicked
    if (result == JOptionPane.OK_OPTION) {
        int quantity = (Integer) quantityComboBox.getSelectedItem();
        
        // Update the food quantity in the database
        try {
            // Get database connection
            Connection conn = Database.getConnection();
            
            // Check if there's enough quantity available
            String checkQuery = "SELECT quantity FROM food WHERE food_name = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, foodName);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                int availableQuantity = rs.getInt("quantity");
                
                if (availableQuantity >= quantity) {
                    // There's enough stock, update the quantity
                    String updateQuery = "UPDATE food SET quantity = quantity - ? WHERE food_name = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setInt(1, quantity);
                    updateStmt.setString(2, foodName);
                    int updated = updateStmt.executeUpdate();
                    
                    if (updated > 0) {
                        JOptionPane.showMessageDialog(this, "You ordered " + quantity + " of " + foodName + "!\nOrder successful!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error processing your order.", "Order Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, only " + availableQuantity + " " + foodName + " available.", 
                        "Limited Stock", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Food item not found in database.", "Order Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Order Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton food1Btn;
    private javax.swing.JButton food2Btn;
    private javax.swing.JButton food3Btn;
    private javax.swing.JButton food4Btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton signOutBtn;
    // End of variables declaration//GEN-END:variables

}