package dcoms.client;

import javax.swing.*;

import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        this.registerButton = new JButton("Register");
        this.loginButton = new JButton("Login");

        leftPanel.add(registerButton);
        rightPanel.add(loginButton);

        buttonPanel.add(leftPanel, BorderLayout.WEST);
        buttonPanel.add(rightPanel, BorderLayout.EAST);
        return buttonPanel;
    }

    private void buttonCallbacks() {
        this.loginButton.addActionListener(e -> {
            String username = this.usernameField.getText().trim();
            char[] password = this.passwordField.getPassword();

            // Validate username
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter your username",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate password
            if (password.length == 0) {
                JOptionPane.showMessageDialog(this,
                        "Please enter your password",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean loginSuccessful = false;

            try {
                var loginUser = ClientInterface.getFunction("loginUser");
                loginSuccessful = loginUser.loginUser(username, password);
            } catch (MalformedURLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (NotBoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                System.out.println("SQL Exception");
            }

            if (!loginSuccessful) {
                JOptionPane.showMessageDialog(this, "Unable to login");
            } else {
                this.cardLayout.show(this.cardPanel, "order");
            }
        });

        this.registerButton.addActionListener(e -> {
            this.cardLayout.show(this.cardPanel, "register");
        });
    }
}