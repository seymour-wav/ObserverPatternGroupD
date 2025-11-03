import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TimeBar extends JPanel {

    private final JButton playButton = new JButton("▶");
    private final JButton pauseButton = new JButton("⏸");
    private final JButton stepButton = new JButton("⏭");
    private final JComboBox<String> speedSelect = new JComboBox<>(new String[]{"1×", "5×", "20×"});
    private final JSlider scrubber = new JSlider(0, 100, 0);

    public TimeBar() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(BorderFactory.createTitledBorder("Time Bar"));

        add(playButton);
        add(pauseButton);
        add(stepButton);
        add(new JLabel("Speed:"));
        add(speedSelect);
        add(scrubber);

        // Example action bindings
        playButton.addActionListener(this::onPlay);
        pauseButton.addActionListener(this::onPause);
        stepButton.addActionListener(this::onStep);
    }

    private void onPlay(ActionEvent e) {
        System.out.println("Simulation started at speed " + speedSelect.getSelectedItem());
    }

    private void onPause(ActionEvent e) {
        System.out.println("Simulation paused");
    }

    private void onStep(ActionEvent e) {
        System.out.println("Stepped one tick");
    }

    public int getSpeedMultiplier() {
        return switch ((String) speedSelect.getSelectedItem()) {
            case "5×" -> 5;
            case "20×" -> 20;
            default -> 1;
        };
    }
}