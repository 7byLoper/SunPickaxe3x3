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
import java.util.List;

public class Manager {
    private static ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
    private static List<String> materials = SunPickaxe3x3.getInstance().getConfig().getStringList("Pickaxe.WhiteListBlock");
    private static String PickaxeDisplayName = SunPickaxe3x3.getInstance().getConfig().getString("Pickaxe.DisplayName").replace("&","§");
    private static List<String> lore = SunPickaxe3x3.getInstance().getConfig().getStringList("Pickaxe.Lore");
    private static ItemMeta meta;
    private static final Integer level = SunPickaxe3x3.getInstance().getConfig().getInt("Pickaxe.level");
    public static void givePickaxe(Player player) {
        meta = pickaxe.getItemMeta();
        if(lore != null){
            meta.setLore(lore);
        }
        if(PickaxeDisplayName != null){
            meta.setDisplayName(PickaxeDisplayName);
        }
        meta.getPersistentDataContainer().set(new NamespacedKey(SunPickaxe3x3.getInstance(), "sunPickaxe"), PersistentDataType.STRING, "3x3Pickaxe");
        pickaxe.setItemMeta(meta);
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
        if(x1 > x2){
            xMin = x2;
            xMax = x1;
        }else{
            xMin = x1;
            xMax = x2;
        }
        if(y1 > y2){
            yMin = y2;
            yMax = y1;
        }else{
            yMin = y1;
            yMax = y2;
        }
        if(z1 > z2){
            zMin = z2;
            zMax = z1;
        }else{
            zMin = z1;
            zMax = z2;
        }
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
}

