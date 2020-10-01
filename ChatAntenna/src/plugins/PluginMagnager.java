import java.util.ArrayList;
import java.util.List;

public class PluginMagnager {
	
	private final List<PluginInterface> plugins;
	private static PluginMagnager pluginMagnager;
	
	private PluginMagnager() {
		this.plugins = new ArrayList<PluginInterface>();
	}
	
	public static PluginMagnager getInstance() { 
        if (pluginMagnager == null) { 
        	pluginMagnager= new PluginMagnager();
        }
        
        return pluginMagnager; 
    }
	
	public void addPlugin(PluginInterface plugin) {
		this.plugins.add(plugin);
	}
}
