package at.coderdojo.linz.minecraft.episode003;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the plugin - not much code needed here.
 *
 * @author Elisabeth Rosemann
 * @since 03.09.2015
 */
public class ExplosionsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Explosions Plugin (003-explosions) enabled.");
        this.getCommand("fire").setExecutor(new ExplosionsExecutor(this));
    }
}
