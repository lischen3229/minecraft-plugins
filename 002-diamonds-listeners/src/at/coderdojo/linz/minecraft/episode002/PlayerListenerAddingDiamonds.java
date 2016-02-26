package at.coderdojo.linz.minecraft.episode002;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author Elisabeth Rosemann
 * @since 03.09.2015
 */
public class PlayerListenerAddingDiamonds implements Listener {

    private final DiamondsPlugin plugin;

    public PlayerListenerAddingDiamonds(DiamondsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        plugin.getLogger().info("Got onLogin().");

        Player player = event.getPlayer(); //the player who logged in
        PlayerInventory inventory = player.getInventory(); //the players inventory
        ItemStack itemStack = new ItemStack(Material.DIAMOND); // stack of diamonds
        if (!inventory.contains(Material.DIAMOND)) {
            //not working - throwing NPE in bukkit code on updateInventory - but only on PlayerLoginEvent
//            inventory.addItem(itemStack);
//            player.updateInventory();
            //putting stack instead in front of the user
            player.setItemInHand(itemStack);
            player.sendMessage("Welcome! Here are some diamonds for you.");
            plugin.getLogger().info("Gave diamonds to player " + player.getDisplayName() + ".");
        } else {
            player.sendMessage("Welcome! No diamonds for you today.");
            plugin.getLogger().info("Player " + player.getDisplayName() + " has already some diamonds, no need to give him any more.");
        }
    }
}
