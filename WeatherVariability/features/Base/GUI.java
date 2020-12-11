import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
	private WeatherStations weatherStations;

	private JFrame frame;
	private JComboBox<String> cityDropdown;

	private JPanel attributesPanel;

	public GUI(WeatherStations weatherStations) {
		this.weatherStations = weatherStations;
		
		frame = new JFrame("WeatherVariability");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		cityDropdown = new JComboBox<String>();
		ItemListener cityDropdownItemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					changeWeatherStation(e.getItem().toString());
				}
			}
		};
		cityDropdown.addItemListener(cityDropdownItemListener);

		attributesPanel = new JPanel();
		attributesPanel.setLayout(new BoxLayout(attributesPanel, BoxLayout.Y_AXIS));

		frame.getContentPane().add(BorderLayout.SOUTH, cityDropdown);
		frame.getContentPane().add(BorderLayout.CENTER, attributesPanel);

		frame.setVisible(true);
	}
	
	public void populateWeatherStations() {
		ArrayList<String> list = new ArrayList<String>();
		for (String name: weatherStations.getWeatherStationNames()) {
			list.add(name);
		}
		Collections.sort(list);
		for (String name: list) {
			cityDropdown.addItem(name);
		}
	}
	
	private void changeWeatherStation(String stationName) {
		WeatherStation weatherStation = weatherStations.getWeatherStation(stationName);
		
		attributesPanel.removeAll();
		JLabel cityLabel = new JLabel(cityTitle(stationName));
		attributesPanel.add(cityLabel);
		
		// add weather information to GUI
		this.addAttributes(weatherStation);
		
		attributesPanel.revalidate();
		attributesPanel.repaint();
	}
	
	private String cityTitle(String city) {
		return "Weer in " + city;
	}
	
	private void addAttributes(WeatherStation weatherStation) {
		// No attributes by default, everything is configurable
	}
	
	private void addAttribute(WeatherAttribute weatherAttribute) {
		weatherAttribute.applyTranslation();
		
		JPanel attrPanel = new JPanel();
		JLabel labelLabel = new JLabel(weatherAttribute.getDescription());
		Font unboldFont = labelLabel.getFont().deriveFont(labelLabel.getFont().getStyle() & ~Font.BOLD);
		labelLabel.setFont(unboldFont);
		attrPanel.add(labelLabel);
		for (WeatherAttributeShownValue shownValue : weatherAttribute.getShownValues()) {
			attrPanel.add(new JLabel(shownValue.getValue()));
			JLabel unitLabel = new JLabel(shownValue.getUnit());
			unitLabel.setFont(unboldFont);
			attrPanel.add(unitLabel);
		}
		attributesPanel.add(attrPanel);
	}
}
