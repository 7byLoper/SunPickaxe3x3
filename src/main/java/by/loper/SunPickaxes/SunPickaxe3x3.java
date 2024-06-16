package by.loper.SunPickaxes;

import by.loper.SunPickaxes.pickaxe.Manager;
import by.loper.SunPickaxes.pickaxe.PickaxeCMD;
import by.loper.SunPickaxes.pickaxe.PickaxeEvents;
import com.sk89q.worldguard.WorldGuard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SunPickaxe3x3 extends JavaPlugin {
    private static SunPickaxe3x3 instance;
    Logger log = Logger.getLogger("Minecraft");

    public static SunPickaxe3x3 getInstance() {
        return instance;
    }

    public static WorldGuard getWorldGuard() {
        return WorldGuard.getInstance();
    }

    public void onEnable() {

        this.saveDefaultConfig();
        instance = this;

        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |SunPickaxe3x3 Enabled   ");
        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |Version : 1.1    ");
        this.log.warning("");

        Bukkit.getPluginManager().registerEvents(new PickaxeEvents(), this);
        this.getCommand("SunPickaxe").setExecutor(new PickaxeCMD());

        for (String s2 : getConfig().getConfigurationSection("Pickaxe.autoMelting.blockList").getKeys(false)) {
            Manager.getAutoMeltingBlockList().put(Material.valueOf(s2), Material.valueOf(getConfig().getString("Pickaxe.autoMelting.blockList." + s2)));
        }
    }

    public void onDisable() {

        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |SunPickaxe3x3 Disabled   ");
        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |Version : 1.1    ");
        this.log.warning("");

    }
}

