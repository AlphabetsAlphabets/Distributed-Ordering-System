package dcoms.admin;

import java.awt.CardLayout;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import dcoms.utils.Database;

public class AdminEditMenuUI extends javax.swing.JPanel {

    private CardLayout cardLayout;
    private JPanel parentPanel;

    public AdminEditMenuUI(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        initComponents();
        loadFoodData();
    }

    private void loadFoodData() {
        try {
            Connection conn = Database.getConnection();
            String query = "SELECT food_name, quantity FROM food ORDER BY food_id ASC LIMIT 4";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            int index = 0;
            while (rs.next()) {
                String name = rs.getString("food_name");
                int quantity = rs.getInt("quantity");

                switch (index) {
                    case 0:
                        food1jLabel.setText(name);
                        food1Spinner.setValue(quantity);
                        break;
                    case 1:
                        food2jLabel.setText(name);
                        food2Spinner.setValue(quantity);
                        break;
                    case 2:
                        food3jLabel.setText(name);
                        food3Spinner.setValue(quantity);
                        break;
                    case 3:
                        food4jLabel.setText(name);
                        food4Spinner.setValue(quantity);
                        break;
                }
                index++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading food data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        food1jPanel = new javax.swing.JPanel();
        food1jLabel = new javax.swing.JLabel();
        food1Spinner = new javax.swing.JSpinner();
        food2jPanel = new javax.swing.JPanel();
        food2jLabel = new javax.swing.JLabel();
        food2Spinner = new javax.swing.JSpinner();
        food3jPanel = new javax.swing.JPanel();
        food3jLabel = new javax.swing.JLabel();
        food3Spinner = new javax.swing.JSpinner();
        food4jPanel = new javax.swing.JPanel();
        food4jLabel = new javax.swing.JLabel();
        food4Spinner = new javax.swing.JSpinner();

        jPanel3.setPreferredSize(new java.awt.Dimension(650, 500));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(650, 80));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Edit Menu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jLabel1)
                                .addContainerGap(248, Short.MAX_VALUE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)
                                .addContainerGap(16, Short.MAX_VALUE)));

        backBtn.setBackground(new java.awt.Color(204, 204, 255));
        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        saveBtn.setBackground(new java.awt.Color(255, 255, 204));
        saveBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        food1jPanel.setBackground(new java.awt.Color(204, 255, 255));
        food1jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        food1jPanel.setForeground(new java.awt.Color(255, 255, 255));

        food1jLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food1jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        food1jLabel.setText("Food 1");

        food1Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout food1jPanelLayout = new javax.swing.GroupLayout(food1jPanel);
        food1jPanel.setLayout(food1jPanelLayout);
        food1jPanelLayout.setHorizontalGroup(
                food1jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food1jPanelLayout.createSequentialGroup()
                                .addGroup(food1jPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(food1jPanelLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(food1Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(food1jPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(food1jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9)));
        food1jPanelLayout.setVerticalGroup(
                food1jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food1jPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(food1jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(food1Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE)));

        food2jPanel.setBackground(new java.awt.Color(204, 255, 255));
        food2jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        food2jPanel.setForeground(new java.awt.Color(255, 255, 255));

        food2jLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food2jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        food2jLabel.setText("Food 2");

        food2Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout food2jPanelLayout = new javax.swing.GroupLayout(food2jPanel);
        food2jPanel.setLayout(food2jPanelLayout);
        food2jPanelLayout.setHorizontalGroup(
                food2jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food2jPanelLayout.createSequentialGroup()
                                .addGroup(food2jPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(food2jPanelLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(food2Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(food2jPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(food2jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9)));
        food2jPanelLayout.setVerticalGroup(
                food2jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food2jPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(food2jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(food2Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE)));

        food3jPanel.setBackground(new java.awt.Color(204, 255, 255));
        food3jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        food3jPanel.setForeground(new java.awt.Color(255, 255, 255));

        food3jLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food3jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        food3jLabel.setText("Food 3");

        food3Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout food3jPanelLayout = new javax.swing.GroupLayout(food3jPanel);
        food3jPanel.setLayout(food3jPanelLayout);
        food3jPanelLayout.setHorizontalGroup(
                food3jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food3jPanelLayout.createSequentialGroup()
                                .addGroup(food3jPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(food3jPanelLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(food3Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(food3jPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(food3jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9)));
        food3jPanelLayout.setVerticalGroup(
                food3jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food3jPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(food3jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(food3Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE)));

        food4jPanel.setBackground(new java.awt.Color(204, 255, 255));
        food4jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        food4jPanel.setForeground(new java.awt.Color(255, 255, 255));

        food4jLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        food4jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        food4jLabel.setText("Food 4");

        food4Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout food4jPanelLayout = new javax.swing.GroupLayout(food4jPanel);
        food4jPanel.setLayout(food4jPanelLayout);
        food4jPanelLayout.setHorizontalGroup(
                food4jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food4jPanelLayout.createSequentialGroup()
                                .addGroup(food4jPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(food4jPanelLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(food4Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(food4jPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(food4jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9)));
        food4jPanelLayout.setVerticalGroup(
                food4jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(food4jPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(food4jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(food4Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(food3jPanel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(food4jPanel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(food1jPanel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(food2jPanel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(food1jPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(food2jPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(food3jPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(food4jPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22,
                                        Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(backBtn)
                                        .addComponent(saveBtn))
                                .addGap(22, 22, 22)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backBtnActionPerformed
        cardLayout.show(parentPanel, "admin");
    }// GEN-LAST:event_backBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveBtnActionPerformed
        try {
            Connection conn = Database.getConnection();

            // Get original quantities from DB
            String query = "SELECT food_name, quantity FROM food ORDER BY food_id ASC LIMIT 4";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            String[] foodNames = new String[4];
            int[] originalQuantities = new int[4];

            int i = 0;
            while (rs.next() && i < 4) {
                foodNames[i] = rs.getString("food_name");
                originalQuantities[i] = rs.getInt("quantity");
                i++;
            }

            // Get current spinner values
            int[] currentQuantities = {
                    (Integer) food1Spinner.getValue(),
                    (Integer) food2Spinner.getValue(),
                    (Integer) food3Spinner.getValue(),
                    (Integer) food4Spinner.getValue()
            };

            // Validate all values
            for (int j = 0; j < 4; j++) {
                if (currentQuantities[j] < 0 || currentQuantities[j] > 99) {
                    JOptionPane.showMessageDialog(this,
                            "Quantity for " + foodNames[j] + " must be between 0 and 99.",
                            "Invalid Quantity", JOptionPane.WARNING_MESSAGE);

                    // Reset spinner to original value
                    switch (j) {
                        case 0:
                            food1Spinner.setValue(originalQuantities[j]);
                            break;
                        case 1:
                            food2Spinner.setValue(originalQuantities[j]);
                            break;
                        case 2:
                            food3Spinner.setValue(originalQuantities[j]);
                            break;
                        case 3:
                            food4Spinner.setValue(originalQuantities[j]);
                            break;
                    }
                    return;
                }
            }

            // All good, proceed to update
            String updateSQL = "UPDATE food SET quantity = ? WHERE food_name = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSQL);

            for (int j = 0; j < 4; j++) {
                updateStmt.setInt(1, currentQuantities[j]);
                updateStmt.setString(2, foodNames[j]);
                updateStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Food quantities updated successfully!");
            loadFoodData(); // refresh UI
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_saveBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JSpinner food1Spinner;
    private javax.swing.JSpinner food1Spinner1;
    private javax.swing.JLabel food1jLabel;
    private javax.swing.JPanel food1jPanel;
    private javax.swing.JPanel food1jPanel1;
    private javax.swing.JSpinner food2Spinner;
    private javax.swing.JLabel food2jLabel;
    private javax.swing.JPanel food2jPanel;
    private javax.swing.JSpinner food3Spinner;
    private javax.swing.JLabel food3jLabel;
    private javax.swing.JPanel food3jPanel;
    private javax.swing.JSpinner food4Spinner;
    private javax.swing.JLabel food4jLabel;
    private javax.swing.JPanel food4jPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables
}
