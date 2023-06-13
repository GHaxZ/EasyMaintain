package mc.plugin.easymaintain.util;

import mc.plugin.easymaintain.EasyMaintain;
import mc.plugin.easymaintain.commands.CommandParser;
import mc.plugin.easymaintain.eventhandlers.MotdChanger;
import mc.plugin.easymaintain.eventhandlers.OnPlayerLogin;

// Initiates all listeners and commands
public class Initiator {
    private static EasyMaintain main = EasyMaintain.getInstance();

    public static void initiate() {
        main.getCommand("em").setExecutor(new CommandParser());

        main.getServer().getPluginManager().registerEvents(new MotdChanger(), main);
        main.getServer().getPluginManager().registerEvents(new OnPlayerLogin(), main);
    }
}
