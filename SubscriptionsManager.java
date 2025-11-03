import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SubscriptionManager extends JPanel {

    private final Map<Channel, JCheckBox> channelBoxes = new HashMap<>();
    private final JCheckBox pushModeToggle = new JCheckBox("Push Mode");
    private final JComboBox<String> prioritySelect = new JComboBox<>(new String[]{"High", "Medium", "Low"});

    public SubscriptionManager() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Subscriptions"));

        for (Channel ch : Channel.values()) {
            JCheckBox box = new JCheckBox(ch.name());
            channelBoxes.put(ch, box);
            add(box);
        }

        add(pushModeToggle);
        add(new JLabel("Observer Priority:"));
        add(prioritySelect);
    }

    public boolean isChannelSelected(Channel ch) {
        return channelBoxes.get(ch).isSelected();
    }

    public boolean isPushMode() {
        return pushModeToggle.isSelected();
    }

    public String getPriority() {
        return (String) prioritySelect.getSelectedItem();
    }
}