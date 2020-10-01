import java.util.ArrayList;


public class PluginManager {
	
	private final ArrayList<Plugin> plugins;
	private static PluginManager pluginMagnager;
	
	private PluginManager() {
		this.plugins = new ArrayList<Plugin>() {{
			add(new ReverseEncryptionPlugin());
			add(new ColorPlugin());
		}};
		
	}
	
	public static PluginManager getInstance() { 
        if (pluginMagnager == null) { 
        	pluginMagnager= new PluginManager();
        }
        
        return pluginMagnager; 
    }
	
	public ArrayList<ClientPlugin> getClientPlugins() {
		ArrayList<ClientPlugin> clientPlugins = new ArrayList<ClientPlugin>();
		for (Plugin plugin : this.plugins) {
			clientPlugins.add(plugin.getClientPlugin());
		}
		
		return clientPlugins;
	}
}
