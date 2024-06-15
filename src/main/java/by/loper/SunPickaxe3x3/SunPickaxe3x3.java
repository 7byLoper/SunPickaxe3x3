package by.loper.SunPickaxe3x3;

import java.util.logging.Logger;

import by.loper.SunPickaxe3x3.pickaxe.Manager;
import by.loper.SunPickaxe3x3.pickaxe.PickaxeCMD;
import by.loper.SunPickaxe3x3.pickaxe.PickaxeEvents;
import com.sk89q.worldguard.WorldGuard;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
public final class SunPickaxe3x3 extends JavaPlugin{
    private static SunPickaxe3x3 instance;
    Logger log = Logger.getLogger("Minecraft");
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

        for(String s2 : getConfig().getConfigurationSection("Pickaxe.autoMelting.blockList").getKeys(false)){
            Manager.getAutoMeltingBlockList().put(Material.valueOf(s2), Material.valueOf(getConfig().getString("Pickaxe.autoMelting.blockList."+s2)));
        }
    }
    public void onDisable() {

        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |SunPickaxe3x3 Disabled   ");
        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |Version : 1.1    ");
        this.log.warning("");

    }
    public static SunPickaxe3x3 getInstance() {
        return instance;
    }
    public static WorldGuard getWorldGuard() {
        return WorldGuard.getInstance();
    }
}

