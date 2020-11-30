import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherStations {
	
	private final String url = "https://data.buienradar.nl/1.0/feed/xml";
	private final HashMap<String, WeatherStation> l; 
	
	public static WeatherStations weatherStations;
	
	private WeatherStations() {
		this.l = new HashMap<String, WeatherStation>();
		
	    try
	    {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();	        
	        Document doc = db.parse(new URL(url).openStream());
	        
	        NodeList weerstations = doc.getElementsByTagName("weerstation");     
        	for (int i=0; i< weerstations.getLength(); i++) {
        		Node weerstation_node = weerstations.item(i);
        		
        		if (weerstation_node.getNodeType() == Node.ELEMENT_NODE) {
        			Element weerstation_element = (Element) weerstation_node;
        			String regio = weerstation_element.getElementsByTagName("stationnaam").item(0).getAttributes().getNamedItem("regio").getTextContent();
        			WeatherStation weatherStation = new WeatherStation(weerstation_element, regio);
        			 
        			 l.put(regio, weatherStation);
        		}
	        }

	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}

	public static WeatherStations getInstance() { 
        return weatherStations == null ? new WeatherStations() : weatherStations;  
    }
	
	public WeatherStation getWeatherStation(String key) {
		return this.l.get(key);
	}
	
	public Set<String> getWeatherStationNames() {
		return this.l.keySet();
	}
	
	// debug code
	public void printWeatherStations() {
		for (String key: this.l.keySet()) {
			System.out.println(this.l.get(key));			
		}
	}
	
	
}
