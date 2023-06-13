// Main Class

package mc.plugin.easymaintain;

import mc.plugin.easymaintain.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EasyMaintain extends JavaPlugin {
    public static String chatPrefix;
    private static EasyMaintain instance;
    private Config configFile;
    private boolean underMaintenance;


    @Override
    public void onEnable() {
        instance = this;
        configFile = new Config();
        chatPrefix = configFile.getPluginChatPrefix();
        underMaintenance = configFile.getUnderMaintenance();
    }

    @Override
    public void onDisable() {
        getConfig().set("under-maintenance", underMaintenance);
    }

    public static EasyMaintain getInstance() {
        return instance;
    }

    public Config getConfigFile() {
        return configFile;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }
}
