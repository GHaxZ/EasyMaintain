package mc.plugin.easymaintain.eventhandlers;

import mc.plugin.easymaintain.EasyMaintain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class OnPlayerLogin implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if(EasyMaintain.getInstance().isUnderMaintenance()) {
            if(!EasyMaintain.getInstance().getConfigFile().getAllowedUUIDs().contains(event.getPlayer().getUniqueId().toString())) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, EasyMaintain.getInstance().getConfigFile().getLoginMessage());
            }
        }
    }
}
