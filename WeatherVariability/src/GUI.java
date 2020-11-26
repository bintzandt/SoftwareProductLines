import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	private JFrame frame;
	private JLabel cityLabel;
	
	public GUI() {
		frame = new JFrame("WeatherVariability");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		cityLabel = new JLabel("");
		
		JComboBox<String> cityDropdown = new JComboBox<>();
		ItemListener itemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cityLabel.setText("Weather in " + e.getItem().toString());
				}
			}
		};
		cityDropdown.addItemListener(itemListener);
		cityDropdown.addItem("Almelo");
		cityDropdown.addItem("Hengelo");

		JPanel attributesPanel = new JPanel();
		attributesPanel.setLayout(new BoxLayout(attributesPanel, BoxLayout.Y_AXIS));

		attributesPanel.add(cityLabel);
		JPanel attr1Panel = new JPanel();
		attr1Panel.add(new JLabel("Temperature:"));
		attr1Panel.add(new JLabel("40°C"));
		attributesPanel.add(attr1Panel);

		frame.getContentPane().add(BorderLayout.SOUTH, cityDropdown);
		frame.getContentPane().add(BorderLayout.CENTER, attributesPanel);

		frame.setVisible(true);
	}
}
