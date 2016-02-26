package at.coderdojo.linz.minecraft.episode001;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Elisabeth Rosemann
 * @since 01.09.2015
 */
public class HelloMePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello World Plugin (001-say-hello) enabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.DARK_PURPLE + "Hello coder-dojo." + ChatColor.GOLD + " See you next Friday, says " + sender.getName() + ".");
        getLogger().info("Hello coder-dojo has been called.");
        return true;
    }
}
