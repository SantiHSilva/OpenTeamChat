package owo.carrot.teamchat.Utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.ArrayList;
import java.util.List;

import static owo.carrot.teamchat.Utils.YamlFiles.teamPathConfig;

public class TeamUtils {

    public static void createTeam(String teamName, String leader) {

        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(leader);
        PlayerData.verifyDataPlayer(targetPlayer);

        try{
            YamlFile teamFile = new YamlFile(teamPathConfig);
            teamFile.load();
            List<String> leaderCache = new ArrayList<>();
            leaderCache.add(leader);
            teamFile.set(teamName, leaderCache);
            teamFile.save();
        } catch(Exception e){
            e.printStackTrace();
            YamlFiles.initialTeams();
            createTeam(teamName, leader);
        }
    }

    public static Boolean verifyTeam(String teamName){
        YamlFile file = new YamlFile(teamPathConfig);
        try {
            file.load();
        } catch (final Exception e) {
            e.printStackTrace();
            YamlFiles.initialTeams();
        }
        return file.isList(teamName);
    }

    public static void addORemovePlayer(Player sender, String teamName, String target, Boolean remove){

        try{
            OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(target);
            PlayerData.verifyDataPlayer(targetPlayer);

            YamlFile teamFile = new YamlFile(teamPathConfig);
            teamFile.load();

            List<String> playerCache = teamFile.getStringList(teamName.replace("-", " "));

            if(remove){
                if(PlayerData.onTeam(targetPlayer)){

                    playerCache.remove(targetPlayer.getName());
                    teamFile.set(teamName.replace("-", " "), playerCache);

                    PlayerData.setDataPlayer(targetPlayer, "team", "none");

                    teamFile.save();
                    sender.sendMessage( targetPlayer + " removed from team " + teamName.replace("-", " "));
                } else {
                    sender.sendMessage(targetPlayer.getName() + " NO esta en un equipo");
                }
            } else {
                if(!PlayerData.onTeam(targetPlayer)){ // si no esta en un equipo

                    playerCache.add(targetPlayer.getName());
                    teamFile.set(teamName.replace("-", " "), playerCache);

                    PlayerData.setDataPlayer(targetPlayer, "team", teamName.replace("-", " "));

                    teamFile.save();
                    sender.sendMessage( targetPlayer + " added to team " + teamName.replace("-", " "));
                } else {
                    sender.sendMessage(targetPlayer.getName() + " ya esta en un equipo (" + PlayerData.getPlayerDataConfig(targetPlayer, "team") + ").");
                }
            }

        } catch(Exception e){
            e.printStackTrace();
            YamlFiles.initialTeams();
            sender.sendMessage("El equipo " + teamName.replace("-", " ") + " no existe");
        }
    }

    public static List<String> getTeamPlayers(String teamName){
        YamlFile file = new YamlFile(teamPathConfig);
        try {
            file.load();
        } catch (final Exception e) {
            e.printStackTrace();
            YamlFiles.initialTeams();
        }
        return file.getStringList(teamName);
    }

    public static List<String> listTeams() {
        List<String> listaDeEquipos = new ArrayList<>();
        try{
            YamlFile teamFile = new YamlFile(teamPathConfig);
            teamFile.load();
            final ConfigurationSection section = teamFile.getConfigurationSection("");
            for(final String key : section.getKeys(false)) {
                if(section.isList(key)) {
                    listaDeEquipos.add(key.replace(" ", "-"));
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            YamlFiles.initialTeams();
            listTeams();
        }
        return listaDeEquipos;
    }

    public static void sendTeamMessage(Player sender, AsyncPlayerChatEvent event){
        YamlFile file = new YamlFile(teamPathConfig);
        try {
            if(PlayerData.onTeam(sender)){
                file.load();
                List<String> teamPlayers = file.getStringList(PlayerData.getPlayerDataConfig(sender, "team"));
                for(String player : teamPlayers){
                    Player target = Bukkit.getPlayer(player);
                    if(target != null){
                        target.sendMessage("§7[§b" + PlayerData.getPlayerDataConfig(sender, "team") + "§7] §b" + sender.getName() + "§7: §f" + event.getMessage());
                    }
                }
                event.setCancelled(true);
            } else {
                sender.sendMessage("§cNo estas en un equipo, cambiando a chat global");
                PlayerData.setDataPlayer(sender, "chat", "global");
            }
        } catch (final Exception e) {
            e.printStackTrace();
            YamlFiles.initialTeams();
        }
    }

    public static void infoTeam(Player target, String teamName){
        try{
            YamlFile teamFile = new YamlFile(teamPathConfig);
            teamFile.load();
            List<String> teamMembers = teamFile.getStringList(teamName);
            target.sendMessage("§aEquipo: §e" + teamName);
            target.sendMessage("§aLíder: §e" + teamMembers.get(0));
            target.sendMessage("§aMiembros:");
            for(int i = 1; i < teamMembers.size(); i++) {
                target.sendMessage(teamMembers.get(i));
            }
            target.sendMessage("§aMiembros: §e" + (teamMembers.size() - 1));
            target.sendMessage("--------------------");
        } catch(Exception e){
            e.printStackTrace();
            YamlFiles.initialTeams();
            infoTeam(target, teamName);
        }
    }

}
