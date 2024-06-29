package by.loper.SunPickaxe3x3.pickaxe;

import by.loper.SunPickaxe3x3.SunPickaxe3x3;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.List;

public class PickaxeEvents implements Listener {

    @EventHandler
    public void mainHand(BlockBreakEvent e) {

        Block b = e.getBlock();
        Player p = e.getPlayer();
        ItemStack pItem = p.getInventory().getItemInMainHand();
        ItemMeta meta = pItem.getItemMeta();
        if(meta == null) return;
        if (!meta.getPersistentDataContainer().has(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING))
            return;
        RegionContainer container = SunPickaxe3x3.getWorldGuard().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();

        if (meta.getPersistentDataContainer().get(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING).equals("3x3")) {

            int level = Manager.getLevel();
            List<Block> blocks = Manager.select(new Location(b.getWorld(), (b.getX() + level), (b.getY() + level), (b.getZ() + level)), new Location(b.getWorld(), (b.getX() - level), (b.getY() - level), (b.getZ() - level)));


            for (Block block1 : blocks) {
                for (String mat : Manager.getMaterials()) {

                    ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(block1.getLocation()));
                    Material material = Material.valueOf(mat);

                    if (block1.getType().equals(material)) {
                        if (set.size() == 0) {

                            block1.breakNaturally();

                        } else {

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

        else if (meta.getPersistentDataContainer().get(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING).equals("magnet")) {

            ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(b.getLocation()));

            Collection<ItemStack> items = b.getDrops();
            for(ItemStack item : items) {
                if (set.size() == 0) {
                    b.setType(Material.AIR);
                    e.setCancelled(true);
                    p.getInventory().addItem(item);

                } else {
                    for (ProtectedRegion region : set) {

                        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);

                        if (region.isMember(localPlayer)) {
                            b.setType(Material.AIR);
                            e.setCancelled(true);
                            p.getInventory().addItem(item);
                        } else if (region.isOwner(localPlayer)) {
                            b.setType(Material.AIR);
                            e.setCancelled(true);
                            p.getInventory().addItem(item);
                        } else if (set.testState(null, Flags.BLOCK_BREAK)) {
                            b.setType(Material.AIR);
                            e.setCancelled(true);
                            p.getInventory().addItem(item);
                        }
                    }
                }
            }

        }
        else if (meta.getPersistentDataContainer().get(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING).equals("automelting")) {
            for (Material key  : Manager.getAutoMeltingBlockList().keySet()){

                ItemStack item = new ItemStack(Manager.getAutoMeltingBlockList().get(key));

                if(b.getType() == key){
                    ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(b.getLocation()));
                    Location location = b.getLocation();

                    if (set.size() == 0) {

                        e.setDropItems(false);

                        location.getWorld().dropItemNaturally(location,item);

                    } else {

                        for (ProtectedRegion region : set) {

                            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);

                            if (region.isMember(localPlayer)) {
                                e.setDropItems(false);
                                location.getWorld().dropItemNaturally(location,item);
                            } else if (region.isOwner(localPlayer)) {
                                e.setDropItems(false);
                                location.getWorld().dropItemNaturally(location,item);
                            } else if (set.testState(null, Flags.BLOCK_BREAK)) {
                                e.setDropItems(false);
                                location.getWorld().dropItemNaturally(location,item);
                            }
                        }
                    }
                }
            }
        }
    }
}

