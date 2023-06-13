package mc.plugin.easymaintain.commands;

import mc.plugin.easymaintain.EasyMaintain;
import mc.plugin.easymaintain.util.PlayerManager;
import mc.plugin.easymaintain.util.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandParser implements CommandExecutor {
    private String helpText = EasyMaintain.chatPrefix + "Here's a list of all available commands:\n" +
            "/em start - Start maintenance\n" +
            "/em stop - Stop maintenance\n" +
            "/em add <username> - Add username to allowed players\n" +
            "/em remove <username> - Remove username from allowed players\n" +
            "/em resetallowed - Resets the allowed players\n" +
            "/em showallowed - Shows all currently allowed players";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length < 1) {
            commandSender.sendMessage(helpText);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start" -> {
                if(!EasyMaintain.getInstance().isUnderMaintenance()) {
                    ServerManager.enableMaintenance();
                    Bukkit.broadcastMessage(EasyMaintain.chatPrefix + "The server is now under maintenance.");
                } else {
                    commandSender.sendMessage(EasyMaintain.chatPrefix + "The server is already under maintenance.");
                }
            }

            case "stop" -> {
                if(EasyMaintain.getInstance().isUnderMaintenance()) {
                    ServerManager.disableMaintenance();
                    Bukkit.broadcastMessage(EasyMaintain.chatPrefix + "The server is now no longer under maintenance.");
                } else {
                    commandSender.sendMessage(EasyMaintain.chatPrefix + "The server is currently not under maintenance.");
                }
            }

            case "resetallowed" -> {
                if(args.length > 1 && args[1].equalsIgnoreCase("confirm")) {
                    EasyMaintain.getInstance().getConfigFile().resetAllowedUUIDs();
                    commandSender.sendMessage(EasyMaintain.chatPrefix + "All players were removed from the allowed list.");
                    Bukkit.getLogger().info(EasyMaintain.chatPrefix + "All players were removed from the allowed list.");
                } else {
                    commandSender.sendMessage(EasyMaintain.chatPrefix + "Keep in mind, that this will delete all allowed players.\n" +
                            "Please enter \"/em resetallowed confirm\" to confirm. ");
                }
            }

            case "add" -> {
                if(args.length > 1) {
                    if(Bukkit.getPlayerUniqueId(args[1]) != null) {
                        if(!EasyMaintain.getInstance().getConfigFile().getAllowedUUIDs().contains(Bukkit.getPlayerUniqueId(args[1]).toString())) {
                            EasyMaintain.getInstance().getConfigFile().addAllowedUUID(Bukkit.getPlayerUniqueId(args[1]).toString());
                            commandSender.sendMessage(EasyMaintain.chatPrefix + "The player \"" + args[1] + "\" was successfully added.");
                        } else {
                            commandSender.sendMessage(EasyMaintain.chatPrefix + "The player \"" + args[1] + "\" is already added.");
                        }
                    } else {
                        commandSender.sendMessage(EasyMaintain.chatPrefix + "The player \"" + args[1] + "\" was not found.");
                    }
                } else {
                    commandSender.sendMessage(EasyMaintain.chatPrefix + "Please enter the name of the player you want to add.");
                }
            }

            case "remove" -> {
                if(args.length > 1) {
                    if(Bukkit.getPlayerUniqueId(args[1]) != null &&
                            EasyMaintain.getInstance().getConfigFile().getAllowedUUIDs().contains(Bukkit.getPlayerUniqueId(args[1]).toString())) {
                        EasyMaintain.getInstance().getConfigFile().removeAllowedUUID(args[1]);
                        commandSender.sendMessage(EasyMaintain.chatPrefix + "The player \"" + args[1] + "\" was successfully removed from the allowed list.");
                    } else {
                        commandSender.sendMessage(EasyMaintain.chatPrefix + "The player \"" + args[1] + "\" wasn't found in the allowed list.");
                    }
                } else {
                    commandSender.sendMessage(EasyMaintain.chatPrefix + "Please enter the name of the player you want to remove.");
                }
            }

            default -> {
                commandSender.sendMessage(EasyMaintain.chatPrefix + "The command \"" + args[0] + "\" is not valid. Run /em for help.");
            }
        }

        return true;
    }
}
