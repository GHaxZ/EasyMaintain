package mc.plugin.easymaintain.util;

import mc.plugin.easymaintain.EasyMaintain;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private final EasyMaintain plugin = EasyMaintain.getInstance();
    private List<String> allowedUUIDs = new ArrayList<>();

    public Config() {
        createConfig();
        loadAllowedUUIDs();
    }

    private void loadAllowedUUIDs() {
        allowedUUIDs = plugin.getConfig().getStringList("allowed-uuids");
    }

    private void createConfig() {
        plugin.getConfig().options().copyDefaults();
        plugin.saveConfig();
    }

    // true if successful, false if not
    public void addAllowedUUID(String uuid) {
        allowedUUIDs.add(uuid);
        plugin.getConfig().set("allowed-uuids", allowedUUIDs);
        plugin.saveConfig();
    }

    public void removeAllowedUUID(String uuid) {
        allowedUUIDs.remove(uuid);
        plugin.saveConfig();
        PlayerManager.kickAllNotAllowedPlayers();
    }

    public void resetAllowedUUIDs() {
        allowedUUIDs.clear();
        plugin.getConfig().set("allowed-uuids", allowedUUIDs);
        plugin.saveConfig();
        PlayerManager.kickAllNotAllowedPlayers();
    }

    public List<String> getAllowedUUIDs() {
        return allowedUUIDs;
    }

    public String getPluginChatPrefix() {
        return plugin.getConfig().getString("plugin-settings.plugin-chat-prefix");
    }

    public boolean getUnderMaintenance() {
        return plugin.getConfig().getBoolean("under-maintenance");
    }

    public String getKickMessage() {
        return plugin.getConfig().getString("plugin-settings.kick-message");
    }

    public String getLoginMessage() {
        return plugin.getConfig().getString("plugin-settings.join-message");
    }

    public String getModtMessage() {
        return plugin.getConfig().getString("plugin-settings.motd-message");
    }
}
