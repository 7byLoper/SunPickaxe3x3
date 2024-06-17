package by.loper.sunpickaxes;

import by.loper.sunpickaxes.command.PickaxeCommand;
import by.loper.sunpickaxes.listener.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SunPickaxes extends JavaPlugin {
    private static SunPickaxes instance;

    public static SunPickaxes getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);

        this.getCommand("SunPickaxe").setExecutor(new PickaxeCommand());

        this.showInfo();
    }

    private void showInfo() {
        Logger logger = this.getLogger();

        PluginDescriptionFile description = this.getDescription();

        logger.info("");
        logger.info(String.format(" | %s Enabled", description.getName()));
        logger.info(String.format(" | Version: %s", description.getVersion()));
        logger.info("");
    }
}

