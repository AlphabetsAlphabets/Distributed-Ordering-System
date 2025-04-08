package dcoms;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginRegisterPage extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginRegisterPage(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        this.initUI();
        this.buttonCallbacks();
    }

    private void initUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(createHeading());
        this.add(Box.createVerticalStrut(15));

        this.usernameField = new JTextField(15);
        this.add(createLabeledField("Username:", this.usernameField));
        this.add(Box.createVerticalStrut(10));

        this.passwordField = new JPasswordField(15);
        this.add(createLabeledField("Password:", this.passwordField));
        this.add(Box.createVerticalStrut(20));

        this.add(createButtonPanel());
    }

    private JPanel createLabeledField(String labelText, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setMaximumSize(field.getPreferredSize());
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(field);
        return panel;
    }

    private JLabel createHeading() {
        JLabel heading = new JLabel("Login");
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        return heading;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.registerButton = new JButton("Register");
        this.loginButton = new JButton("Login");
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);
        return buttonPanel;
    }

    private void buttonCallbacks() {
        this.loginButton.addActionListener(e -> {
            this.cardLayout.show(this.cardPanel, "hi");
        });

        this.registerButton.addActionListener(e -> {
            char[] password = this.passwordField.getPassword();
            String username = this.usernameField.getText();

            if (username.length() <= 0) {
                JOptionPane.showMessageDialog(this, "Username field cannot be empty.");
                return;
            } else if (password.length <= 0) {
                JOptionPane.showMessageDialog(this, "Password field cannot be empty.");
            }

            try {
                Register.register(this.usernameField.getText(),
                        this.passwordField.getPassword());
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }
}