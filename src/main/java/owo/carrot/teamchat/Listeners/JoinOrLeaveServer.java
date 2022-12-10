package owo.carrot.teamchat.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import owo.carrot.teamchat.Utils.PlayerData;
import owo.carrot.teamchat.Utils.Utils;
import owo.carrot.teamchat.Utils.YamlFiles;

public class JoinOrLeaveServer implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        if(!PlayerData.verifyDataPlayer(event.getPlayer())){
            PlayerData.createDataPlayer(event.getPlayer());
            Utils.infoServer(YamlFiles.getMessage("prefix") + "Player data created for " + event.getPlayer().getName());
        }
        PlayerData.updateLastLogin(event.getPlayer());
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        PlayerData.updateLastLogin(event.getPlayer());
    }

}
