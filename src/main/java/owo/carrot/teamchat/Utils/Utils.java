package owo.carrot.teamchat.Utils;

import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static void defaultServer(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    public static void infoServer(String message) {
        Bukkit.getLogger().info(message);
    }

    public static String dateFormat(Date date){
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date) + " / " + TimeZone.getDefault().getID();
    }

}
