package dcoms.client;

import java.awt.CardLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import dcoms.remote.RemoteInterface;
import dcoms.utils.Database;

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
        startConnectionMonitor();
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

    private void startConnectionMonitor() {
        new Thread(() -> {
            while (true) {
                boolean connected = testServerConnection();

                // update status panel colour
                javax.swing.SwingUtilities.invokeLater(() -> {
                    statusPanel.setBackground(connected ? java.awt.Color.GREEN : java.awt.Color.RED);
                    statusPanel.repaint();
                });

                try {
                    Thread.sleep(5000); // check every 5s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private boolean testServerConnection() {
        try {
            boolean result = ClientInterface.getFunction().ping();
            System.out.println("Ping success: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("Ping failed: " + e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        food1Btn = new javax.swing.JButton();
        food2Btn = new javax.swing.JButton();
        food3Btn = new javax.swing.JButton();
        food4Btn = new javax.swing.JButton();
        signOutBtn = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();

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
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE)));

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

        statusPanel.setBackground(java.awt.Color.lightGray);
        statusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        statusPanel.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
                statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 18, Short.MAX_VALUE));
        statusPanelLayout.setVerticalGroup(
                statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 18, Short.MAX_VALUE));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(food3Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(food1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 179,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(food2Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(food4Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(124, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(signOutBtn)
                                .addGap(20, 20, 20)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(food1Btn, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(food2Btn, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(food3Btn, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(food4Btn, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9,
                                                        Short.MAX_VALUE)
                                                .addComponent(signOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void food1BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_food1BtnActionPerformed
        handleOrder(food1Btn.getText());
    }// GEN-LAST:event_food1BtnActionPerformed

    private void food2BtnActionPerformed(java.awt.event.ActionEvent evt) {
        handleOrder(food2Btn.getText());
    }

    private void food3BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_food3BtnActionPerformed
        handleOrder(food3Btn.getText());
    }// GEN-LAST:event_food3BtnActionPerformed

    private void food4BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_food4BtnActionPerformed
        handleOrder(food4Btn.getText());

    }// GEN-LAST:event_food4BtnActionPerformed

    private void signOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
        cardLayout.show(parentPanel, "login");
    }

    private JSpinner createSpinner(int availableQuantity) {
        // Spinner for quantity selection
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, availableQuantity, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);

        var textField = ((DefaultEditor) quantitySpinner.getEditor()).getTextField();
        textField.setFocusLostBehavior(JFormattedTextField.REVERT);
        textField.addKeyListener(new KeyAdapter() {
            // Needed to inform user if they type something wrong:
            @Override
            public void keyReleased(final KeyEvent kevt) {
                warn(quantitySpinner);
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            // Needed to inform user if the text field loses focus and has a wrong value:
            @Override
            public void focusLost(final FocusEvent fevt) {
                warn(quantitySpinner);
            }
        });

        return quantitySpinner;
    }

    private int createSpinnerDialogue(JSpinner quantitySpinner, String foodName, int availableQuantity) {
        JPanel dialogue = new JPanel();
        dialogue.setLayout(new BoxLayout(dialogue, BoxLayout.Y_AXIS));
        dialogue.add(new JLabel("Select quantity for " + foodName + " (max: " + availableQuantity + "): "));
        dialogue.add(quantitySpinner);

        int result = JOptionPane.showConfirmDialog(
                this,
                dialogue,
                foodName + " Order",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) {
            return 0;
        }

        return 1;
    }

    private void handleOrder(String foodName) {
        int availableQuantity;
        // Initial stock check
        RemoteInterface obj;
        try {
            obj = ClientInterface.getFunction();
            availableQuantity = obj.getQuantity(foodName);

            if (availableQuantity == 0) {
                JOptionPane.showMessageDialog(this,
                        foodName + " is out of stock!",
                        "Out of Stock",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (availableQuantity == -1) {
                JOptionPane.showMessageDialog(this, "Food item not found in database.", "Order Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return;
        }

        JSpinner quantitySpinner = createSpinner(availableQuantity);
        int proceed = createSpinnerDialogue(quantitySpinner, foodName, availableQuantity);

        if (proceed == 0)
            return;

        int quantity = (Integer) quantitySpinner.getValue();

        try {
            int updated = obj.handleOrder(foodName, quantity);

            if (updated != 1) {
                int currentStock = obj.getQuantity(foodName);
                if (currentStock <= -1) {
                    JOptionPane.showMessageDialog(this, "Food item not found in database.",
                            "Order Error",
                            JOptionPane.ERROR_MESSAGE);

                    return;
                }

                String msg = "Sorry, only " + currentStock + " " + foodName + " available.\\n" + //
                        "Your transaction was cancelled.";

                JOptionPane.showMessageDialog(this,
                        msg,
                        "Order Cancelled",
                        JOptionPane.WARNING_MESSAGE);
            }

            JOptionPane.showMessageDialog(this,
                    "You ordered " + quantity + " of " + foodName + "!\nOrder successful!");

            return;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Order Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void warn(final JSpinner spin) {
        final JFormattedTextField jftf = ((DefaultEditor) spin.getEditor()).getTextField();
        try {
            spin.commitEdit(); // Try to commit given value.
        } catch (final ParseException px) {
            // Reset value back to minimum
            jftf.setValue(1);
        }
    }

    // GEN-LAST:event_food1BtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton food1Btn;
    private javax.swing.JButton food2Btn;
    private javax.swing.JButton food3Btn;
    private javax.swing.JButton food4Btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton signOutBtn;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

}
