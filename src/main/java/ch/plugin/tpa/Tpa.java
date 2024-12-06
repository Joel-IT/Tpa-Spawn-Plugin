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

public class Tpa extends JavaPlugin {

    private final HashMap<UUID, UUID> tpaRequests = new HashMap<>();

    @Override
    public void onEnable() {
        // Registrierung der Kommandos
        getCommand("tpa").setExecutor(new CommandHandler());
        getCommand("tpaccept").setExecutor(new CommandHandler());
        getCommand("tpdeny").setExecutor(new CommandHandler());
        getLogger().info("TPA Plugin wurde aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TPA Plugin wurde deaktiviert!");
    }

    private class CommandHandler implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Nur Spieler können diesen Befehl verwenden!");
                return true;
            }

            Player player = (Player) sender;

            switch (command.getName().toLowerCase()) {
                case "tpa":
                    if (args.length != 1) {
                        player.sendMessage(ChatColor.YELLOW + "Benutzung: /tpa <spieler>");
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null || target.equals(player)) {
                        player.sendMessage(ChatColor.RED + "Spieler nicht gefunden oder ungültig.");
                        return true;
                    }

                    tpaRequests.put(target.getUniqueId(), player.getUniqueId());
                    target.sendMessage(ChatColor.GREEN + player.getName() + " hat dir eine Teleport-Anfrage gesendet. Tippe /tpaccept um zu akzeptieren oder /tpdeny um abzulehnen.");
                    player.sendMessage(ChatColor.GREEN + "Teleport-Anfrage an " + target.getName() + " gesendet.");
                    break;

                case "tpaccept":
                    if (!tpaRequests.containsKey(player.getUniqueId())) {
                        player.sendMessage(ChatColor.RED + "Du hast keine ausstehenden Teleport-Anfragen.");
                        return true;
                    }

                    UUID requesterId = tpaRequests.get(player.getUniqueId());
                    Player requester = Bukkit.getPlayer(requesterId);
                    if (requester == null) {
                        player.sendMessage(ChatColor.RED + "Der Spieler, der die Anfrage gesendet hat, ist nicht mehr online.");
                        tpaRequests.remove(player.getUniqueId());
                        return true;
                    }

                    tpaRequests.remove(player.getUniqueId());
                    initiateTeleport(requester, player);
                    break;

                case "tpdeny":
                    if (!tpaRequests.containsKey(player.getUniqueId())) {
                        player.sendMessage(ChatColor.RED + "Du hast keine ausstehenden Teleport-Anfragen.");
                        return true;
                    }

                    UUID deniedRequesterId = tpaRequests.get(player.getUniqueId());
                    Player deniedRequester = Bukkit.getPlayer(deniedRequesterId);
                    if (deniedRequester != null) {
                        deniedRequester.sendMessage(ChatColor.RED + player.getName() + " hat deine Teleport-Anfrage abgelehnt.");
                    }

                    player.sendMessage(ChatColor.YELLOW + "Teleport-Anfrage abgelehnt.");
                    tpaRequests.remove(player.getUniqueId());
                    break;
            }
            return true;
        }

        private void initiateTeleport(Player requester, Player target) {
            requester.sendMessage(ChatColor.GREEN + "Teleport-Anfrage akzeptiert. Teleportiere in 3 Sekunden...");
            target.sendMessage(ChatColor.GREEN + "Du hast die Teleport-Anfrage akzeptiert. Teleportiere " + requester.getName() + " in 3 Sekunden...");

            new BukkitRunnable() {
                private int countdown = 3;

                @Override
                public void run() {
                    if (countdown > 0) {
                        requester.sendTitle("", ChatColor.AQUA + "Teleportiere in " + countdown + "...", 0, 20, 0);
                        countdown--;
                    } else {
                        requester.teleport(target.getLocation());
                        requester.sendMessage(ChatColor.GREEN + "Zu " + target.getName() + " teleportiert.");
                        this.cancel();
                    }
                }
            }.runTaskTimer(Tpa.this, 0L, 20L); // 20 ticks = 1 Sekunde
        }
    }
}
