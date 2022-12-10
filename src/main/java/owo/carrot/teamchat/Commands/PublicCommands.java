package owo.carrot.teamchat.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;
import owo.carrot.teamchat.Utils.PlayerData;

@CommandAlias("user|tc|teamchat")
public class PublicCommands extends BaseCommand {

    @Subcommand("toggle")
    public void toggleChat(Player sender){
        if(PlayerData.onTeam(sender)){
            if(PlayerData.getPlayerDataConfig(sender, "chat").equals("team")){
                PlayerData.setDataPlayer(sender, "chat", "global");
                sender.sendMessage("Has cambiado a chat global!");
            }else{
                PlayerData.setDataPlayer(sender, "chat", "team");
                sender.sendMessage("Has cambiado a chat de equipo!");
            }
    }

}
}
