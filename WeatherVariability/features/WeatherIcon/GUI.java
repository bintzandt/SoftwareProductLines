import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Dimension;
import java.lang.ClassLoader;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GUI {
	private void addAttributes(WeatherStation weatherStation) {
		JPanel panel = new JPanel();
		panel.add(new ImageComponent(weatherStation.getIcoonactueel()));
		attributesPanel.add(panel);
		original(weatherStation);
	}
	
	class ImageComponent extends Component {
		BufferedImage img;
		
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
		
		public BufferedImage loadIcon(String path){
			BufferedImage img=null;
			  try {
			    URL urlInput = new URL(path);
			    img= ImageIO.read(urlInput);
			  }
			 catch (  Exception e) {
			    System.out.println("Exception occured: " + e.getMessage() + " - ");
			  }
			  return (img);
		}
		
		public ImageComponent(String path) {
			img = this.loadIcon(path);
		}
		
		public Dimension getPreferredSize() {
			if (img == null) {
				return new Dimension(30,30);
			} else {
				return new Dimension(img.getWidth(), img.getHeight());
			}
		}
	}
}
