import java.util.ArrayList;


public class PluginManager {
	
	private final ArrayList<Plugin> plugins;
	private static PluginManager pluginManager;
	
	private PluginManager() {
		this.plugins = new ArrayList<Plugin>() {{
			add(new ReverseEncryptionPlugin());
			add(new ColorPlugin());
			//add(new ConsolePlugin());
			add(new GUIPlugin());
		}};
		
	}
	
	public static PluginManager getInstance() {
		if (pluginManager == null) {
			pluginManager= new PluginManager();
		}

		return pluginManager;
	}
	
	public ArrayList<ClientPlugin> getClientPlugins() {
		ArrayList<ClientPlugin> clientPlugins = new ArrayList<ClientPlugin>();
		for (Plugin plugin : this.plugins) {
			clientPlugins.add(plugin.getClientPlugin());
		}
		
		return clientPlugins;
	}
}
