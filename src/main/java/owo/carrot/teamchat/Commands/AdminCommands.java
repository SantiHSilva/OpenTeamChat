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
    @CommandCompletion("equipo @players")
    public void addPlayer(Player p, String equipo, String[] target) {
        if(TeamUtils.verifyTeam(equipo)){
            p.sendMessage("Añadiendo a " + target[0] + " al equipo " + equipo);
            TeamUtils.addPlayer(p, equipo, target[0]);
        } else {
            p.sendMessage("El equipo " + equipo + " no existe");
        }
    }
}
