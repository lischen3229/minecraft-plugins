package at.coderdojo.linz.minecraft.episode003;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * @author Elisabeth Rosemann
 * @since 03.09.2015
 */
public class ExplosionsExecutor implements CommandExecutor {

    private final ExplosionsPlugin plugin;

    public ExplosionsExecutor(ExplosionsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        plugin.getLogger().info("Got 'fire' command - lighting something up now.");
        //make sure the sender is a player
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Location location = player.getTargetBlock((Set) null, 100).getLocation();
            float explosionPower = 1f; // damage done to surrounding things
            player.getWorld().createExplosion(location, explosionPower);
            plugin.getLogger().info("Added a fake explosion at location " + location + ".");
            return true;
        }
        return false;
    }

}
