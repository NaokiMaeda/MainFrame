package ubi.system.plugin;

import java.util.Optional;

public interface PluginFactory {
	String						getName();
	Optional<Plugin>		createPlugin();
	void							startPlugin();
}
