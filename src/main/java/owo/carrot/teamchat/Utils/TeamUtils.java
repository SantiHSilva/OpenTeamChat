package owo.carrot.teamchat.Utils;

import org.bukkit.entity.Player;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.ArrayList;
import java.util.List;

import static owo.carrot.teamchat.Utils.YamlFiles.teamPathConfig;

public class TeamUtils {

    public static void createTeam(String teamName, String leader) {
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

    public static void addPlayer(Player sender, String teamName, String target){
        try{
            YamlFile teamFile = new YamlFile(teamPathConfig);
            teamFile.load();
            List<String> playerCache = teamFile.getStringList(teamName);
            playerCache.add(target);
            teamFile.set(teamName, playerCache);
            teamFile.save();
            sender.sendMessage( target + " added to team " + teamName);
        } catch(Exception e){
            YamlFiles.initialTeams();
            sender.sendMessage("El equipo " + teamName + " no existe");
        }
    }

    public static List<String> listTeams() {
        List<String> listaDeEquipos = new ArrayList<>();
        try{
            YamlFile teamFile = new YamlFile(teamPathConfig);
            teamFile.load();
            final ConfigurationSection section = teamFile.getConfigurationSection("");
            for(final String key : section.getKeys(false)) {
                if(section.isList(key)) {
                    listaDeEquipos.add(key);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            YamlFiles.initialTeams();
            listTeams();
        }
        return listaDeEquipos;
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
