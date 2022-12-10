package owo.carrot.teamchat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import owo.carrot.teamchat.Commands.AdminCommands;
import owo.carrot.teamchat.Listeners.JoinOrLeaveServer;
import owo.carrot.teamchat.Utils.Utils;
import owo.carrot.teamchat.Utils.YamlFiles;

public final class Teamchat extends JavaPlugin {

    @Override
    public void onEnable() {
        Utils.infoServer("Activando OpenTeamchat!");
        YamlFiles.verifyFiles();
        loadListener();
        loadCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Utils.infoServer("Desactivando OpenTeamchat!");
    }

    public void loadListener(){
        registerListeners(
                new JoinOrLeaveServer()
        );
    }

    private void registerListeners(Listener... listeners){
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public void loadCommands(){
        registerCommands(
                new AdminCommands()
        );
    }

    private void registerCommands(Object... commands) {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        for (Object command : commands) {
            commandManager.registerCommand((BaseCommand) command);
        }
    }

}
