package at.coderdojo.linz.minecraft.episode004;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Random;

/**
 * @author Elisabeth Rosemann
 * @since 01.09.2015
 */
public class SpawnPlugin extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        getLogger().info("Spwan Plugin (004-creature-spawner) enabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        final Server server = sender.getServer();
        //verify command
        if ("spawn".equalsIgnoreCase(label))
        {
            //broadcast message
            server.broadcastMessage(ChatColor.RED + "The squids are invading!");
            //get all online players
            final Collection<? extends Player> players = server.getOnlinePlayers();
            getLogger().info("Spawning squids for " + players.size() + " players.");
            //iterate over all players
            for (Player player : players)
            {
                //get the location of each player
                Location location = player.getLocation();

                //let the squid fall down close to each player, but not exactly onto the player
//                Random random = new Random();
//                location.setX(location.getX() + random.nextInt(101) - 50);
//                location.setZ(location.getZ() + random.nextInt(101) - 50);
//                location.setY(location.getWorld().getHighestBlockYAt(location) + 20);

                //create the squid
                LivingEntity squid = location.getWorld().spawnCreature(location, CreatureType.SQUID);
//                squid.setVelocity(new Vector(random.nextInt(3) - 1, 0, random.nextInt(3) - 1));
                squid.setFallDistance(0);
            }
            return true;
        }
        else
        {
            return false;
        }

    }
}
