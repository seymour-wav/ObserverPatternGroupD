import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DiagnosticsOverlay extends JPanel {

    private final Map<Channel, Long> lastUpdate = new HashMap<>();
    private int queueDepth = 0;
    private double avgLatency = 0;
    private int droppedFrames = 0;
    private boolean pushMode = true;

    public DiagnosticsOverlay() {
        setOpaque(false); // Transparent overlay
    }

    public void updateMetrics(Channel ch, long latency, int queue, int dropped, boolean isPush) {
        lastUpdate.put(ch, System.currentTimeMillis());
        this.avgLatency = latency;
        this.queueDepth = queue;
        this.droppedFrames = dropped;
        this.pushMode = isPush;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(10, 10, 260, 140);
        g.setColor(Color.WHITE);
        int y = 30;
        g.drawString("Diagnostics Overlay", 20, y);
        y += 20;
        g.drawString("Mode: " + (pushMode ? "Push" : "Pull"), 20, y);
        y += 20;
        g.drawString("Avg Latency: " + avgLatency + "ms", 20, y);
        y += 20;
        g.drawString("Queue Depth: " + queueDepth, 20, y);
        y += 20;
        g.drawString("Dropped Frames: " + droppedFrames, 20, y);
        y += 20;
        g.drawString("Last Updates:", 20, y);
        y += 15;
        for (Channel ch : lastUpdate.keySet()) {
            g.drawString(ch.name() + ": " + (System.currentTimeMillis() - lastUpdate.get(ch)) + "ms ago", 30, y);
            y += 15;
        }
    }
}