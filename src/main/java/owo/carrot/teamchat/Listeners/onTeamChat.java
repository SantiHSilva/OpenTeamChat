package owo.carrot.teamchat.Listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import owo.carrot.teamchat.Utils.PlayerData;
import owo.carrot.teamchat.Utils.TeamUtils;

public class onTeamChat implements Listener {

    @EventHandler
    public void talkWithTeam(AsyncPlayerChatEvent event) {

        if(PlayerData.getPlayerDataConfig(event.getPlayer(), "chat").equals("team")){
            event.setCancelled(true);
            TeamUtils.sendTeamMessage(event.getPlayer(), event);
        };

    }

}
