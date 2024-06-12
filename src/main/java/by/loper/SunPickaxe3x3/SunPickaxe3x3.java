package by.loper.SunPickaxe3x3;

import java.util.List;
import java.util.logging.Logger;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class SunPickaxe3x3 extends JavaPlugin implements Listener {
    private static SunPickaxe3x3 instance;
    Logger log = Logger.getLogger("Minecraft");
    private static List<String> materials;

    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |SunPickaxe3x3 Enabled   ");
        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |Version : 1.0    ");
        this.log.warning("");
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("SunPickaxe").setExecutor(new PickaxeCommand());
        materials = SunPickaxe3x3.getInstance().getConfig().getStringList("Pickaxe.WhiteListBlock");
    }

    public void onDisable() {
        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |SunPickaxe3x3 Disabled   ");
        this.log.warning("");
        this.log.warning(ChatColor.GREEN + "  |Version : 1.0    ");
        this.log.warning("");
    }

    public static SunPickaxe3x3 getInstance() {
        return instance;
    }

    @EventHandler
    public void mainHand(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
        if (!meta.getPersistentDataContainer().has(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING)) return;
        List<Block> blocks = PickaxeManager.select(new Location(b.getWorld(), (b.getX() + 1), (b.getY() + 1), (b.getZ() + 1)), new Location(b.getWorld(), (b.getX() - 1), (b.getY() - 1), (b.getZ() - 1)));
        RegionContainer container = getWorldGuard().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        for (Block block1 : blocks) {
            for (String mat : materials) {
                ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(block1.getLocation()));
                Material material = Material.valueOf(mat);
                if (block1.getType().equals(material)) {
                    if(set.size() == 0) {
                        block1.breakNaturally();
                    }else {
                        for (ProtectedRegion region : set) {
                            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
                            if (region.isMember(localPlayer)) {
                                block1.breakNaturally();
                            } else if (region.isOwner(localPlayer)) {
                                block1.breakNaturally();
                            } else if (set.testState(null, Flags.BLOCK_BREAK)) {
                                block1.breakNaturally();
                            }
                        }
                    }
                }
            }
        }
    }
    public static WorldGuard getWorldGuard() {
        return WorldGuard.getInstance();
    }
}

