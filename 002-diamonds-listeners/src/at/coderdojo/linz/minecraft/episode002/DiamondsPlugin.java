package at.coderdojo.linz.minecraft.episode002;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the plugin - not much code needed here.
 *
 * @author Elisabeth Rosemann
 * @since 03.09.2015
 */
public class DiamondsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Diamonds Plugin (002-diamonds) enabled.");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerListenerAddingDiamonds(this), this);
        pm.registerEvents(new EntityListenerUnlimitedHealth(this), this);
        pm.registerEvents(new BlockListenerPlaceDiamonds(this), this);
    }
}
