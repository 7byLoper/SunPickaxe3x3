package by.loper.SunPickaxe3x3.pickaxe;


import by.loper.SunPickaxe3x3.SunPickaxe3x3;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager {
    private static ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
    private static List<String> materials = SunPickaxe3x3.getInstance().getConfig().getStringList("Pickaxe.3x3.WhiteListBlock");
    private static String pickaxeDisplayName3x3 = SunPickaxe3x3.getInstance().getConfig().getString("Pickaxe.3x3.DisplayName").replace("&","§");
    private static String pickaxeDisplayNameMagnet = SunPickaxe3x3.getInstance().getConfig().getString("Pickaxe.magnet.DisplayName").replace("&","§");
    private static String pickaxeDisplayNameAutoMelting = SunPickaxe3x3.getInstance().getConfig().getString("Pickaxe.autoMelting.DisplayName").replace("&","§");
    private static List<String> lore3x3 = SunPickaxe3x3.getInstance().getConfig().getStringList("Pickaxe.3x3.Lore");
    private static List<String> loreMagnet = SunPickaxe3x3.getInstance().getConfig().getStringList("Pickaxe.magnet.Lore");
    private static List<String> loreAutoMelting = SunPickaxe3x3.getInstance().getConfig().getStringList("Pickaxe.autoMelting.Lore");
    private static HashMap<Material,Material> autoMeltingBlockList = new HashMap<>();
    private static ItemMeta meta3x3,metaMagnet,metaAutoMelting;
    private static final int level = SunPickaxe3x3.getInstance().getConfig().getInt("Pickaxe.3x3.level");
    public static void givePickaxe3x3(Player player) {

        meta3x3 = pickaxe.getItemMeta();

        if(lore3x3 != null){

            meta3x3.setLore(lore3x3);

        }
        if(pickaxeDisplayName3x3 != null){

            meta3x3.setDisplayName(pickaxeDisplayName3x3);

        }

        meta3x3.getPersistentDataContainer().set(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING, "3x3");
        pickaxe.setItemMeta(meta3x3);
        player.getInventory().addItem(pickaxe);

    }
    public static void givePickaxeMagnet(Player player) {

        metaMagnet = pickaxe.getItemMeta();

        if(loreMagnet != null){

            metaMagnet.setLore(loreMagnet);

        }
        if(pickaxeDisplayName3x3 != null){

            metaMagnet.setDisplayName(pickaxeDisplayNameMagnet);

        }

        metaMagnet.getPersistentDataContainer().set(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING, "magnet");
        pickaxe.setItemMeta(metaMagnet);
        player.getInventory().addItem(pickaxe);

    }
    public static void givePickaxeAutoMelting(Player player) {

        metaAutoMelting = pickaxe.getItemMeta();

        if(loreAutoMelting != null){

            metaAutoMelting.setLore(loreAutoMelting);

        }
        if(pickaxeDisplayNameAutoMelting != null){

            metaAutoMelting.setDisplayName(pickaxeDisplayNameAutoMelting);

        }

        metaAutoMelting.getPersistentDataContainer().set(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING, "automelting");
        pickaxe.setItemMeta(metaAutoMelting);
        player.getInventory().addItem(pickaxe);

    }
    public static List<Block> select(Location loc1,Location loc2){
        List<Block> blocks = new ArrayList<Block>();

        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();
        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();

        int xMin, yMin, zMin;
        int xMax, yMax, zMax;
        int x, y, z;

        xMin = Math.min(x1,x2);
        xMax = Math.max(x1,x2);

        yMin = Math.min(y1,y2);
        yMax = Math.max(y1,y2);

        zMin = Math.min(z1,z2);
        zMax = Math.max(z1,z2);

        for(x = xMin; x <= xMax; x ++){
            for(y = yMin; y <= yMax; y ++){
                for(z = zMin; z <= zMax; z ++){

                    Block b = new Location(loc1.getWorld(), x, y, z).getBlock();
                    blocks.add(b);

                }
            }
        }
        return blocks;
    }

    public static Integer getLevel() {
        return level;
    }
    public static List<String> getMaterials() {
        return materials;
    }

    public static HashMap<Material,Material> getAutoMeltingBlockList(){
        return autoMeltingBlockList;
    }
}

