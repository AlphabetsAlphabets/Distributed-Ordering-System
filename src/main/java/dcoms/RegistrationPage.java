package dcoms;

import javax.swing.*;
import java.awt.*;

public class RegistrationPage extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    // Components
    private JTextField usernameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;

    public RegistrationPage(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        this.initUI();
        this.registerCallbacks();
    }

    private void initUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(createHeading());
        this.add(Box.createVerticalStrut(15));

        this.usernameField = new JTextField(15);
        this.add(createLabeledField("Username:", this.usernameField));
        this.add(Box.createVerticalStrut(10));

        this.emailField = new JTextField(15);
        this.add(createLabeledField("Email:", this.emailField));
        this.add(Box.createVerticalStrut(10));

        this.phoneField = new JTextField(15);
        this.add(createLabeledField("Phone:", this.phoneField));
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
        JLabel heading = new JLabel("Register");
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        return heading;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.backButton = new JButton("Back");
        this.registerButton = new JButton("Register");
        buttonPanel.add(backButton);
        buttonPanel.add(registerButton);
        return buttonPanel;
    }

    private void registerCallbacks() {
        this.registerButton.addActionListener(e -> {
            String username = this.usernameField.getText();
            String email = this.emailField.getText();
            String phone = this.phoneField.getText();
            char[] password = this.passwordField.getPassword();
            
            // TODO: Add validation and registration logic
            JOptionPane.showMessageDialog(this, "Registration successful!");
            this.cardLayout.show(this.cardPanel, "login");
        });

        this.backButton.addActionListener(e -> {
            this.cardLayout.show(this.cardPanel, "login");
        });
    }
} 