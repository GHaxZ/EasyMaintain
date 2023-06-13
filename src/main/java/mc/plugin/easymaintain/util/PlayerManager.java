package mc.plugin.easymaintain.util;

import mc.plugin.easymaintain.EasyMaintain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {
    public static void kickAllNotAllowedPlayers() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!EasyMaintain.getInstance().getConfigFile().getAllowedUUIDs().contains(player.getUniqueId().toString())) {
                player.kickPlayer(EasyMaintain.getInstance().getConfigFile().getKickMessage());
            }
        }
    }
}
