import java.util.ArrayList;


public class PluginManager {
	
	private final ArrayList<Plugin> plugins;
	private static PluginManager pluginManager;
	
	private PluginManager() {
		this.plugins = new ArrayList<Plugin>() {{
			add(new ReverseEncryptionPlugin());
			add(new ROTEncryptionPlugin());
			add(new ColorPlugin());
			add(new AuthenticationPlugin());
			
//			 add(new ConsolePlugin());
//			GUI plugin does not work in Ubuntu terminal on windows
			 //add(new GUIPlugin());
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

	public ArrayList<ServerPlugin> getServerPlugins() {
		ArrayList<ServerPlugin> serverPlugins = new ArrayList<ServerPlugin>();
		for (Plugin plugin : this.plugins ){
			serverPlugins.add(plugin.getServerPlugin());
		}

		return serverPlugins;
	}
}
