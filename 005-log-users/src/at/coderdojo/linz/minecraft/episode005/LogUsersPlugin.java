package at.coderdojo.linz.minecraft.episode005;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Elisabeth Rosemann
 * @since 01.09.2015
 */
public class LogUsersPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Logging Users Plugin (005-log-users) enabled.");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new LogUsersListener(this), this);
    }
}
