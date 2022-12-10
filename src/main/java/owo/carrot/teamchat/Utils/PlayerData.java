package owo.carrot.teamchat.Utils;

import org.bukkit.entity.Player;
import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.Date;

import static owo.carrot.teamchat.Utils.YamlFiles.initialPlayerData;
import static owo.carrot.teamchat.Utils.YamlFiles.playerDataConfig;

public class PlayerData {

    public static Boolean verifyDataPlayer(Player target) {
        YamlFile file = new YamlFile(playerDataConfig);
        try {
            file.load();
        } catch (final Exception e) {
            e.printStackTrace(); //temporal
            Utils.infoServer("Generando player.yml");
            initialPlayerData();
        }
        return file.isSet(target.getUniqueId().toString());
    }

    public static void updateLastLogin(Player target){
        setDataPlayer(target, "lastLogin", Utils.dateFormat(new Date()));
    }

    public static void createDataPlayer(Player target){
        try{
            YamlFile playerDataFile = new YamlFile(playerDataConfig);
            ConfigurationSection playerData = playerDataFile.createSection(target.getUniqueId().toString());
            playerData.set("nick", target.getName());
            playerData.set("team", "none");
            playerData.set("chat", "global");
            playerData.set("lastLogin", Utils.dateFormat(new Date()));
            playerDataFile.save();
        } catch(Exception e){
            e.printStackTrace(); //temporal
            YamlFiles.initialPlayerData();
        }
    }

    public static void setDataPlayer(Player target, String id, String value){
        YamlFile file = new YamlFile(playerDataConfig);
        try {
            file.load();
            file.set(target.getUniqueId() + "." + id, value);
            file.save();
        } catch (final Exception e) {
            e.printStackTrace();
            Utils.infoServer("Generando player.yml");
            initialPlayerData();
            setDataPlayer(target, id, value);
        }
    }

    public static String getPlayerDataConfig(Player target, String id){
        YamlFile file = new YamlFile(playerDataConfig);
        try {
            file.load();
        } catch (final Exception e) {
            e.printStackTrace(); //temporal
            Utils.infoServer("Generando player.yml");
            initialPlayerData();
        }
        return (String) file.get(target.getUniqueId() + "." + id);
    }

}
