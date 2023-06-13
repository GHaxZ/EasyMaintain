package mc.plugin.easymaintain.util;

import mc.plugin.easymaintain.EasyMaintain;

public class ServerManager {
    public static void enableMaintenance() {
        EasyMaintain.getInstance().setUnderMaintenance(true);
        PlayerManager.kickAllNotAllowedPlayers();
    }

    public static void disableMaintenance() {
        EasyMaintain.getInstance().setUnderMaintenance(false);
    }
}
