package at.coderdojo.linz.minecraft.episode002;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author Elisabeth Rosemann
 * @since 03.09.2015
 */
public class EntityListenerUnlimitedHealth implements Listener {

    private final DiamondsPlugin plugin;

    public EntityListenerUnlimitedHealth(DiamondsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        //for players only
        if (event.getEntity() instanceof Player) {
            //when the player would be damaged, just cancel the event, so no damage is done to the player
            event.setCancelled(true);
            plugin.getLogger().info("Player would have been damaged for " + event.getDamage() + " damage, which has been cancelled.");
        }
    }

}
