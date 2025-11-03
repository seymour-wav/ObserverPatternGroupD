import javax.swing.*;
import java.awt.*;

public class ObserverMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ObserverMain::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Observer-Only Smart Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // --- Top Bar ---
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(new JLabel("Observer Dashboard"));
        JButton themeToggle = new JButton("Toggle Theme");
        JButton diagToggle = new JButton("Diagnostics On/Off");
        topBar.add(themeToggle);
        topBar.add(diagToggle);
        frame.add(topBar, BorderLayout.NORTH);

        // --- Left Drawer: Subscription Manager ---
        SubscriptionManager subscriptionManager = new SubscriptionManager();
        frame.add(subscriptionManager, BorderLayout.WEST);

        // --- Center: Panels area (placeholder until you add real subjects/observers) ---
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Observer Panels"));
        centerPanel.add(createPlaceholderPanel("Weather Panel"));
        centerPanel.add(createPlaceholderPanel("Transit Panel"));
        centerPanel.add(createPlaceholderPanel("Air Quality Panel"));
        centerPanel.add(createPlaceholderPanel("Alerts Panel"));
        frame.add(centerPanel, BorderLayout.CENTER);

        // --- Diagnostics Overlay (starts hidden) ---
        DiagnosticsOverlay diagnostics = new DiagnosticsOverlay();
        diagnostics.setVisible(false);
        frame.setGlassPane(diagnostics);

        diagToggle.addActionListener(e -> {
            boolean show = !diagnostics.isVisible();
            diagnostics.setVisible(show);
            diagnostics.setOpaque(false);
            frame.getGlassPane().setVisible(show);
        });

        // --- Bottom: Time Bar ---
        TimeBar timeBar = new TimeBar();
        frame.add(timeBar, BorderLayout.SOUTH);

        // --- Show everything ---
        frame.setVisible(true);

        // Example Timer that updates diagnostics overlay every 2 seconds
        new Timer(2000, e -> {
            diagnostics.updateMetrics(Channel.WEATHER,
                    (long) (Math.random() * 50),
                    (int) (Math.random() * 5),
                    (int) (Math.random() * 2),
                    subscriptionManager.isPushMode());
        }).start();
    }

    private static JPanel createPlaceholderPanel(String name) {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder(name));
        p.add(new JLabel("Data goes here"));
        return p;
    }
}