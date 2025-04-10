package dcoms;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class Assignment {

    public static void main(String[] args) {
        // Run the UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Client Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Create and add the login panel
            ClientLoginUI loginPanel = new ClientLoginUI();
            frame.add(loginPanel);
            
            // Set frame size and make it visible
            frame.pack();
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
