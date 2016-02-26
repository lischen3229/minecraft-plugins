package at.coderdojo.linz.minecraft.episode002;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author Elisabeth Rosemann
 * @since 03.09.2015
 */
public class BlockListenerPlaceDiamonds implements Listener {

    private final DiamondsPlugin plugin;

    public BlockListenerPlaceDiamonds(DiamondsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        //generate a random number
        int random = (int) (Math.random() * 10); //returns between 0 and 1 -> convert to between 0 and 10 and cast to int
        List<Material> onPlacing = Arrays.asList(Material.COBBLESTONE, Material.DIRT, Material.SANDSTONE);  //materials to be replaced
        //only if the number can be divided by 3 -> so not every time!
        if (random % 3 == 0 && onPlacing.contains(event.getBlock().getType())) {
            generateDiamondCube(event.getBlock().getLocation(), 1);
            addStuffToInventoryForHarvestingDiamondBlocks(event.getPlayer());
            plugin.getLogger().info("Generated a diamond cube at location " + event.getBlock().getLocation());
        } else {
            plugin.getLogger().info("A block was placed, but no diamond cube has been generated.");
        }
    }

    private void addStuffToInventoryForHarvestingDiamondBlocks(Player player) {
        List<Material> materialsToAdd = Arrays.asList(Material.IRON_PICKAXE);
        for (Material m : materialsToAdd) {
            ItemStack itemStack = new ItemStack(m); // stack of diamonds
            if (!player.getInventory().contains(m)) {
                player.getInventory().addItem(itemStack);
                plugin.getLogger().info("Added a stack of " + m.name() + " to " + player.getDisplayName() + "'s inventory.");
            }
        }
        player.updateInventory();
    }

    public void generateDiamondCube(Location loc, int length) {
        int x1 = loc.getBlockX();
        int y1 = loc.getBlockY();
        int z1 = loc.getBlockZ();

        int x2 = x1 + length;
        int y2 = y1 + length;
        int z2 = z1 + length;

        World world = loc.getWorld();

        // Loop over the cube in all dimensions
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                for (int z = z1; z < z2; z++) {
                    Block currentBlock = world.getBlockAt(x, y, z);
                    //set block to be a diamond
                    currentBlock.setType(Material.DIAMOND_BLOCK);
                }
            }
        }
    }
}
