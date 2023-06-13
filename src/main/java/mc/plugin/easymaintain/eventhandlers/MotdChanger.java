package mc.plugin.easymaintain.eventhandlers;

import mc.plugin.easymaintain.EasyMaintain;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdChanger implements Listener {
    @EventHandler
    public void onServerlistPing(ServerListPingEvent event) {
        if(EasyMaintain.getInstance().isUnderMaintenance()) {
            event.setMotd(EasyMaintain.getInstance().getConfigFile().getModtMessage());

            event.setMaxPlayers(Bukkit.getOnlinePlayers().size());
        }
    }
}
