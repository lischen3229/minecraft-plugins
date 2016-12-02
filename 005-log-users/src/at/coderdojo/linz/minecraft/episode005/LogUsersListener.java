package at.coderdojo.linz.minecraft.episode005;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Elisabeth Rosemann
 * @since 01.09.2015
 */
public class LogUsersListener implements Listener {

    private final LogUsersPlugin plugin;
    private static final String USERS_FILENAME = "c:/temp/users.txt";
    private Map<String, String> playersAndIps;

    public LogUsersListener(LogUsersPlugin plugin) {
        //initialise the plugin because we need it to write to the logs
        this.plugin = plugin;
        //read all players from the file
        playersAndIps = readPlayersFromFile();
    }

    @EventHandler
    public void onUserLogin(PlayerLoginEvent event) {
        //add player to the list
        addPlayer(event.getPlayer());
        //write the list to the file again
        writePlayersToFile();
    }

    private Map<String, String> readPlayersFromFile() {
        //create an empty map where we put everything in
        Map<String, String> result = new HashMap<>();
        //get the file from the file system
        Path file = Paths.get(LogUsersListener.USERS_FILENAME);
        //file handling might throw an exception, so surround with try/catch
        try {
            //read all lines from the file
            List<String> lines = Files.readAllLines(file);
            //split each line so the key is the user name, the value is the IP
            for (String line : lines) {
                //split the current line
                String[] entry = line.split(":");
                //add the values into the result
                result.put(entry[0], entry[1]);
            }
        } catch (IOException e) {
            //when there is an exception, write it to the server log
            plugin.getLogger().warning("Error on reading file " + USERS_FILENAME + ": " + e);
        }
        return result;
    }

    private void addPlayer(Player p) {
        //check if the player exists, and only add it to the list if it's not empty
        if (p != null) {
            //get name and ip from the player element
            String name = p.getDisplayName();
            String ip = "-";
            if (p.getAddress() != null && p.getAddress().getHostName() != null) {
                ip = p.getAddress().getHostName();
            }
            //add it to the list
            playersAndIps.put(name, ip);
        } else {
            plugin.getLogger().warning("Cannot add empty user to the list.");
        }
    }

    private void writePlayersToFile() {
        //create object to put all lines into
        List<String> linesToWrite = new ArrayList<>();
        //get the file from the file system
        Path file = Paths.get(LogUsersListener.USERS_FILENAME);
        //change map entry to simple string
        for (Map.Entry<String, String> currLine : playersAndIps.entrySet()) {
            //add the values into the result
            linesToWrite.add(currLine.getKey() + ":" + currLine.getValue());
        }
        //actually write all lines to the file
        try {
            Files.write(file, linesToWrite);
        } catch (IOException e) {
            //when there is an exception, write it to the server log
            plugin.getLogger().warning("Error on reading file " + USERS_FILENAME + ": " + e);
        }
    }

}
