package ch.plugin.tpa;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class spawn {
    private final HashMap<UUID, UUID> tpaRequests = new HashMap<>();

    @Override
    public void onEnable() {
        // Registrierung der Kommandos
        getCommand("spawn").setExecutor(new Tpa.CommandHandler());
}

    Player player = (Player) sender;

            switch (command.getName().toLowerCase()) {
        case "spawn":
            if (args.length != 1) {
                player.sendMessage(ChatColor.YELLOW + "Benutzung: /spawn <spieler>");
                return false;
            }