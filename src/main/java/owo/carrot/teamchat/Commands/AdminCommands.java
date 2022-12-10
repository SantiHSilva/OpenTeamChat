package owo.carrot.teamchat.Commands;

import owo.carrot.teamchat.Utils.TeamUtils;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;


@CommandAlias("tca|dev|adminteamchat")
@CommandPermission("teamchat.admin")
public class AdminCommands extends BaseCommand {

    @Subcommand("createTeam")
    @CommandPermission("teamchat.admin.team")
    //TODO! hacer funcional xd
    //@CommandCompletion("create|delete|list|join|leave|info|set|add|remove|rename")
    @CommandCompletion("nombreEquipo LiderEquipo @nothing")
    public void createTeam(Player p, String nombre, String lider) {
        p.sendMessage("Creando equipo " + nombre + " con lider " + lider);
        TeamUtils.createTeam(nombre, lider);
    }

    @Subcommand("listTeams")
    public void listTeams(Player p) {
        p.sendMessage("La lista de equipos es: " + TeamUtils.listTeams());
        for(int i = 0; i < TeamUtils.listTeams().size(); i++) {
            TeamUtils.infoTeam(p, TeamUtils.listTeams().get(i));
        }
    }

    @Subcommand("addPlayer")
    @CommandCompletion("@teamList @players @nothing")
    public void addPlayer(Player p, String equipo, String[] target) {
        if(TeamUtils.verifyTeam(equipo.replace("-", " "))) {
            p.sendMessage("Añadiendo a " + target[0] + " al equipo " + equipo);
            TeamUtils.addORemovePlayer(p, equipo, target[0], false);
        } else {
            p.sendMessage("El equipo " + equipo + " no existe");
        }
    }

    @Subcommand("removePlayer")
    @CommandCompletion("@teamList @players @nothing")
    public void removePlayer(Player p, String equipo, String[] target){
        if(TeamUtils.verifyTeam(equipo.replace("-", " "))) {
            p.sendMessage("Quitando a " + target[0] + " del equipo " + equipo);
            TeamUtils.addORemovePlayer(p, equipo, target[0], true);
        } else {
            p.sendMessage("El equipo " + equipo + " no existe");
        }
    }

    @Subcommand("listMembers")
    @CommandCompletion("@teamList")
    public void listMembers(Player p, String equipo) {
        if(TeamUtils.verifyTeam(equipo.replace("-", " "))) {
            p.sendMessage("Miembros del equipo " + equipo.replace("-", " ") + ": " + TeamUtils.getTeamPlayers(equipo.replace("-", " ")));
        } else {
            p.sendMessage("El equipo " + equipo + " no existe");
        }
    }
}
