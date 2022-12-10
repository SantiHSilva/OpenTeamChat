package owo.carrot.teamchat.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import owo.carrot.teamchat.Utils.PlayerData;

public class JoinOrLeaveServer implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) { PlayerData.updateLastLogin(event.getPlayer()); }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) { PlayerData.updateLastLogin(event.getPlayer()); }

}
