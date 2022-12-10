package owo.carrot.teamchat.Utils;

import org.simpleyaml.configuration.file.YamlFile;

public class YamlFiles {

    public static void verifyFiles(){
        Utils.infoServer("Verifying files...");
        initialMessages();
        initialPlayerData();
        initialTeams();
    }

    final static String messagesPathConfig = "plugins/TeamChat/messages.yml";
    final static String teamPathConfig = "plugins/TeamChat/teams.yml";
    final static String playerDataConfig = "plugins/TeamChat/player.yml";

    public static String getMessage(String id){
        final YamlFile yamlFile = new YamlFile(messagesPathConfig);
        try{
            yamlFile.load();
        } catch (Exception e) {
            e.printStackTrace(); //temporal
            initialMessages();
            getMessage(id);
        }
        return yamlFile.getString(id);
    }

    public static void initialMessages(){
        final YamlFile fileMessage = new YamlFile(messagesPathConfig);
        try{
            if (!fileMessage.exists()) {
                //create file
                fileMessage.createNewFile();
                //prefix
                fileMessage.set("prefix", "&8[&bTeamChat&8] &r>> ");

                //success
                fileMessage.set("createTeam", "&aHas creado el equipo &b%team%");
                fileMessage.set("joinTeam", "&aEl jugador %player% pertenecera ahora al equipo &b%team%");
                fileMessage.set("alreadyTeam", "&cEl jugador %player% ya pertenece a un equipo");
                fileMessage.set("deleteTeam", "&aHas eliminado el equipo &b%team%");
                fileMessage.set("toggleChat", "&aHas cambiado el chat a &bequipo");
                fileMessage.set("toggleChatGlobal", "&aHas cambiado el chat a &bglobal");

                //errors
                fileMessage.set("noPermission", "&cNo tienes permiso para hacer esto.");
                fileMessage.set("noTeamFound", "&cNo se ha encontrado el equipo.");
                fileMessage.set("noPlayerOnTeam", "&cEl jugador %player% no pertenece a ningun equipo");
                fileMessage.set("noTeam", "&cNo perteneces a ningún equipo.");
                fileMessage.save();

                Utils.defaultServer("Creating default messages.yml");
            }
        } catch (final Exception ignored) {}
    }

    public static void initialTeams(){
        final YamlFile fileTeam = new YamlFile(teamPathConfig);
        try{
            if (!fileTeam.exists()) {
                //create file
                fileTeam.createNewFile();
                fileTeam.save();
                Utils.defaultServer("Creating default teams.yml");
            }
        } catch (final Exception ignored) {}
    }

    public static void initialPlayerData(){
        final YamlFile filePlayerData = new YamlFile(playerDataConfig);
        try{
            if (!filePlayerData.exists()) {
                //create file
                filePlayerData.createNewFile();
                filePlayerData.save();
                Utils.defaultServer("Creating default player.yml");
            }
        } catch (final Exception ignored) {}
    }
}
